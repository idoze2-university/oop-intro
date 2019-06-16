import java.io.File;
import java.util.ArrayList;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.component.GameFlow;
import game.levels.LevelInformation;
import game.levels.Levels;
import game.screens.HighScoresAnimation;
import game.screens.MenuAnimation;
import io.HighScoresTable;
import io.ScoreInfo;
import ui.Menu;
import ui.Task;

/**
 * The Ass6 implements an Arkanoid game using the Game class.
 *
 * @author zeiraid 322607177
 */
public class Ass7Game {
  /**
   * Start a game instance.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("myGame", 800, 600);
    AnimationRunner animationRunner = new AnimationRunner(gui);
    HighScoresTable scores = new HighScoresTable(5);
    File table = new File(ClassLoader.getSystemClassLoader().getResource("test.txt").getFile());
    try {
      scores.load(table);
    } catch (Exception e) {
      System.out.println(String.format("Couldn't load Hs table from '%s'\nBecause: '%s'", table, e));
    }

    Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor());

    Task<Void> runGame = new Task<Void>() {

      @Override
      public Void run() {
        GameFlow game = new GameFlow(animationRunner, gui.getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        for (String arg : args) {
          LevelInformation info = Levels.getLevel(Integer.parseInt(arg));
          if (info != null) {
            levels.add(info);
          }
        }
        game.runLevels(levels);
        int score = game.getScore();
        if (scores.getRank(score) <= scores.size()) {
          DialogManager dialog = gui.getDialogManager();
          String name = dialog.showQuestionDialog("Name", "What is your name?", "");
          ScoreInfo data = new ScoreInfo(name, score);
          scores.add(data);
        }
        try {
          scores.save(table);
        } catch (Exception e) {
          System.out.println(String.format("Couldn't save Hs table from '%s'\nBecause: '%s'", table, e));
        }
        return null;
      }
    };
    menu.addSelection("s", "Play", runGame);

    Task<Void> showHiScores = new Task<Void>() {
      @Override
      public Void run() {
        KeyPressStoppableAnimation hs = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space",
            new HighScoresAnimation(scores));
        animationRunner.run(hs);
        return null;
      }
    };
    menu.addSelection("h", "Hi scores", showHiScores);

    Task<Void> quit = new Task<Void>(){
      @Override
      public Void run() {
        gui.close();
        return null;
      }
    };
    menu.addSelection("q", "Quit", quit);

    while (true) {
      animationRunner.run(menu);
      // wait for user selection
      Task<Void> task = menu.getStatus();
      task.run();
    }
  }
}
