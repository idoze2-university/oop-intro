package ac.biu.oop.game.core;

import ac.biu.oop.game.geometry.Point;







public class CollisionInfo
{
  private Point collisionPoint;
  private Collidable collidedObject;
  
  public CollisionInfo(Point collisionPoint, Collidable collidedObject)
  {
    this.collisionPoint = collisionPoint;
    this.collidedObject = collidedObject;
  }
  
  public Point collisionPoint() {
    return collisionPoint;
  }
  
  public Collidable collisionObject() {
    return collidedObject;
  }
}
