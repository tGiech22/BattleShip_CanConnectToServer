package cs3500.pa04.model.types;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for ShipType
 */
class ShipTypeTest {
  /**
   * Testing values of ShipType
   */
  @Test
  public void testValues() {
    ShipType[] enums = ShipType.values();
    assertEquals(enums[0], ShipType.CARRIER);
    assertEquals(enums[1], ShipType.BATTLESHIP);
    assertEquals(enums[2], ShipType.DESTROYER);
    assertEquals(enums[3], ShipType.SUBMARINE);
    int[] valuesSorted = {6, 5, 4, 3};
    for (int i = 0; i < enums.length; i++) {
      assertEquals(enums[i].size, valuesSorted[i]);
    }
  }

  /**
   * Testing valueOf for ShipType
   */
  @Test
  public void testValueOf() {
    assertEquals(ShipType.valueOf("CARRIER"), ShipType.CARRIER);
    assertEquals(ShipType.valueOf("BATTLESHIP"), ShipType.BATTLESHIP);
    assertEquals(ShipType.valueOf("DESTROYER"), ShipType.DESTROYER);
    assertEquals(ShipType.valueOf("SUBMARINE"), ShipType.SUBMARINE);
  }
}