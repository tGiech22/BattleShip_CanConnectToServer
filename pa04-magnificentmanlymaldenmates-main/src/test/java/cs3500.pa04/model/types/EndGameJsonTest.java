package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cs3500.pa04.model.json.message.EndGameJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for EndGameJson
 */
public class EndGameJsonTest {
  /**
   * testing EndGameJson
   */
  @Test
  public void testSuccessfulHitsJson() {
    EndGameJson endGameJson =
        new EndGameJson("end-game", null);

    assertEquals(endGameJson.methodName(), "end-game");
    assertNull(endGameJson.arguments());
  }
}
