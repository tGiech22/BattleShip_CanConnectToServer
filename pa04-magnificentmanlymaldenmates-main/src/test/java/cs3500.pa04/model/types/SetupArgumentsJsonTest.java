package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.json.data.FleetSpecJson;
import cs3500.pa04.model.json.data.SetupArgumentsJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for SetupArgumentsJson
 */
public class SetupArgumentsJsonTest {

  /**
   * testing SetupArgumentsJson
   */

  @Test
  public void testSetupArgumentsJson() {
    FleetSpecJson fleetSpecs = new FleetSpecJson(2, 1, 3, 1);
    SetupArgumentsJson test = new SetupArgumentsJson(8, 10, fleetSpecs);

    assertEquals(test.fleetSpec().carrier(), 2);
    assertEquals(test.fleetSpec().battleship(), 1);
    assertEquals(test.fleetSpec().destroyer(), 3);
    assertEquals(test.fleetSpec().submarine(), 1);
    assertEquals(test.width(), 8);
    assertEquals(test.height(), 10);
  }
}
