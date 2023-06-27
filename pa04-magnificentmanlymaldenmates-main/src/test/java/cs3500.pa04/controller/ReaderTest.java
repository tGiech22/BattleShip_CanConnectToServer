package cs3500.pa04.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.input.Reader;
import cs3500.pa04.controller.input.ReaderInterface;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 * Test class for Reader
 */
class ReaderTest {
  /**
   * Tests read method
   */
  @Test
  public void testRead() {
    // Readable is only going to take the first input
    Readable readable = new StringReader("Hello!!!!\nWORLD!!!");

    ReaderInterface reader = new Reader(readable);
    assertEquals(reader.read(), "Hello!!!!");
    assertEquals(reader.read(), "");
  }
}