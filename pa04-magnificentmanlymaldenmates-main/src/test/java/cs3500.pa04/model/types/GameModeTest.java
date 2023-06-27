package cs3500.pa04.model.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for GameMode
 */
public class GameModeTest {

  /**
   * Testing values of GameMode enum
   */
  @Test
  public void testGameMode() {
    GameMode[] enums = GameMode.values();
    assertEquals(enums[0], GameMode.SINGLE);
    assertEquals(enums[1], GameMode.MULTI);
  }

  /**
   * Testing valueOf in GameMode enum
   */
  @Test
  public void testValueOf() {
    assertEquals(GameMode.valueOf("SINGLE"), GameMode.SINGLE);
    assertEquals(GameMode.valueOf("MULTI"), GameMode.MULTI);
  }
}
