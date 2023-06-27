package cs3500.pa04.view;

/**
 * Represents a View class, responsible for outputting Strings to the user
 */
public class View {
  private final ConsoleWriter writer;

  /**
   * Constructor for View
   *
   * @param appendable Appendable object
   */
  public View(Appendable appendable) {
    this.writer = new ConsoleWriter(appendable);
  }

  /**
   * Prints the given String to the user
   *
   * @param output String to be outputted
   */
  public void printMessage(String output) {
    writer.write(output + "\n");
  }
}
