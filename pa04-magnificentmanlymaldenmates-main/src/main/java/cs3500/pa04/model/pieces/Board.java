package cs3500.pa04.model.pieces;

import cs3500.pa04.model.types.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a board for BattleSalvo
 */
public class Board {
  private final ArrayList<Ship> ships;
  private final char[][] board;

  /**
   * Constructor for Board
   *
   * @param height height of board
   * @param width width of board
   * @param ships ships on board
   */
  public Board(int height, int width, ArrayList<Ship> ships) {
    this.ships = ships;
    this.board = new char[height][width];
    this.fillBoard();
  }

  /**
   * Fills the 2d array of characters with representation of ships and empty spaces
   */
  private void fillBoard() {
    for (char[] row : board) {
      Arrays.fill(row, '0');
    }

    for (Ship ship : ships) {
      ship.fillBoard(board);
    }
  }

  /**
   * reports the given opponent's list of hits to this board and returns a list of valid hits
   *
   * @param allOpponentHits list of coordinates the opponent has targetted
   * @return valid hits on this board (hits on ships)
   */
  public ArrayList<Coord> reportShipDamages(List<Coord> allOpponentHits) {
    ArrayList<Coord> validHits = new ArrayList<>();
    Iterator<Ship> iter = ships.iterator();
    while (iter.hasNext()) {
      Ship ship = iter.next();
      ArrayList<Coord> validOpponentShots = ship.reportShipDamage(allOpponentHits);
      validHits.addAll(validOpponentShots);
      if (ship.isSunk()) {
        iter.remove();
      }
    }
    this.updateShipDamages(allOpponentHits, validHits);
    return validHits;
  }

  /**
   * updates this board with all hits and valid hits ('M' for miss, 'H' for hit)
   *
   * @param allHits All hits made
   * @param validHits All valid hits
   */
  public void updateShipDamages(List<Coord> allHits, List<Coord> validHits) {
    // placing all hits as misses, then overriding them with the valid hits
    for (Coord missHit : allHits) {
      this.board[missHit.getY()][missHit.getX()] = 'M';
    }

    for (Coord validHit : validHits) {
      this.board[validHit.getY()][validHit.getX()] = 'H';
    }
  }

  /**
   * Returns a string representing this board with an X and Y axis
   *
   * @return string representation of this board
   */
  public String toString() {
    StringBuilder temp = new StringBuilder("  X");
    if (this.board.length >= 10) {
      temp.insert(0, " ");
    }
    for (int i = 0; i < board[0].length; i++) {
      temp.append(" ").append(i);
    }
    temp.append("\nY");

    for (int i = 0; i < this.board.length; i++) {
      temp.append("\n").append(i).append("  ");
      if (i < 10 && this.board.length >= 10) {
        temp.append(" ");
      }
      for (int j = 0; j < this.board[i].length; j++)  {
        if (j >= 10) {
          temp.append(" ");
        }
        temp.append(" ").append(this.board[i][j]);
      }
    }

    return temp.toString();
  }
}
