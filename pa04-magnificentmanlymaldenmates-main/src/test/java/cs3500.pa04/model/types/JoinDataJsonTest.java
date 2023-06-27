package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.json.data.JoinDataJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for JoinData
 */
public class JoinDataJsonTest {

  /**
   * testing JoinDataJson
   */

  @Test
  public void testJoinData() {
    JoinDataJson test = new JoinDataJson("Tony", GameMode.MULTI);
    JoinDataJson test2 = new JoinDataJson("Nam", GameMode.SINGLE);

    assertEquals(test.name(), "Tony");
    assertEquals(test2.name(), "Nam");
    assertEquals(test.gameType(), GameMode.MULTI);
    assertEquals(test2.gameType(), GameMode.SINGLE);
  }
}
