package cs3500.pa04.model.pieces;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.ShipType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Board
 */
class BoardTest {
  private Board board;
  private final ArrayList<Coord> subCoords = new ArrayList<>();

  /**
   * Initialization method to be ran before every test method
   */
  @BeforeEach
  public void setUp() {
    subCoords.add(new Coord(0, 0));
    subCoords.add(new Coord(0, 1));
    subCoords.add(new Coord(0, 2));
    ArrayList<Ship> shipList = new ArrayList<>();
    shipList.add(new Ship(ShipType.SUBMARINE, subCoords));
    this.board = new Board(3, 3, shipList);
  }

  /**
   * Testing reportShipDamages, updateShipDamages
   */
  @Test
  void testShipDamages() {
    // adding a missed hit to test 'M' in update Ship damages
    ArrayList<Coord> tempCoords = new ArrayList<>(this.subCoords);
    tempCoords.add(new Coord(1, 1));

    // reporting the ship damages (all the ship's coordinates)
    ArrayList<Coord> successfulHits = this.board.reportShipDamages(tempCoords);

    // checking the successfulHits
    assertEquals(successfulHits, this.subCoords);

    // checking afterState of the 2d array board (testing updateShipDamages for both branches 'M',
    // and 'H'
    assertEquals(this.board.toString(),
        "  X 0 1 2\nY\n0   H 0 0\n1   H M 0\n2   H 0 0");
  }

  /**
   * Tests toString in Board
   */
  @Test
  void testToString() {
    assertEquals(this.board.toString(),
        "  X 0 1 2\nY\n0   S 0 0\n1   S 0 0\n2   S 0 0");

    Board bigBoard = new Board(11, 11, new ArrayList<>());
    assertEquals(bigBoard.toString(), """
           X 0 1 2 3 4 5 6 7 8 9 10
        Y
        0    0 0 0 0 0 0 0 0 0 0  0
        1    0 0 0 0 0 0 0 0 0 0  0
        2    0 0 0 0 0 0 0 0 0 0  0
        3    0 0 0 0 0 0 0 0 0 0  0
        4    0 0 0 0 0 0 0 0 0 0  0
        5    0 0 0 0 0 0 0 0 0 0  0
        6    0 0 0 0 0 0 0 0 0 0  0
        7    0 0 0 0 0 0 0 0 0 0  0
        8    0 0 0 0 0 0 0 0 0 0  0
        9    0 0 0 0 0 0 0 0 0 0  0
        10   0 0 0 0 0 0 0 0 0 0  0""");

    Board bigBoardV2 = new Board(5, 11, new ArrayList<>());
    assertEquals(bigBoardV2.toString(),
        """
              X 0 1 2 3 4 5 6 7 8 9 10
            Y
            0   0 0 0 0 0 0 0 0 0 0  0
            1   0 0 0 0 0 0 0 0 0 0  0
            2   0 0 0 0 0 0 0 0 0 0  0
            3   0 0 0 0 0 0 0 0 0 0  0
            4   0 0 0 0 0 0 0 0 0 0  0""");
  }
}