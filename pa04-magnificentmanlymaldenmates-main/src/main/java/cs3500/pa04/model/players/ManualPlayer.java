package cs3500.pa04.model.players;

import cs3500.pa04.view.View;
import java.util.Random;

/**
 * Represents a Manual Player
 */
public class ManualPlayer extends AbstPlayer {

  /**
   * Constructor for ManualPlayer
   *
   * @param name Name of player
   * @param height height of board
   * @param width width of board
   * @param rand Random object
   */
  public ManualPlayer(String name, int height, int width, Random rand, View view) {
    super(name, height, width, rand, view);
  }
}
