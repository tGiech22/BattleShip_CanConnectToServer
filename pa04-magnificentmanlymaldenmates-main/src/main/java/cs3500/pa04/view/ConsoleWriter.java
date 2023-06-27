package cs3500.pa04.view;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a writer class responsible for writing output to the console to the user
 */
public class ConsoleWriter {
  private final Appendable appendable;

  /**
   * Constructor for ConsoleWriter
   *
   * @param appendable Appendable object
   */
  public ConsoleWriter(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * Writes the given string value to the appendable object
   *
   * @param value - string to be appended/added
   */
  public void write(String value) {
    try {
      appendable.append(value);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
