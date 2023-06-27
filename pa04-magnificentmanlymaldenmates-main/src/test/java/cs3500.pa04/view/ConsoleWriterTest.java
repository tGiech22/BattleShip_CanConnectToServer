package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa04.view.mock.MockAppendable;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a class testing ConsoleWriter for successful and failure on writing
 */
class ConsoleWriterTest {
  private Appendable appendable;
  private ConsoleWriter writer;
  private static final String testString = "Test";

  /**
   * An initialization method to be run before each test method
   */
  @BeforeEach
  public void setUp() {
    this.appendable = new StringBuilder();
    this.writer = new ConsoleWriter(this.appendable);
  }

  /**
   * Testing on the success portion of ConsoleWriter
   */
  @Test
  public void testSuccess() {
    // checking empty string
    assertEquals(this.appendable.toString(), "");

    // writing the test string to it
    this.writer.write(testString);

    // checking "Test" is in the StringBuilder
    assertEquals(this.appendable.toString(), "Test");
  }

  /**
   * Testing when ConsoleWriter fails
   */
  @Test
  public void testFail() {
    Appendable mockAppendable = new MockAppendable();
    this.writer = new ConsoleWriter(mockAppendable);
    Exception testException = assertThrows(RuntimeException.class, () ->
        this.writer.write(testString), "Mock throwing an error");

    // testing the other two variations of append
    Exception testAppendableMethod2 = assertThrows(IOException.class, () ->
        mockAppendable.append("hello", 0, 2), "Mock throwing an error");
    Exception testAppendableMethod3 = assertThrows(IOException.class, () ->
        mockAppendable.append('z'), "Mock throwing an error");

    assertEquals(testException.getMessage(), "Mock throwing an error");
    assertEquals(testAppendableMethod2.getMessage(), "Mock throwing an error");
    assertEquals(testAppendableMethod3.getMessage(), "Mock throwing an error");
  }

}