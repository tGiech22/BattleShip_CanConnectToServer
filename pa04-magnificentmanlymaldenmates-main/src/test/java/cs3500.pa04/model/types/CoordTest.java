package cs3500.pa04.model.types;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Coord
 */
class CoordTest {
  private Coord coord;

  /**
   * Initialization method to be ran before every test method
   */
  @BeforeEach
  public void setUp() {
    this.coord = new Coord(5, 6);
  }

  /**
   * Test for getX
   */
  @Test
  void getX() {
    assertEquals(this.coord.getX(), 5);
  }

  /**
   * Test for getY
   */
  @Test
  void getY() {
    assertEquals(this.coord.getY(), 6);
  }

  /**
   * Test for equals
   */
  @Test
  void testEquals() {
    assertTrue(this.coord.equals(new Coord(5, 6)));
    assertFalse(this.coord.equals(new Coord(5, 5)));
    assertFalse(this.coord.equals(new Coord(4, 6)));
  }
}