package game.levels;

import geometry.Velocity;
import java.util.List;
import game.component.Block;
import game.component.Sprite;

/**
 * The LevelInformation interface provides a protocol for implementing a Level
 * in the game.
 */
public interface LevelInformation {
  /**
   * @return number of balls.
   */
  int numberOfBalls();

  /**
   * @return The initial velocity of each ball Note that
   *         initialBallVelocities().size() == numberOfBalls()
   */
  List<Velocity> initialBallVelocities();

  /**
   *
   * @return the speed of the paddle.
   */
  int paddleSpeed();

  /**
   *
   * @return the with of the paddle.
   */
  int paddleWidth();

  /**
   * @return the level name will be displayed at the top of the screen.
   */
  String levelName();

  /**
   * @return a sprite with the background of the level
   */
  Sprite getBackground();

  /**
   * @return The Blocks that make up this level, each block contains its size,
   *         color and location.
   */
  List<Block> blocks();

  /**
   * @return the Number of blocks that should be removed before the level is
   *         considered to be "cleared". This number should be <= blocks.size();
   */
  int numberOfBlocksToRemove();
}