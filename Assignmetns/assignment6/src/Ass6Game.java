import game.component.GameLevel;

/**
 * The Ass5 implements an Arkanoid game using the Game class.
 *
 * @author zeiraid 322607177
 */
public class Ass6Game {
  /**
   * Start a game instance.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    GameLevel game = new GameLevel(800, 600);
    game.run();
  }
}
