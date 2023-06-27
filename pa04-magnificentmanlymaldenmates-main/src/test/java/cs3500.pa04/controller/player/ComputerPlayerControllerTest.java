package cs3500.pa04.controller.player;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.controller.input.mock.MockReader;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.ShipType;
import cs3500.pa04.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ComputerPlayerController
 */
class ComputerPlayerControllerTest {
  private AbstPlayerController computerPlayer;

  /**
   * Initialization method to be rand before each test method
   */
  @BeforeEach
  public void setUp() {
    Appendable appendable = new StringBuilder();
    View view = new View(appendable);
    Random rand = new Random(115);
    this.computerPlayer =
        new ComputerPlayerController(new MockReader(""), 6, 6, view, rand);
  }

  /**
   * Test getShots
   */
  @Test
  void testGetShots() {
    this.computerPlayer.setup();
    List<Coord> generatedShots = computerPlayer.getShots(4);
    assertEquals(generatedShots.size(), 4);
    assertTrue(generatedShots.get(0).equals(new Coord(3, 4)));
    assertTrue(generatedShots.get(1).equals(new Coord(4, 1)));
    assertTrue(generatedShots.get(2).equals(new Coord(4, 0)));
    assertTrue(generatedShots.get(3).equals(new Coord(1, 4)));

    generatedShots = computerPlayer.getShots(7);
    assertTrue(generatedShots.get(0).equals(new Coord(3, 1)));
    assertTrue(generatedShots.get(1).equals(new Coord(5, 4)));
    assertTrue(generatedShots.get(2).equals(new Coord(5, 3)));
    assertTrue(generatedShots.get(3).equals(new Coord(1, 5)));
    assertTrue(generatedShots.get(4).equals(new Coord(0, 2)));
    assertTrue(generatedShots.get(5).equals(new Coord(4, 2)));
    assertTrue(generatedShots.get(6).equals(new Coord(2, 4)));
  }

  /**
   * Tests getName
   */
  @Test
  void testGetName() {
    // "Computer Player" is the only thing it returns
    assertEquals(this.computerPlayer.getName(), "nam-doan16");
  }

  /**
   * Tests getFleetSize()
   */
  @Test
  void getFleetSize() {
    HashMap<ShipType, Integer> fleet = this.computerPlayer.getFleetSize(10);
    assertEquals(fleet.keySet().size(), 4);
    assertEquals(fleet.get(ShipType.CARRIER), 2);
    assertEquals(fleet.get(ShipType.BATTLESHIP), 1);
    assertEquals(fleet.get(ShipType.DESTROYER), 4);
    assertEquals(fleet.get(ShipType.SUBMARINE), 3);
  }
}