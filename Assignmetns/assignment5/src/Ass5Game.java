import game.component.Game;

/**
 * The Ass5 implements an Arkanoid game using the Game class.
 *
 * @author zeiraid 322607177
 */
public class Ass5Game {
  /**
   * Start a game instance.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Game game = new Game(1400, 800);
    game.initGame();
    game.run();
  }
}
