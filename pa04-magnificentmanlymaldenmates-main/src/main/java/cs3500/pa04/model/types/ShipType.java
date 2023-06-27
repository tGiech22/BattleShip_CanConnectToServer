package cs3500.pa04.model.types;

/**
 * Represents a enumeration for Ship types and their sizes
 */
public enum ShipType {
  /**
   * represents a carrier ship with the size of 6
   */
  CARRIER(6),

  /**
   * represents a battleship with the size of 5
   */
  BATTLESHIP(5),

  /**
   * represents a destroyer ship with the size of 4
   */
  DESTROYER(4),

  /**
   * represents a submarine with the size of 3
   */
  SUBMARINE(3);

  /**
   * represents the size for each ship type
   */
  public final int size;

  ShipType(int size) {
    this.size = size;
  }
}
