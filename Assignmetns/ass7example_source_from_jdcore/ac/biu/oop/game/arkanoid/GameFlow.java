package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.animation.AnimationRunner;
import ac.biu.oop.game.animation.EndScreen;
import ac.biu.oop.game.animation.HighScores;
import ac.biu.oop.game.animation.KeyPressStoppableAnimation;
import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.levels.LevelInformation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;













public class GameFlow
{
  private KeyboardSensor keyboardSensor;
  private DialogManager dialogManager;
  private AnimationRunner animationRunner;
  private int gameWidth;
  private int gameHeight;
  private Player player;
  private HighScoreTable highScoreTable;
  
  public GameFlow(AnimationRunner runner, KeyboardSensor ks, DialogManager dialogManager, Player player, HighScoreTable highScoreTable, int gameWidth, int gameHeight)
  {
    animationRunner = runner;
    keyboardSensor = ks;
    this.gameWidth = gameWidth;
    this.gameHeight = gameHeight;
    this.player = player;
    this.highScoreTable = highScoreTable;
    this.dialogManager = dialogManager;
  }
  
  public void runLevels(List<LevelInformation> levels)
  {
    player.reset();
    
    for (LevelInformation levelInfo : levels)
    {
      GameLevel level = new GameLevel(levelInfo, keyboardSensor, animationRunner, player.getScoreCounter(), player.getLivesCounter(), gameWidth, gameHeight);
      








      level.initialize();
      
      while ((level.getRemainingBlocks() > 0) && (player.getLivesCounter().getValue() > 0)) {
        level.playOneTurn();
        
        if (level.getRemainingBlocks() > 0) {
          player.getLivesCounter().decrease(1);
        }
      }
      
      if (player.getLivesCounter().getValue() == 0) {
        break;
      }
    }
    



    if (highScoreTable.isWorthy(player))
    {
      String name = dialogManager.showQuestionDialog("Enter Name", "What is your name?", "Anonymous");
      player.setName(name);
      
      highScoreTable.add(player);
      
      try
      {
        highScoreTable.save();
      } catch (IOException e) {
        System.err.println("Failed persisting the high scores table");
        e.printStackTrace(System.err);
      }
    }
    
    animationRunner.play(new KeyPressStoppableAnimation(new EndScreen(player.getScoreCounter(), player.getLivesCounter().getValue() != 0), keyboardSensor, "space"));
    









    animationRunner.play(new KeyPressStoppableAnimation(new HighScores(highScoreTable), keyboardSensor, "space"));
  }
}
