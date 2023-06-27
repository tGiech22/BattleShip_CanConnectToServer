package cs3500.pa04.view;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for View
 */
class ViewTest {
  private Appendable appendable;
  private View view;

  /**
   * Initialization method to be ran before every test method
   */
  @BeforeEach
  public void setUp() {
    this.appendable = new StringBuilder();
    this.view = new View(this.appendable);
  }

  /**
   * Tests printMessage in View
   */
  @Test
  public void testPrintMessage() {
    // testing initial state of appendable
    assertEquals(this.appendable.toString(), "");

    // writing
    view.printMessage("Test");

    // testing after state of appendable
    assertEquals(this.appendable.toString(), "Test\n");
  }
}