package ac.biu.oop.game;

import ac.biu.oop.game.animation.AnimationRunner;
import ac.biu.oop.game.animation.HighScores;
import ac.biu.oop.game.animation.KeyPressStoppableAnimation;
import ac.biu.oop.game.arkanoid.GameFlow;
import ac.biu.oop.game.arkanoid.HighScoreTable;
import ac.biu.oop.game.arkanoid.Player;
import ac.biu.oop.game.io.DynamicLevelInformation;
import ac.biu.oop.game.levels.LevelInformation;
import ac.biu.oop.game.menu.LevelSets;
import ac.biu.oop.game.menu.LevelSets.LevelSet;
import ac.biu.oop.game.menu.Menu;
import ac.biu.oop.game.menu.MenuImpl;
import ac.biu.oop.game.menu.Task;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;


public class Main
{
  private static final int FPS = 60;
  private static final int GAME_WIDTH = 800;
  private static final int GAME_HEIGHT = 600;
  
  public Main() {}
  
  public static void main(String[] args)
  {
    String levelSetsPath = "level_sets.txt";
    if (args.length > 0) {
      levelSetsPath = args[0];
    }
    
    String gameTitle = "Arkanoid";
    

    HighScoreTable h = null;
    try {
      h = HighScoreTable.load();
    } catch (IOException e) {
      System.err.println("Failed loading high score table");
      e.printStackTrace(System.err);
      return;
    }
    
    final HighScoreTable highScoreTable = h;
    

    GUI gui = new GUI(gameTitle, 800, 600);
    final KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
    final DialogManager dialogManager = gui.getDialogManager();
    AnimationRunner runner = new AnimationRunner(gui, 60);
    
    LevelSets levelSets = null;
    try
    {
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetsPath);
      levelSets = LevelSets.fromReader(new InputStreamReader(is));
    } catch (IOException e) {
      throw new RuntimeException("Failed loading level sets");
    }
    
    Menu<Task<Void>> levelSetsMenu = new MenuImpl("Level Sets", runner, keyboardSensor);
    
    for (final LevelSets.LevelSet levelSet : levelSets.getLevelSetList())
    {
      levelSetsMenu.addSelection(levelSet.getKey(), levelSet.getMessage(), new Task()
      {
        public Void run() {
          GameFlow gameFlow = new GameFlow(val$runner, keyboardSensor, dialogManager, new Player(7), highScoreTable, 800, 600);
          








          List<LevelInformation> levels = null;
          try
          {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSet.getLevelDefinitionPath());
            levels = DynamicLevelInformation.fromReader(new InputStreamReader(is));
          } catch (IOException e) {
            throw new RuntimeException("Failed loading levels:" + levelSet.getLevelDefinitionPath());
          }
          
          gameFlow.runLevels(levels);
          
          return null;
        }
      });
    }
    


    Menu<Task<Void>> mainMenu = new MenuImpl("Arkanoid", runner, keyboardSensor);
    
    mainMenu.addSubMenu("s", "Start Game", levelSetsMenu);
    
    mainMenu.addSelection("h", "High Scores", new Task()
    {
      public Void run() {
        val$runner.play(new KeyPressStoppableAnimation(new HighScores(highScoreTable), keyboardSensor, "space"));
        






        return null;
      }
      
    });
    mainMenu.addSelection("e", "Exit", new Task()
    {
      public Void run() {
        System.exit(0);
        return null;
      }
    });
    
    for (;;)
    {
      runner.play(mainMenu);
      Task<Void> task = (Task)mainMenu.getStatus();
      

      task.run();
      
      mainMenu.reset();
    }
  }
}
