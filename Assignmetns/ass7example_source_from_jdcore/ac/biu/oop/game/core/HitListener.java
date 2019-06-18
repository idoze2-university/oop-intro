package ac.biu.oop.game.core;

import ac.biu.oop.game.arkanoid.Ball;
import ac.biu.oop.game.arkanoid.Block;

public abstract interface HitListener
{
  public abstract void hitEvent(Block paramBlock, Ball paramBall);
}
