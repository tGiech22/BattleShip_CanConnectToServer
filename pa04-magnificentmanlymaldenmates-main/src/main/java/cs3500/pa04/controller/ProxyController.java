package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.controller.player.AbstPlayerController;
import cs3500.pa04.model.JsonUtils;
import cs3500.pa04.model.json.data.CoordJson;
import cs3500.pa04.model.json.data.EndGameArgsJson;
import cs3500.pa04.model.json.data.FleetJson;
import cs3500.pa04.model.json.data.JoinDataJson;
import cs3500.pa04.model.json.data.SetupArgumentsJson;
import cs3500.pa04.model.json.data.ShipJson;
import cs3500.pa04.model.json.data.VolleyJson;
import cs3500.pa04.model.json.message.EndGameJson;
import cs3500.pa04.model.json.message.JoinJson;
import cs3500.pa04.model.json.message.MessageJson;
import cs3500.pa04.model.json.message.ReportDamageJson;
import cs3500.pa04.model.json.message.SetupJson;
import cs3500.pa04.model.json.message.SuccessfulHitsJson;
import cs3500.pa04.model.json.message.TakeShotsJson;
import cs3500.pa04.model.pieces.Ship;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.GameMode;
import cs3500.pa04.model.types.ShipType;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a ProxyController for the game BattleSalvo, typically uses a ComputerPlayer to
 * be sent to the given server
 */
public class ProxyController {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AbstPlayerController player;
  private final ObjectMapper mapper = new ObjectMapper();

  private List<Ship> currentShips;

  /**
   * Constructor for ProxyController
   * Constructs an instance of a ProxyPlayer
   *
   * @param server connection to the given server (host, port)
   * @param player an instance of a player
   * @throws IOException Thrown if the given server is unreachable
   */
  public ProxyController(Socket server, AbstPlayerController player) throws IOException {
    this.server = server;
    this.player = player;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
  }

  /**
   * While the server is still open, run constantly listens for messages in the format of
   * MessageJSON. When a message is received, this method parses it and delegates it to the
   * proper method JSON.
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.err.println("Unable to parse server's request");
    }
  }

  /**
   * Delegates the given MessageJson to its respective method handler method
   *
   * @param message MessageJson sent from the server
   */
  private void delegateMessage(MessageJson message) {
    String methodName = message.methodName();
    JsonNode arguments = message.arguments();

    // delegates the given MessageJson's method name to the correct handler method
    switch (methodName) {
      case "join" -> this.handleJoin();
      case "setup" -> this.handleSetup(arguments);
      case "take-shots" -> this.handleTakeShots();
      case "report-damage" -> this.handleReportDamage(arguments);
      case "successful-hits" -> this.handleSuccessfulHits(arguments);
      case "end-game" -> this.handleEndGame(arguments);
      default -> throw new IllegalStateException("Invalid message/method name");
    }
  }

  /**
   * Handles the join server request
   */
  private void handleJoin() {
    JoinDataJson joinData = new JoinDataJson(player.getName(), GameMode.SINGLE);

    JoinJson join = new JoinJson("join", JsonUtils.serializeRecord(joinData));

    this.out.println(JsonUtils.serializeRecord(join));
  }

  /**
   * Helper method for handleSetup
   * Initializes the instance of this player's list of ships
   *
   * @param setupArgs SetupArgumentsJson arguments
   */
  private void setupHelp(SetupArgumentsJson setupArgs) {
    HashMap<ShipType, Integer> spec = new HashMap<>();
    spec.put(ShipType.CARRIER, setupArgs.fleetSpec().carrier());
    spec.put(ShipType.BATTLESHIP, setupArgs.fleetSpec().battleship());
    spec.put(ShipType.DESTROYER, setupArgs.fleetSpec().destroyer());
    spec.put(ShipType.SUBMARINE, setupArgs.fleetSpec().submarine());
    int height = setupArgs.height();
    int width = setupArgs.width();

    this.currentShips = this.player.setup(height, width, spec);
  }

  /**
   * Handler for the setup method with the given JsonNode of arguments for this player
   *
   * @param arguments Arguments sent from the server
   */
  private void handleSetup(JsonNode arguments) {
    // converting the given arguments to real values (height, width, and fleet sizes)
    SetupArgumentsJson setupArgs = this.mapper.convertValue(arguments, SetupArgumentsJson.class);
    this.setupHelp(setupArgs);

    // converting the returned list of ships to a serialized SetupJson
    ShipJson[] shipJsons = new ShipJson[currentShips.size()];
    int index = 0;
    for (Ship ship : currentShips) {
      shipJsons[index] = ship.getShipJson();
      index++;
    }
    // converting the fleet to its own JSON
    FleetJson fleet = new FleetJson(shipJsons);

    // converting the fleet into a SetupJSON
    SetupJson setupResponse = new SetupJson("setup", JsonUtils.serializeRecord(fleet));

    this.out.println(JsonUtils.serializeRecord(setupResponse));
  }

  /**
   * Handler for the takeShots method
   */
  private void handleTakeShots() {
    List<Coord> shotsArgs = this.player.getShots(this.currentShips.size());
    int index = 0;
    CoordJson[] shotCoordArgs = new CoordJson[shotsArgs.size()];
    for (Coord coordinate : shotsArgs) {
      shotCoordArgs[index] = new CoordJson(coordinate.getX(), coordinate.getY());
      index++;
    }

    VolleyJson volleyResponse = new VolleyJson(shotCoordArgs);
    TakeShotsJson takeShotsResponse =
        new TakeShotsJson("take-shots", JsonUtils.serializeRecord(volleyResponse));
    this.out.println(JsonUtils.serializeRecord(takeShotsResponse));
  }

  /**
   * Handler for the reportDamage method with the given JsonNode arguments for this player
   * Reports the given arguments, which should be the opponents shots on this player's board
   *
   * @param arguments arguments tied to VolleyJson (Array of shots)
   */
  private void handleReportDamage(JsonNode arguments) {
    // converting the given JsonNode arguments to a list of coordinates from reportDamage
    VolleyJson opponentShotsArgs = this.mapper.convertValue(arguments, VolleyJson.class);
    List<Coord> opponentCoords = new ArrayList<>();
    for (CoordJson coordinate : opponentShotsArgs.volley()) {
      opponentCoords.add(new Coord(coordinate.x(), coordinate.y()));
    }
    List<Coord> validOpponentShotsList = this.player.reportDamage(opponentCoords);

    // converting returned List from reportDamage back to a JSON
    CoordJson[] validShotsArr = new CoordJson[validOpponentShotsList.size()];
    int index = 0;
    for (Coord coordinate : validOpponentShotsList) {
      validShotsArr[index] = new CoordJson(coordinate.getX(), coordinate.getY());
      index++;
    }
    VolleyJson validOpponentShots = new VolleyJson(validShotsArr);
    ReportDamageJson reportDamageJson = new ReportDamageJson("report-damage",
        JsonUtils.serializeRecord(validOpponentShots));
    this.out.println(JsonUtils.serializeRecord(reportDamageJson));

  }

  /**
   * Handler method for successfulHits with the given arguments for this player
   *
   * @param arguments JsonNode arguments for successfulHits
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    VolleyJson shotsOnOpponentBoardArgs = this.mapper.convertValue(arguments, VolleyJson.class);
    List<Coord> coords = new ArrayList<>();
    for (CoordJson coord : shotsOnOpponentBoardArgs.volley()) {
      coords.add(new Coord(coord.x(), coord.y()));
    }

    // update this player's opponent board representation
    this.player.successfulHits(coords);
    SuccessfulHitsJson successfulHitsResponse =
        new SuccessfulHitsJson("successful-hits", mapper.createObjectNode());
    this.out.println(JsonUtils.serializeRecord(successfulHitsResponse));
  }

  /**
   * Handler for endGame for this player with the given JsonNode arguments
   *
   * @param arguments JsonNode arguments tied to endGame
   */
  private void handleEndGame(JsonNode arguments) {
    EndGameArgsJson endGameArgs = this.mapper.convertValue(arguments, EndGameArgsJson.class);

    this.player.endGame(endGameArgs.result(), endGameArgs.reason());

    try {
      EndGameJson endGameResponse =
          new EndGameJson("end-game", mapper.createObjectNode());
      this.out.println(JsonUtils.serializeRecord(endGameResponse));
      this.server.close();
    } catch (IOException e) {
      System.err.println("Unable to close server");
    }

  }

}
