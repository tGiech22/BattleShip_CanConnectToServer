package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa04.model.json.message.TakeShotsJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for TakeShotsJson
 */
public class TakeShotsJsonTest {
  /**
   * testing TakeShotsJson
   */
  @Test
  public void testTakeShotsJson() {
    TakeShotsJson takeShotsJson =
        new TakeShotsJson("take-shots", null);

    assertEquals(takeShotsJson.methodName(), "take-shots");
    assertNull(takeShotsJson.arguments());
  }
}
