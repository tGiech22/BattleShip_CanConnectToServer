package cs3500.pa04.controller;


import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.controller.input.ReaderInterface;
import cs3500.pa04.controller.input.mock.MockReader;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test for BattleSalvoController
 */
class BattleSalvoControllerTest {
  private String initialStart;

  /**
   * Initialization method to be ran before each test method
   */
  @BeforeEach
  public void setUp() {
    initialStart = "0 0\na a\n6 15\nNam Doan\n2 2 1 1\n";
  }

  /**
   * Testing a run where the player wins
   */
  @Test
  void testWinRun() {
    String shots = "0 1\n0 2\n0 3\n0 4\n"
        + "1 3\n1 4\n"
        + "2 1\n2 2\n2 3\n"
        + "5 0\n5 1\n5 2\n5 3\n5 4\n"
        + "8 1\n9 1\n10 1\n11 1\n"
        + "10 3\n11 3\n12 3\n13 3\n"
        + "14 3\n12 1\n5 5\n2 4\n1 5\n"
        + "10 5\n11 5\n12 5\n13 5\n14 5\n";

    Appendable output = new StringBuilder();
    ReaderInterface input = new MockReader(initialStart + shots);
    BattleSalvoController controller = new BattleSalvoController(output, input, new Random(5));
    controller.run();
    System.out.println(output);
    assertTrue(output.toString().contains("You win!"));
  }

  /**
   * Testing a run where the player loses because bad shots
   */
  @Test
  void testLostRun() {
    StringBuilder shots = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        shots.append(j).append(" ").append(i).append("\n");
      }
    }

    Appendable output = new StringBuilder();
    String initialStart = "0 0\na a\n6 6\nNam Doan\n2 2 1 1\n";
    ReaderInterface input = new MockReader(initialStart + shots);
    BattleSalvoController controller = new BattleSalvoController(output, input, new Random(7));
    controller.run();
    assertTrue(output.toString().contains("You lose!"));
  }


  /**
   * Testing a run where the player draws
   */
  @Test
  void testDrawRun() {
    StringBuilder shots = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        shots.append(i).append(" ").append(j).append("\n");
      }
    }

    Appendable output = new StringBuilder();
    ReaderInterface input = new MockReader("6 6\nNam Doan\n2 2 1 1\n" + shots);
    BattleSalvoController controller = new BattleSalvoController(output, input, new Random(10));
    controller.run();
    System.out.println(output);
    assertTrue(output.toString().contains("Draw!"));
  }

}