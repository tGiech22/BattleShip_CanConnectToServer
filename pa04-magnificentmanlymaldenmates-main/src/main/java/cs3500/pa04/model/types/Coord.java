package cs3500.pa04.model.types;

/**
 * Represents a Coordinate on a board using xy values
 */
public class Coord {
  private final int coordX;
  private final int coordY;

  /**
   * Constructor for Coord
   *
   * @param coordX x-value of coordinate
   * @param coordY y-value of coordinate
   */
  public Coord(int coordX, int coordY) {
    this.coordX = coordX;
    this.coordY = coordY;
  }

  /**
   * getter method for x-value
   *
   * @return x-value of coordinate
   */
  public int getX() {
    return this.coordX;
  }

  /**
   * getter method for y-value
   *
   * @return y-value of coordinate
   */
  public int getY() {
    return this.coordY;
  }


  /**
   * Determines if the other Coord is the same as this Coord
   *
   * @param other other coordinate
   * @return whether the other coordinate is the same as this coordinate
   */
  public boolean equals(Coord other) {
    return this.coordX == other.coordX && this.coordY == other.coordY;
  }


}
