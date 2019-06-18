package game.component;

import java.util.List;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.levels.LevelInformation;
import game.screens.EndScreen;
import geometry.Point;
import utillity.Counter;

/**
 * the GameFlow class adds support for moving from one level to the next.
 */
public class GameFlow {

   private KeyboardSensor keyboardSensor;
   private AnimationRunner animationRunner;
   private LivesIndicator lives;
   private Counter scoreCounter;

   /**
    * Default Constructor.
    *
    * @param animationRunner animation Runner to be used.
    * @param keyboardSensor  keyboard sensor to be used.
    */
   public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
      this.animationRunner = animationRunner;
      this.keyboardSensor = keyboardSensor;
      scoreCounter = new Counter();
      lives = new LivesIndicator(new Point(40, 15), 7);
   }

   /**
    *
    * @param levels Array of levels to be run.
    */
   public void runLevels(List<LevelInformation> levels) {
      // ...
      for (LevelInformation levelInfo : levels) {

         GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.scoreCounter,
               this.lives);
         level.initialize();
         do {
            level.playOneTurn();
         } while (hasLives() && level.hasBlocks());

         if (!hasLives()) {
            break;
         }

      }
      EndScreen e = new EndScreen(hasLives(), scoreCounter.getValue());
      animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "m", e));
   }

   /**
    * @return Wether or not the player has any remaining lives.
    */
   public boolean hasLives() {
      return lives.getLives() != 0;
   }

   /**
    *
    * @return the current score of the game.
    */
   public int getScore() {
      return scoreCounter.getValue();
   }
}