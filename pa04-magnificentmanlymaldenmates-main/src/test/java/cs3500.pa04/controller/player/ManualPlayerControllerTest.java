package cs3500.pa04.controller.player;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.controller.input.ReaderInterface;
import cs3500.pa04.controller.input.mock.MockReader;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for ManualPlayerController and AbstPlayerController since everything in ManualPlayer
 * Controller is in the abstract class by default
 */
class ManualPlayerControllerTest {
  private AbstPlayerController manual;
  private Appendable appendable;
  private View view;
  private Random rand;

  /**
   * Initialization method to be ran before each test method
   */
  @BeforeEach
  public void setUp() {
    this.rand = new Random(5);
    this.appendable = new StringBuilder();
    this.view = new View(this.appendable);
    manual = null;
  }

  /**
   * Sets manual to a "Nam\n2 1 1 2" as its input
   */
  private void setManual() {
    this.setManual(new MockReader("Nam\n2 1 1 2"));
  }

  /**
   * Initializes manual with the given ReaderInterface
   *
   * @param reader ReaderInterface object (Reader or MockReader)
   */
  private void setManual(ReaderInterface reader) {
    manual = new ManualPlayerController(reader, this.view, 6, 6, this.rand);
    this.manual.setup();
  }

  /**
   * Tests setup with multiple different "user" inputs from readable
   */
  @Test
  public void testSetup() {
    // setting up the string to input in order to get all edge cases for setup
    // 0 0 0 0, 6 0 0 0, 3 3 0 0, 2 2 2 0, empty line, 2 2 2 2, and 2 2 1 1 covers all edge cases
    // for the if statement toward the end
    ReaderInterface reader = new MockReader("""
        Nam

        0 0 0 0
        6 0 0 0
        3 3 0 0
        2 2 2 0
        2 2 2 2
        1 1 2 2""");
    this.setManual(reader);

    assertTrue(this.appendable.toString()
        .contains("""
            Please enter your fleet in the order of:
            [Carrier, Battleship, Destroyer, Submarine].
            Remember, your fleet size may not exceed 6."""));
    assertTrue(this.appendable.toString().contains("Oh no! You entered invalid fleet sizes!"));
  }

  /**
   * Tests printBoard
   */
  @Test
  public void testPrintBoard() {
    // Created this specific testset to show Random seed 2 and how there can be vertical and
    // horizontal pieces
    ReaderInterface input = new MockReader("Nam\n1 1 2 2\n");
    this.setManual(input);

    this.manual.printBoard();
    assertTrue(appendable.toString().contains("""
          X 0 1 2 3 4 5
        Y
        0   0 0 0 0 0 0
        1   0 0 0 0 0 0
        2   0 0 0 0 0 0
        3   0 0 0 0 0 0
        4   0 0 0 0 0 0
        5   0 0 0 0 0 0

        Nam's board:
          X 0 1 2 3 4 5
        Y
        0   0 0 0 0 S C
        1   D D D D S C
        2   S S S 0 S C
        3   0 D D D D C
        4   B B B B B C
        5   0 0 0 0 0 C
        """));
  }


  /**
   * Tests getShots and SuccessfulHits
   * getShots needs to be ran prior to successfulHits
   */
  @Test
  public void testGetShotsAndSuccessfulHits() {
    // "a a" and "100 100" for testing edge cases
    ReaderInterface reader = new MockReader("Nam\n1 1 2 2\na a\n100 100\n0 0");
    this.setManual(reader);
    List<Coord> shots = manual.getShots(1);
    assertTrue(shots.get(0).equals(new Coord(0, 0)));

    // testing successful hit since getShots needs to be ran prior
    this.manual.successfulHits(shots);
  }

  /**
   * Tests reportDamage
   */
  @Test
  public void testReportDamage() {
    this.setManual();
    List<Coord> testCoords = new ArrayList<>();
    testCoords.add(new Coord(0, 0));
    testCoords.add(new Coord(1, 1));
    testCoords.add(new Coord(2, 2));
    testCoords.add(new Coord(3, 3));
    testCoords.add(new Coord(4, 4));

    List<Coord> successHits = manual.reportDamage(testCoords);

    assertEquals(successHits.size(), 3);
    assertTrue(successHits.get(0).equals(new Coord(3, 3)));
    assertTrue(successHits.get(1).equals(new Coord(4, 4)));
    assertTrue(successHits.get(2).equals(new Coord(1, 1)));
  }

  /**
   * Tests getName
   */
  @Test
  public void testGetName() {
    this.setManual(new MockReader("Nam\n2 2 1 1\nNam Doan"));
    String name = this.manual.getName();
    assertEquals(name, "Nam Doan");
  }
}