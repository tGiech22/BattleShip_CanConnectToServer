package cs3500.pa04.model.players;

import cs3500.pa04.Player;
import cs3500.pa04.model.pieces.Board;
import cs3500.pa04.model.pieces.Ship;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.GameResult;
import cs3500.pa04.model.types.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents an abstract class for a Player
 */
public abstract class AbstPlayer implements Player {
  private String name;
  private Board board;
  private Board opponentBoard;
  private ArrayList<Coord> shots;
  private final Random rand;
  private final View view;

  /**
   * Constructor for AbstPlayer
   *
   * @param name name of player
   * @param height height of player's board
   * @param width width of player's board
   * @param rand random object
   */
  public AbstPlayer(String name, int height, int width, Random rand, View view) {
    this.name = name;
    this.rand = rand;
    this.view = view;
    this.opponentBoard = new Board(height, width, new ArrayList<>());
  }

  /**
   * Sets this player's name with the given string
   *
   * @param str Name
   */
  public void setName(String str) {
    this.name = str;
  }


  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.opponentBoard = new Board(height, width, new ArrayList<>());
    // setting up initial storage to check for available locations for each ship
    ArrayList<Ship> ships = new ArrayList<>();
    boolean[][] availableLocations = new boolean[height][width];
    for (boolean[] row : availableLocations) {
      Arrays.fill(row, false);
    }

    for (ShipType ship : ShipType.values()) {
      for (int i = 0; i < specifications.get(ship); i++) {
        boolean position = rand.nextBoolean();
        if (!addShip(position, ships, availableLocations, ship)) {
          addShip(!position, ships, availableLocations, ship);
        }
      }
    }
    this.board = new Board(height, width, ships);
    return ships;
  }

  /**
   * sets this player's shots with the given list of shots
   *
   * @param shots list of this player's shots
   */
  public void setShots(ArrayList<Coord> shots) {
    this.shots = shots;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    return this.shots;
  }

  /**
   * adds a ship to the given list of ships
   *
   * @param position vertical or horizontal
   * @param ships list of ships
   * @param locations occupied and open spaces on a board
   * @param shipType Ship type
   * @return whether the method successfully added a ship to the given list of ships or not
   */
  private boolean addShip(boolean position, ArrayList<Ship> ships,
                          boolean[][] locations, ShipType shipType) {
    HashMap<Integer, Integer> availableIndexFit = new HashMap<>();
    for (int i = 0; i < Math.min(locations.length, locations[0].length); i++) {
      int startingIndex;
      if (position) {
        startingIndex = this.canFit(this.verticalToArray(locations, i), shipType.size);
      } else {
        startingIndex = this.canFit(locations[i], shipType.size);
      }

      if (startingIndex != -1) {
        availableIndexFit.put(i, startingIndex);
      }
    }

    if (availableIndexFit.isEmpty()) {
      return false;
    } else {
      Integer[] startingIndexes = availableIndexFit.keySet().toArray(new Integer[0]);
      int startingIndex = startingIndexes[this.rand.nextInt(startingIndexes.length)];
      ArrayList<Coord> shipCoords = new ArrayList<>();

      for (int i = 0; i < shipType.size; i++) {
        if (position) {
          // vertical
          shipCoords.add(new Coord(startingIndex, availableIndexFit.get(startingIndex) + i));
          locations[availableIndexFit.get(startingIndex) + i][startingIndex] = true;
        } else {
          shipCoords.add(new Coord(availableIndexFit.get(startingIndex) + i, startingIndex));
          locations[startingIndex][availableIndexFit.get(startingIndex) + i] = true;
        }
      }
      ships.add(new Ship(shipType, shipCoords));
      return true;
    }
  }

  /**
   * Transforms the given 2d array's column depending on the given index to a 1d array
   *
   * @param locations 2d array representing the locations of ships
   * @param index column index
   *
   * @return 1d array representing the column row
   */
  private boolean[] verticalToArray(boolean[][] locations, int index) {
    boolean[] tempArray = new boolean[locations.length];
    for (int i = 0; i < locations.length; i++) {
      tempArray[i] = locations[i][index];
    }
    return tempArray;
  }

  /**
   * Determines if the given size can fit in the given boolean array
   *
   * @param location boolean array containing true and false (true - occupied, false - open)
   * @param size size of ship
   * @return random valid index that the ship size can fit
   */
  private int canFit(boolean[] location, int size) {
    ArrayList<Integer> allPossibleIndexes = new ArrayList<>();
    int availableCount = 0;
    int startIndex = -1;
    for (int i = 0; i < location.length; i++) {
      // if location is false (nothing occupying)
      if (!location[i]) {
        if (startIndex == -1) {
          startIndex = i;
        }
        availableCount++;

        // adds every valid index based on the given size
        if (availableCount - size >= 0) {
          allPossibleIndexes.add(startIndex + (availableCount - size));
        }
      } else {
        // resets all counters if a ship piece already occupies the cell
        availableCount = 0;
        startIndex = -1;
      }
    }

    // returns a random valid index for the given size
    if (allPossibleIndexes.size() > 0) {
      return allPossibleIndexes.get(rand.nextInt(allPossibleIndexes.size()));
    } else {
      return -1;
    }
  }


  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *         ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return this.board.reportShipDamages(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    this.opponentBoard.updateShipDamages(this.takeShots(), shotsThatHitOpponentShips);
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    StringBuilder gameResult = new StringBuilder();
    if (GameResult.WIN == result) {
      gameResult.append("You win! ");
    } else if (GameResult.LOSE == result) {
      gameResult.append("You lose! ");
    } else {
      gameResult.append("Draw! ");
    }

    view.printMessage(gameResult + reason);
  }

  /**
   * Returns a string representation of the opponent's unknown board and this player's board
   *
   * @return string representing this player and this player's opponents boards
   */
  public String stringBoard() {
    return this.opponentBoard + "\n\n"
        + this.name() + "'s board:\n" + this.board;
  }

}
