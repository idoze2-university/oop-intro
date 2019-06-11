import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import game.animation.AnimationRunner;
import game.component.GameFlow;
import game.levels.LevelInformation;
import game.levels.Levels;

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
    GUI gui = new GUI("myGame", 800, 600);
    GameFlow game = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
    List<LevelInformation> levels = new ArrayList<LevelInformation>();
    for (String arg : args) {
      LevelInformation info = Levels.getLevel(Integer.parseInt(arg));
      if (info != null) {
        levels.add(info);
      }
    }

    game.runLevels(levels);
    gui.close();
  }
}
