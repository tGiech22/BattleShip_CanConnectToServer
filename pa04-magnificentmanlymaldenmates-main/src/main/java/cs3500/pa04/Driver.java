package cs3500.pa04;

import cs3500.pa04.controller.BattleSalvoController;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.controller.input.Reader;
import cs3500.pa04.controller.player.AbstPlayerController;
import cs3500.pa04.controller.player.ComputerPlayerController;
import cs3500.pa04.view.View;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * Prevents instances of Driver from being made
   */
  private Driver() {}

  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {
    Appendable output = new PrintStream(System.out);
    Reader reader = new Reader(new InputStreamReader(System.in));

    Integer port = Validator.getSocket(args);
    if (Objects.isNull(port)) {
      BattleSalvoController battleSalvo = new BattleSalvoController(output, reader, new Random());
      battleSalvo.run();
    } else {
      runClient(args[0], port);
    }
  }

  /**
   * Runs the Battle Salvo client on the given server
   *
   * @param host host name
   * @param port host port number
   *
   * @throws IOException if the given server is unreachable
   */
  private static void runClient(String host, int port)
      throws IOException {
    Socket server = new Socket(host, port);

    AbstPlayerController player
        = new ComputerPlayerController(new Reader(new InputStreamReader(System.in)),
        6, 6, new View(System.out), new Random());
    ProxyController proxyController = new ProxyController(server, player);
    proxyController.run();
  }
}