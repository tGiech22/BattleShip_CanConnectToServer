package cs3500.pa04.controller.player;

import cs3500.pa04.Validator;
import cs3500.pa04.controller.input.ReaderInterface;
import cs3500.pa04.model.pieces.Ship;
import cs3500.pa04.model.players.AbstPlayer;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.GameResult;
import cs3500.pa04.model.types.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Abstract class for a PlayerController
 */
public abstract class AbstPlayerController {
  /**
   * gives access to subclasses to delegate to the player class
   */
  protected final AbstPlayer player;
  private final View view;
  private final int height;
  private final int width;
  private final ReaderInterface reader;
  private final int totalCoordinates;

  /**
   * Constructor for AbstPlayerController

   * @param player type of player
   * @param view View object
   * @param reader ReaderInterface object
   * @param height height of board
   * @param width width of board
   */
  public AbstPlayerController(AbstPlayer player, View view,
                              ReaderInterface reader, int height, int width) {
    this.reader = reader;
    this.player = player;
    this.view = view;
    this.player.setName(this.getName());
    this.height = height;
    this.width = width;
    this.totalCoordinates = height * width;
  }

  /**
   * Delegates to this player's setup method to return the list of setup ships
   *
   * @return List of setup ships
   */
  public List<Ship> setup() {
    return this.setup(height, width, this.getFleetSize(Math.min(height, width)));
  }

  /**
   * Delegates to this player's setup method with the given parameters
   * to return the list of setup ships
   *
   * @return List of setup ships
   */
  public List<Ship> setup(int height, int width, HashMap<ShipType, Integer> specs) {
    return this.player.setup(height, width, specs);
  }

  /**
   * Delegates to this player to report the damage from the given shots from the opponent

   * @param opponentShotsOnBoard shots from the opponent on this player's board
   * @return list of successful hits from the given list of shots
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return this.player.reportDamage(opponentShotsOnBoard);
  }

  /**
   * Delegates to this player to report successful hits on the opponent's board

   * @param shotsThatHitOpponentShips successful shots on the opponent's board
   */
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    this.player.successfulHits(shotsThatHitOpponentShips);
  }


  /**
   * takes in user input for shots to be sent to the opponent

   * @param maxShots maximum shots that can be sent to the opponent
   * @return all shots inputted
   */
  public List<Coord> getShots(int maxShots) {
    maxShots = Math.min(maxShots, totalCoordinates);
    ArrayList<Coord> shots = new ArrayList<>();
    do {
      view.printMessage("Please enter " + maxShots + " Shots in the format of \"X Y\":");
      int[] shotCoord;
      for (int i = 0; i < maxShots; i++) {
        String shot = reader.read();
        try {
          shotCoord = Validator.allNumArray(shot.split(" "), 2);
        } catch (IllegalArgumentException e) {
          view.printMessage("Invalid Input! Enter your Salvo again.");
          shots.clear();
          break;
        }
        if (Validator.isInBound(shotCoord, height, width, 0)) {
          shots.add(new Coord(shotCoord[0], shotCoord[1]));
        } else {
          view.printMessage("Invalid numerical bounds! Enter your Salvo again.");
        }
      }
    } while (shots.size() != maxShots);

    player.setShots(shots);
    return player.takeShots();
  }

  /**
   * Takes in user input to get this player's name

   * @return this player's inputted name
   */
  public String getName() {
    view.printMessage("Enter your name:");
    return reader.read();
  }

  /**
   * Takes in user input to get the user's fleet
   *
   * @param maxFleet maximum fleets that can be on the board
   * @return hashmap representing each fleet and their quantities
   */
  public HashMap<ShipType, Integer> getFleetSize(int maxFleet) {
    int carrier = -1;
    int battleship = -1;
    int destroyer = -1;
    int submarine = -1;
    int[] fleetSizes = null;
    boolean valid;
    do {
      view.printMessage("Please enter your fleet in the order of:");
      view.printMessage("[Carrier, Battleship, Destroyer, Submarine].");
      view.printMessage("Remember, your fleet size may not exceed " + maxFleet + ".");
      String input = reader.read();
      try {
        fleetSizes = Validator.allNumArray(input.split(" "), 4);
        carrier = fleetSizes[0];
        battleship = fleetSizes[1];
        destroyer = fleetSizes[2];
        submarine = fleetSizes[3];
      } catch (IllegalArgumentException e) {
        view.printMessage("Oh no! You entered invalid fleet sizes!");
      }
      valid = ((carrier > 0 && battleship > 0 && destroyer > 0 && submarine > 0)
          && (carrier + battleship + destroyer + submarine == maxFleet));
    } while (!valid);

    // assuming fleetSizes is valid
    HashMap<ShipType, Integer> fleets = new HashMap<>();
    fleets.put(ShipType.CARRIER, fleetSizes[0]);
    fleets.put(ShipType.BATTLESHIP, fleetSizes[1]);
    fleets.put(ShipType.DESTROYER, fleetSizes[2]);
    fleets.put(ShipType.SUBMARINE, fleetSizes[3]);
    return fleets;
  }

  /**
   * Tells the view object to print out this player's board
   */
  public void printBoard() {
    view.printMessage(this.player.stringBoard());
  }

  public void endGame(GameResult result, String reason) {
    this.player.endGame(result, reason);
  }
}
