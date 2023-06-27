package cs3500.pa04.controller.input;

import java.util.Scanner;

/**
 * Represents a Reader class
 */
public class Reader implements ReaderInterface {
  private final Readable readable;

  /**
   * Constructor for Reader
   *
   * @param readable Readable object
   */
  public Reader(Readable readable) {
    this.readable = readable;
  }


  /**
   * Reads the next line and returns it
   *
   * @return the next line in the input stream
   */
  public String read() {
    Scanner scanner = new Scanner(this.readable);
    if (scanner.hasNextLine()) {
      return scanner.nextLine();
    }
    return "";
  }


}
