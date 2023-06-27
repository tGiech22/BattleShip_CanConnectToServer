package cs3500.pa04.model.players;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.model.pieces.Ship;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.GameResult;
import cs3500.pa04.model.types.ShipType;
import cs3500.pa04.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for AbstPlayer
 */
class AbstPlayerTest {
  private AbstPlayer player;

  /**
   * Initialization method to be run before each test method
   */
  @BeforeEach
  public void setUp() {
    Appendable appendable = new StringBuilder();
    this.player = new ManualPlayer("Nam Doan", 6, 6, new Random(6),
        new View(appendable));
    HashMap<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 2);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 1);
    this.player.setup(6, 6, specs);
  }

  /**
   * Tests setName
   */
  @Test
  public void testSetName() {
    // testing initial name
    assertEquals(player.name(), "Nam Doan");

    // changing name
    player.setName("John Doe");

    // testing after state
    assertEquals(player.name(), "John Doe");
  }

  /**
   * Tests name()
   */
  @Test
  public void testName() {
    assertEquals(this.player.name(), "Nam Doan");
  }

  /**
   * Tests setup()
   */
  @Test
  public void testSetup() {
    // using something other than the before each setup AbstPlayer to show a different specification
    HashMap<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 2);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 1);
    specs.put(ShipType.SUBMARINE, 1);
    AbstPlayer tempPlayer = new ManualPlayer("Test", 6, 6, new Random(6),
        new View(new StringBuilder()));
    List<Ship> ships = tempPlayer.setup(6, 6, specs);
    assertEquals(ships.size(), 5);
  }

  /**
   * Tests both takeShots and setShots()
   */
  @Test
  public void testTakeAndSetShots() {
    // initial state
    assertNull(this.player.takeShots());

    // setting shots
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(3, 3));
    this.player.setShots(coords);

    // checking after state
    assertEquals(this.player.takeShots(), coords);
  }

  /**
   * Tests reportDamage
   */
  @Test
  void reportDamage() {
    // representation of opponent
    assertTrue(this.player.stringBoard().contains(
        """
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 0 0
            1   0 0 0 0 0 0
            2   0 0 0 0 0 0
            3   0 0 0 0 0 0
            4   0 0 0 0 0 0
            5   0 0 0 0 0 0"""));
    // (3,5) - MISS, (4,5) - HIT, (5,5) - HIT
    Coord miss = new Coord(3, 5);
    Coord hit1 = new Coord(4, 5);
    Coord hit2 = new Coord(5, 5);
    List<Coord> hit = this.player.reportDamage(new ArrayList<>(Arrays.asList(miss, hit1, hit2)));
    assertEquals(hit.size(), 2);
    assertTrue(hit.get(0).equals(hit1));
    assertTrue(hit.get(1).equals(hit2));
    assertTrue(this.player.stringBoard().contains(
        """
            Nam Doan's board:
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 C 0
            1   D 0 B 0 C B
            2   D D B S C B
            3   D D B S C B
            4   D D B S C B
            5   0 D B M H H"""));
  }

  /**
   * tests successfulHits
   */
  @Test
  void successfulHits() {
    // initial representation of the opponent board (this works because our board has ships)
    assertTrue(this.player.stringBoard().contains(
        """
            Nam Doan's board:
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 C 0
            1   D 0 B 0 C B
            2   D D B S C B
            3   D D B S C B
            4   D D B S C B
            5   0 D B 0 C B"""));

    // setting shots
    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(3, 3));
    coords.add(new Coord(1, 3));
    this.player.setShots(coords);

    // changing state (1, 3) is a successful hit supposedly
    this.player.successfulHits(new ArrayList<>(List.of(new Coord(1, 3))));

    assertTrue(this.player.stringBoard().contains(
        """
            Nam Doan's board:
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 C 0
            1   D 0 B 0 C B
            2   D D B S C B
            3   D D B S C B
            4   D D B S C B
            5   0 D B 0 C B"""));
  }

  /**
   * Tests if endGame runs
   */
  @Test
  void endGame() {
    // end game doesn't do anything until Assignment 4
    this.player.endGame(GameResult.DRAW, "NULL");
  }

  /**
   * Tests stringBoard
   */
  @Test
  void stringBoard() {
    assertEquals(this.player.stringBoard(),
        """
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 0 0
            1   0 0 0 0 0 0
            2   0 0 0 0 0 0
            3   0 0 0 0 0 0
            4   0 0 0 0 0 0
            5   0 0 0 0 0 0

            Nam Doan's board:
              X 0 1 2 3 4 5
            Y
            0   0 0 0 0 C 0
            1   D 0 B 0 C B
            2   D D B S C B
            3   D D B S C B
            4   D D B S C B
            5   0 D B 0 C B""");
  }
}