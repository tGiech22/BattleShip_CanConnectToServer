package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.Mocket;
import cs3500.pa04.controller.input.Reader;
import cs3500.pa04.controller.player.AbstPlayerController;
import cs3500.pa04.controller.player.ComputerPlayerController;
import cs3500.pa04.model.JsonUtils;
import cs3500.pa04.model.json.data.CoordJson;
import cs3500.pa04.model.json.data.EndGameArgsJson;
import cs3500.pa04.model.json.data.FleetSpecJson;
import cs3500.pa04.model.json.data.JoinDataJson;
import cs3500.pa04.model.json.data.SetupArgumentsJson;
import cs3500.pa04.model.json.data.VolleyJson;
import cs3500.pa04.model.json.message.EndGameJson;
import cs3500.pa04.model.json.message.JoinJson;
import cs3500.pa04.model.json.message.ReportDamageJson;
import cs3500.pa04.model.json.message.SetupJson;
import cs3500.pa04.model.json.message.SuccessfulHitsJson;
import cs3500.pa04.model.json.message.TakeShotsJson;
import cs3500.pa04.model.types.GameMode;
import cs3500.pa04.model.types.GameResult;
import cs3500.pa04.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (mocket)
 */
public class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController controller;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Check that the server returns a guess when given a hint.
   */
  @Test
  public void testSetupMethod() {
    // for takeShots
    CoordJson[] listCoords = new CoordJson[3];
    listCoords[0] = new CoordJson(0, 0);
    listCoords[1] = new CoordJson(1, 0);
    listCoords[2] = new CoordJson(2, 2);

    // for setup
    FleetSpecJson fleetSpecs = new FleetSpecJson(2, 1, 3, 1);
    SetupArgumentsJson setupArgs = new SetupArgumentsJson(8, 10, fleetSpecs);
    JsonNode setupNode = createSetupArgs(setupArgs);

    // for join
    JoinDataJson joinData = new JoinDataJson("Nam", GameMode.SINGLE);
    JsonNode joinNode = createJoin(joinData);

    VolleyJson volley = new VolleyJson(listCoords);
    JsonNode takeShotsNode = createTakeShots(volley);

    // for reportDamage
    VolleyJson validShots = new VolleyJson(listCoords);
    JsonNode reportDmgNode = createDamageReport(validShots);

    // for successfulHits
    VolleyJson shotOnOppBoard = new VolleyJson(listCoords);
    JsonNode successfulHitsNode = createSuccessfulHits(shotOnOppBoard);

    // for endGame
    EndGameArgsJson endGame = new EndGameArgsJson(GameResult.WIN, "All enemy ships sunk.");
    JsonNode endGameNode = createEndArgs(endGame);

    Mocket socket = new Mocket(this.testLog, List.of(setupNode.toString(),
        joinNode.toString(),
        takeShotsNode.toString(),
        reportDmgNode.toString(),
        successfulHitsNode.toString(),
        endGameNode.toString()));

    Appendable output = new PrintStream(System.out);
    Reader reader = new Reader(new InputStreamReader(System.in));
    View view = new View(output);
    AbstPlayerController testPlayer =
        new ComputerPlayerController(reader, 10, 8, view, new Random(15));

    try {
      this.controller = new ProxyController(socket, testPlayer);
    } catch (IOException e) {
      fail();
    }

    this.controller.run();

    assertTrue(logToString().contains("setup"));
    assertTrue(logToString().contains("join"));
    assertTrue(logToString().contains("\"arguments\":{\"fleet\":[{\"coord\":{\"x\":0,\"y\":2}"));
    assertTrue(logToString().contains("report-damage"));
    assertTrue(logToString().contains("successful-hits"));
    assertTrue(logToString().contains("end-game"));
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  private JsonNode createSetupArgs(Record setupArgs) {
    SetupJson setup = new SetupJson("setup", JsonUtils.serializeRecord(setupArgs));
    return JsonUtils.serializeRecord(setup);
  }

  private JsonNode createTakeShots(Record takeShotsArg) {
    TakeShotsJson takeShots = new TakeShotsJson("take-shots",
        JsonUtils.serializeRecord(takeShotsArg));
    return JsonUtils.serializeRecord(takeShots);
  }

  private JsonNode createEndArgs(Record endGameArg) {
    EndGameJson endGame = new EndGameJson("end-game",
        JsonUtils.serializeRecord(endGameArg));
    return JsonUtils.serializeRecord(endGame);
  }

  private JsonNode createDamageReport(Record damageReportArg) {
    ReportDamageJson damageReport = new ReportDamageJson("report-damage",
        JsonUtils.serializeRecord(damageReportArg));
    return JsonUtils.serializeRecord(damageReport);
  }

  private JsonNode createSuccessfulHits(Record successfulHitsArg) {
    SuccessfulHitsJson successfulHits = new SuccessfulHitsJson("successful-hits",
        JsonUtils.serializeRecord(successfulHitsArg));
    return JsonUtils.serializeRecord(successfulHits);
  }

  private JsonNode createJoin(Record joinArg) {
    JoinJson join = new JoinJson("join",
        JsonUtils.serializeRecord(joinArg));
    return  JsonUtils.serializeRecord(join);
  }
}
