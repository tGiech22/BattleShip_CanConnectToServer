package cs3500.pa04.model.json.mock;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.pieces.Board;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for MockJson
 */
class MockJsonTest {
  /**
   * Testing initialization of the board field
   */
  @Test
  public void testBoard() {
    Board board = new Board(5, 5, new ArrayList<>());
    MockJson testJson = new MockJson(board);

    assertEquals(testJson.board(), board);
  }
}