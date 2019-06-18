package ac.biu.oop.game.core;

import ac.biu.oop.game.arkanoid.Ball;
import ac.biu.oop.game.geometry.Collision;
import ac.biu.oop.game.geometry.Rectangle;

public abstract interface Collidable
{
  public abstract Rectangle getCollisionRectangle();
  
  public abstract Velocity hit(Ball paramBall, Collision paramCollision, Velocity paramVelocity);
}
