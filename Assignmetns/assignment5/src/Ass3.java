import game.Game;

/**
 * The Ass3 implements an Arkanoid game using the Game class.
 *
 * @author zeiraid 322607177
 */
public class Ass3 {
  /**
   * Start a game instance.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Game game = new Game(1400, 800);
    game.initialize();
    game.run();
  }
}
