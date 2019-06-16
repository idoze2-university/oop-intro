package game.collision;

import geometry.Point;

/**
 * The CollisionInfo class implements an object which holds information
 * regarding a collision of the object calling it with another object, at a
 * specific point.
 *
 * @author zeiraid 322607177
 */
public class CollisionInfo {

  private Point collisionPoint;
  private Collidable collisionObject;

  /**
   * The default constructor.
   *
   * @param collisionPoint  the point in which the collision occurs.
   * @param collisionObject the object involved in the collision.
   */
  public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
    this.collisionObject = collisionObject;
    this.collisionPoint = collisionPoint;
  }

  /**
   * @return the point at which the collision occurs.
   */
  public Point collisionPoint() {
    return collisionPoint;

  }

  /**
   * @return the collidable object involved in the collision.
   */
  public Collidable collisionObject() {
    return collisionObject;
  }
}