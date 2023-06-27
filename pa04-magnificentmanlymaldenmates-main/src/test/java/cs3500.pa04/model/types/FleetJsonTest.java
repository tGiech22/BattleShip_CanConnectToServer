package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.json.data.CoordJson;
import cs3500.pa04.model.json.data.FleetJson;
import cs3500.pa04.model.json.data.ShipJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for FleetJson
 */
public class FleetJsonTest {

  /**
   * testing FleetJson
   */
  @Test
  public void testFleetJson() {
    CoordJson coordJsonTest = new CoordJson(0, 0);
    CoordJson coordJsonTest2 = new CoordJson(0, 1);

    ShipJson shipJsonTest = new ShipJson(coordJsonTest, 3, Direction.HORIZONTAL);
    ShipJson shipJsonTest2 = new ShipJson(coordJsonTest2, 4, Direction.VERTICAL);
    ShipJson[] fleet = new ShipJson[2];
    fleet[0] = shipJsonTest;
    fleet[1] = shipJsonTest2;

    FleetJson test = new FleetJson(fleet);

    assertEquals(test.fleet().length, 2);
    assertEquals(test.fleet()[0].length(), 3);
    assertEquals(test.fleet()[1].length(), 4);
    assertEquals(test.fleet()[0].direction(), Direction.HORIZONTAL);
    assertEquals(test.fleet()[1].direction(), Direction.VERTICAL);
    assertEquals(test.fleet()[0].shipCoord().x(), 0);
    assertEquals(test.fleet()[1].shipCoord().x(), 0);
  }
}
