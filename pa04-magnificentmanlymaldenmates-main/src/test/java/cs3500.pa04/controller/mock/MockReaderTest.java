package cs3500.pa04.controller.mock;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.input.ReaderInterface;
import cs3500.pa04.controller.input.mock.MockReader;
import org.junit.jupiter.api.Test;

/**
 * Test class for MockReader
 */
class MockReaderTest {
  /**
   * Tests read method
   */
  @Test
  public void testRead() {
    String input = "Nam Doan\nOOD 3500\nSummer 2023\nLOL!!!!";
    ReaderInterface reader = new MockReader(input);
    assertEquals(reader.read(), "Nam Doan");
    assertEquals(reader.read(), "OOD 3500");
    assertEquals(reader.read(), "Summer 2023");
    assertEquals(reader.read(), "LOL!!!!");
    assertEquals(reader.read(), "");

  }
}