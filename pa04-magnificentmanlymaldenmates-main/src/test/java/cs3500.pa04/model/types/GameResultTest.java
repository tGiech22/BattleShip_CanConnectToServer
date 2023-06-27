package cs3500.pa04.model.types;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for GameResult
 */
class GameResultTest {

  /**
   * Testing values of GameResult enum
   */
  @Test
  public void testValues() {
    GameResult[] enums = GameResult.values();
    assertEquals(enums[0], GameResult.WIN);
    assertEquals(enums[1], GameResult.LOSE);
    assertEquals(enums[2], GameResult.DRAW);
  }

  /**
   * Testing valueOf in GameResult enum
   */
  @Test
  public void testValueOf() {
    assertEquals(GameResult.valueOf("WIN"), GameResult.WIN);
    assertEquals(GameResult.valueOf("LOSE"), GameResult.LOSE);
    assertEquals(GameResult.valueOf("DRAW"), GameResult.DRAW);
  }
}