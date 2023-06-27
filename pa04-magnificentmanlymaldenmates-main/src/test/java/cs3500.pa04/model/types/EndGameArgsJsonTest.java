package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.json.data.EndGameArgsJson;
import org.junit.jupiter.api.Test;

/**
 * Test class for EndGameArgsJson
 */
public class EndGameArgsJsonTest {

  /**
   * testing EndGameArgsJson
   */
  @Test
  public void testEndGameArgs() {
    EndGameArgsJson test1 = new EndGameArgsJson(GameResult.WIN, "You sunk all enemy ships.");
    EndGameArgsJson test2 = new EndGameArgsJson(GameResult.LOSE, "All ships sunk.");
    EndGameArgsJson test3 = new EndGameArgsJson(GameResult.DRAW, "Both fleets sunk.");

    assertEquals(test1.result(), GameResult.WIN);
    assertEquals(test2.result(), GameResult.LOSE);
    assertEquals(test3.result(), GameResult.DRAW);
    assertEquals(test1.reason(), "You sunk all enemy ships.");

  }
}
