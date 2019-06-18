import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.component.GameFlow;
import game.levels.LevelInformation;
import game.screens.HighScoresAnimation;
import game.screens.MenuAnimation;
import io.levelfactory.LevelSpecificationReader;
import io.levelsets.LevelSet;
import io.levelsets.LevelSetsReader;
import io.score.HighScoresTable;
import io.score.ScoreInfo;
import ui.Menu;
import ui.Task;

/**
 * The Ass7 implements an Arkanoid game using the Game class.
 *
 * @author zeiraid 322607177
 */
public class Ass7Game {
  /**
   * Start a game instance.
   *
   * @param args command line arguments.
   */
  public static LevelSet levelSet;

  public static void main(String[] args) throws IOException {
    GUI gui = new GUI("myGame", 800, 600);
    AnimationRunner animationRunner = new AnimationRunner(120, gui);
    HighScoresTable scores = HighScoresTable.load("db");
    Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(animationRunner, gui.getKeyboardSensor());

    Task<Void> showHiScores = new Task<Void>() {
      @Override
      public Void run() {
        KeyPressStoppableAnimation hs = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "space",
            new HighScoresAnimation(scores));
        animationRunner.run(hs);
        return null;
      }
    };

    InputStream setsFile = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
    List<LevelSet> levelSets = LevelSetsReader.fromReader(new InputStreamReader(setsFile));
    Task<Void> runGame = new Task<Void>() {
      @Override
      public Void run() {
        GameFlow game = new GameFlow(animationRunner, gui.getKeyboardSensor());
        List<LevelInformation> levels = levelSet.getLevels();
        game.runLevels(levels);
        int score = game.getScore();
        if (scores.getRank(score) <= scores.size()) {
          DialogManager dialog = gui.getDialogManager();
          String name = dialog.showQuestionDialog("Name", "What is your name?", "");
          ScoreInfo data = new ScoreInfo(name, score);
          scores.add(data);
        }
        try {
          scores.save("db");
        } catch (Exception e) {
          System.out.println("Can't save.... for some reason. " + e.toString());
        }
        showHiScores.run();
        return null;
      }
    };
    Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(animationRunner, gui.getKeyboardSensor());
    for (LevelSet l : levelSets) {
      subMenu.addSelection(l.getName(), l.getDesc(), new Task<Void>() {
        @Override
        public Void run() {
          levelSet = l;
          return runGame.run();
        }
      });
    }
    menu.addSubMenu("s", "Play Game", subMenu);
    menu.addSelection("h", "Hi scores", showHiScores);

    Task<Void> quit = new Task<Void>() {
      @Override
      public Void run() {
        System.exit(0);
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
