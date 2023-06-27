package cs3500.pa04.model.pieces;

import cs3500.pa04.model.json.data.CoordJson;
import cs3500.pa04.model.json.data.ShipJson;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.Direction;
import cs3500.pa04.model.types.ShipType;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Ship on a board
 */
public class Ship {
  private final ShipType shipType;
  private final ArrayList<Coord> shipLocation;
  private final ArrayList<Coord> hitShips;
  private int floatStatus;

  /**
   * Constructor for Ship
   *
   * @param shipType this ship's type
   * @param shipLocation list of coordinates representing where each piece of this ship lives
   */
  public Ship(ShipType shipType, ArrayList<Coord> shipLocation) {
    this.hitShips = new ArrayList<>();
    this.shipType = shipType;
    this.shipLocation = shipLocation;
    this.floatStatus = 0;
  }

  /**
   * Returns a ShipJson instance based on this Ship's location on a board
   *
   * @return a ShipJson representing this ship
   */
  public ShipJson getShipJson() {
    // checking for horizontal or vertical
    Direction axis;
    if (shipLocation.get(0).getX() == shipLocation.get(shipLocation.size() - 1).getX()) {
      axis = Direction.VERTICAL;
    } else {
      axis = Direction.HORIZONTAL;
    }

    Coord startCoordinate = this.shipLocation.get(0);
    CoordJson startingCoordJson = new CoordJson(startCoordinate.getX(), startCoordinate.getY());
    return new ShipJson(startingCoordJson, shipType.size, axis);
  }

  /**
   * Fills the given board with a symbol representing this ship's type at the ship's coordinates
   *
   * @param board battlesalvo board
   */
  public void fillBoard(char[][] board) {
    for (Coord coordinate : shipLocation) {
      board[coordinate.getY()][coordinate.getX()] = shipType.toString().charAt(0);
    }
  }

  /**
   * true - afloat piece
   * false - sunken piece
   *
   * @return whether this ship is sunk or not
   */
  public boolean isSunk() {
    // there are no floating pieces, so the entire boat is sunk
    return this.floatStatus >= shipType.size;
  }

  /**
   * determines if the given list of coordinates hit any of this ship's coordinates
   * if so, update the state of this ship.
   *
   * @param opponentHits - list of opponent's targetted coordinates
   * @return successfully hit coordinates
   */
  public ArrayList<Coord> reportShipDamage(List<Coord> opponentHits) {
    ArrayList<Coord> currentHits = new ArrayList<>();
    for (Coord coord : this.shipLocation) {
      for (Coord opponentCoord : opponentHits) {
        if (opponentCoord.equals(coord)) {
          this.hitShips.add(coord);
          currentHits.add(coord);
          this.floatStatus++;
        }
      }
    }
    return currentHits;
  }
}
