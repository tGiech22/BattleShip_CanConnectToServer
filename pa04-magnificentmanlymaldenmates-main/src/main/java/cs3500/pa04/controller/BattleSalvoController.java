package cs3500.pa04.controller;

import cs3500.pa04.Validator;
import cs3500.pa04.controller.input.ReaderInterface;
import cs3500.pa04.controller.player.AbstPlayerController;
import cs3500.pa04.controller.player.ComputerPlayerController;
import cs3500.pa04.controller.player.ManualPlayerController;
import cs3500.pa04.model.pieces.Ship;
import cs3500.pa04.model.types.Coord;
import cs3500.pa04.model.types.GameResult;
import cs3500.pa04.view.View;
import java.util.List;
import java.util.Random;

/**
 * Represents the Controller for the BattleSalvo game
 */
public class BattleSalvoController {
  private final View view;
  private final Random rand;
  private final ReaderInterface reader;

  /**
   * Constructor for BattleSalvoController
   *
   * @param output Appendable object
   * @param input ReaderInterface object
   * @param rand Random object
   */
  public BattleSalvoController(Appendable output, ReaderInterface input, Random rand) {
    this.rand = rand;
    this.view = new View(output);
    this.reader = input;
  }

  /**
   * Runs the BattleSalvo game and controls its states
   */
  public void run() {
    view.printMessage("Welcome to BattleSalvo!");
    int[] dimensions = this.getDimensions();
    int height = dimensions[0];
    int width = dimensions[1];
    AbstPlayerController player =
        new ManualPlayerController(this.reader, this.view, height, width, rand);
    AbstPlayerController opponent =
          new ComputerPlayerController(this.reader, height, width, this.view, rand);
    List<Ship> playerShips = player.setup();
    List<Ship> opponentShips = opponent.setup();
    do {
      view.printMessage(opponent.getName() + "'s Board Data:");
      player.printBoard();
      List<Coord> playerShots = player.getShots(playerShips.size());
      List<Coord> opponentShots = opponent.getShots(opponentShips.size());
      player.successfulHits(opponent.reportDamage(playerShots));
      opponent.successfulHits(player.reportDamage(opponentShots));
    } while (playerShips.size() != 0 && opponentShips.size() != 0);
    if (playerShips.size() == 0 && opponentShips.size() == 0) {
      player.endGame(GameResult.DRAW, "Both players' ships were sunk!");
    } else if (playerShips.size() == 0) {
      player.endGame(GameResult.LOSE, "The opponent sunk all your ships!");
    } else {
      player.endGame(GameResult.WIN, "You sunk all the opponents ships!");
    }
  }

  /**
   * Takes in user input to get dimensions of the board
   *
   * @return int array of length 2 describing the dimensions of the board
   */
  private int[] getDimensions() {
    int[] dimensions = null;
    do {
      view.printMessage("Height and width has to be between [6, 15] inclusive!");
      view.printMessage("Please enter a valid height and width: ");
      String input = reader.read();
      try {
        dimensions = Validator.allNumArray(input.split(" "), 2);
        if (!Validator.isInBound(dimensions, 16, 16, 6)) {
          view.printMessage("Invalid numerical input. Please try again.");
        }
      } catch (IllegalArgumentException e) {
        view.printMessage("Invalid Input. Please try again.");
      }
    } while (!Validator.isInBound(dimensions, 16, 16, 6));
    return dimensions;
  }
}
