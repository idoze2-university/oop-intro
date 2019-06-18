package ac.biu.oop.game.levels;

import ac.biu.oop.game.arkanoid.Block;
import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.core.Velocity;
import java.util.List;

public abstract interface LevelInformation
{
  public abstract int numberOfBalls();
  
  public abstract List<Velocity> initialBallVelocities();
  
  public abstract int paddleSpeed();
  
  public abstract int paddleWidth();
  
  public abstract String levelName();
  
  public abstract Sprite getBackgroundSprite();
  
  public abstract List<Block> blocks();
  
  public abstract int numberOfBlocksToRemove();
}
