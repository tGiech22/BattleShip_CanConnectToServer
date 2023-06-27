package cs3500.pa04.controller.input.mock;

import cs3500.pa04.controller.input.ReaderInterface;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Represents a Mock Reader class
 */
public class MockReader implements ReaderInterface {
  private final Scanner scanner;

  /**
   * Constructor for MockReader
   *
   * @param str String to be parsed
   */
  public MockReader(String str) {
    scanner = new Scanner(new StringReader(str));
  }

  /**
   * Returns every next line of the constructor's given string
   *
   * @return iteration of line from constructor's given string
   */
  public String read() {
    if (scanner.hasNextLine()) {
      return scanner.nextLine();
    }
    return "";
  }



}
