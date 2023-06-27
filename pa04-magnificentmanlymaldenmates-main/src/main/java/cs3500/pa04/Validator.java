package cs3500.pa04;

/**
 * Represents a utility class Validator, responsible for validating parameters
 */
public class Validator {

  private Validator() {}

  /**
   * Determines if the given inputArray is expected length and all elements are numerical
   *
   * @param inputArray String array
   * @param expectedLength expected length of the given array
   * @return a parsed integer array of the given String array
   */
  public static int[] allNumArray(String[] inputArray, int expectedLength) {
    if (inputArray == null || inputArray.length != expectedLength) {
      throw new IllegalArgumentException("Invalid Arguments!");
    }

    int[] tempStorage = new int[inputArray.length];
    for (int i = 0; i < inputArray.length; i++) {
      String element = inputArray[i];
      try {
        tempStorage[i] = Integer.parseInt(element);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Non-numerical number input!");
      }
      if (tempStorage[i] < 0) {
        throw new IllegalArgumentException("No negative numbers");
      }
    }
    return tempStorage;
  }

  /**
   * Determines if the given number array (length of 2) is in bounds of the given
   * height, width, and lowerBound
   *
   * @param numbers array of numbers (used only in instances of size 2)
   * @param height height integer
   * @param width width integer
   * @param lowerBound lower bound integer
   * @return whether the given numbers in the given array are in bounds
   */
  public static boolean isInBound(int[] numbers, int height, int width, int lowerBound) {
    if (numbers == null) {
      return false;
    }
    if (numbers.length == 2) {
      boolean firstInBound = numbers[0] < width && numbers[0] >= lowerBound;
      boolean secondInBound = numbers[1] < height && numbers[1] >= lowerBound;
      return firstInBound && secondInBound;
    }
    return true;
  }

  /**
   * Determines if the given arguments contains only a valid host name and a valid port number,
   * if so, return the numeric representation of the port number
   * otherwise return null
   *
   * @param args array of arguments given by the user
   * @return numeric representation of the port or null
   */
  public static Integer getSocket(String[] args) {
    if (!(args.length == 0 || args.length == 2)) {
      throw new IllegalArgumentException("Invalid amount of arguments!");
    }
    // 0 args = singleplayer, 2 args = multiplayer
    if (args.length == 2) {
      int port;
      try {
        port = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid port number!");
      }
      return port;
    }
    return null;
  }
}
