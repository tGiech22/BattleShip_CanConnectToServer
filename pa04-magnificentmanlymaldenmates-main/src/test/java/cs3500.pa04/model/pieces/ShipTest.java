package cs3500.pa04.model.pieces;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.json.data.ShipJson;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.Direction;
import cs3500.pa04.model.types.ShipType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Ship
 */
class ShipTest {
  private Ship submarine;
  private ArrayList<Coord> subCoords;

  /**
   * Initialization method to be ran before each test
   */
  @BeforeEach
  public void setUp() {
    subCoords = new ArrayList<>();
    subCoords.add(new Coord(0, 0));
    subCoords.add(new Coord(0, 1));
    subCoords.add(new Coord(0, 2));
    submarine = new Ship(ShipType.SUBMARINE, subCoords);
  }

  /**
   * Tests getShipJson
   */
  @Test
  public void testGetShipJson() {
    ArrayList<Coord> shipLoc = new ArrayList<>();
    shipLoc.add(new Coord(0, 0));
    shipLoc.add(new Coord(1, 0));
    shipLoc.add(new Coord(2, 0));

    Ship testShip = new Ship(ShipType.SUBMARINE, shipLoc);
    ShipJson test = testShip.getShipJson();

    assertEquals(test.length(), 3);
    assertEquals(test.shipCoord().x(), 0);
    assertEquals(test.shipCoord().y(), 0);
    assertEquals(test.direction(), Direction.HORIZONTAL);

    ArrayList<Coord> shipLoc2 = new ArrayList<>();
    shipLoc2.add(new Coord(1, 1));
    shipLoc2.add(new Coord(1, 2));
    shipLoc2.add(new Coord(1, 3));

    Ship testShip2 = new Ship(ShipType.SUBMARINE, shipLoc2);
    ShipJson test2 = testShip2.getShipJson();

    assertEquals(test2.length(), 3);
    assertEquals(test2.shipCoord().x(), 1);
    assertEquals(test2.shipCoord().y(), 1);
    assertEquals(test2.direction(), Direction.VERTICAL);
  }

  /**
   * Tests fillBoard
   */
  @Test
  public void testFillBoard() {
    char[][] board = new char[3][3];
    submarine.fillBoard(board);
    assertEquals(board[0][0], 'S');
    assertEquals(board[1][0], 'S');
    assertEquals(board[2][0], 'S');
  }

  /**
   * Tests isSunk and reportShipDamage
   */
  @Test
  public void testIsSunkAndShipDamage() {
    // pre sinking
    assertFalse(submarine.isSunk());

    // sinking
    ArrayList<Coord> successHits = submarine.reportShipDamage(this.subCoords);

    // testing successHits
    assertEquals(successHits, this.subCoords);

    // testing sinking
    assertTrue(submarine.isSunk());

    // testing edge case of hitting even though it's already sunk
    submarine.reportShipDamage(this.subCoords);

    // testing sinking
    assertTrue(submarine.isSunk());
  }
}