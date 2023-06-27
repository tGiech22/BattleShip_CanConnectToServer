package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Direction
 */
public class DirectionTest {

  /**
   * Testing valueOf in Direction enum
   */
  @Test
  public void testGameMode() {
    Direction[] enums = Direction.values();
    assertEquals(enums[0], Direction.VERTICAL);
    assertEquals(enums[1], Direction.HORIZONTAL);
  }

  /**
   * Testing valueOf in Direction enum
   */
  @Test
  public void testValueOf() {
    assertEquals(Direction.valueOf("VERTICAL"), Direction.VERTICAL);
    assertEquals(Direction.valueOf("HORIZONTAL"), Direction.HORIZONTAL);
  }
}
