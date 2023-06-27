package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa04.model.json.message.SuccessfulHitsJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for SuccessfulHitsJson
 */
public class SuccessfulHitsJsonTest {
  /**
   * testing SuccessfulHitsJson
   */
  @Test
  public void testSuccessfulHitsJson() {
    SuccessfulHitsJson successfulHitsJson =
        new SuccessfulHitsJson("successful-hits", null);

    assertEquals(successfulHitsJson.methodName(), "successful-hits");
    assertNull(successfulHitsJson.arguments());
  }
}
