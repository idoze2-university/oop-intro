package game.component;

import java.util.ArrayList;
import game.collision.Collidable;
import game.collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

/**
 * The GameEnvironment Class implements an object which holds a collection of
 * collidable objects.
 *
 * @author zeiraid 322607177
 */
public class GameEnvironment {

  private ArrayList<Collidable> objects;

  /**
   * default constructor.
   */
  public GameEnvironment() {
    objects = new ArrayList<Collidable>();
  }

  /**
   * add the given collidable to the environment.
   *
   * @param c Object to add to the environment
   */
  public void addCollidable(Collidable c) {
    objects.add(c);
  }

  /**
   * remove the given collidable to the environment.
   *
   * @param c Object to remove to the environment
   */
  public void remove(Collidable c) {
    objects.remove(c);
  }

  /**
   * Assume an object moving from line.start() to line.end().
   *
   * @param trajectory The line in which the object moves.
   * @return the information about the closest collision that is going to occur,
   *         or null if doesn't collide.
   */
  public CollisionInfo getClosestCollision(Line trajectory) {
    CollisionInfo ret = null;
    Point closest = trajectory.end();
    for (Collidable obj : objects) {
      for (Point pt : obj.getCollisionRectangle().intersectionPoints(trajectory)) {
        // if a collision is found with obj:
        if (pt != null) {
          // get the closest intersection point (sort distance from start of trajectory)
          if (pt.distance(trajectory.start()) <= closest.distance(trajectory.start())) {
            closest = pt;
            ret = new CollisionInfo(pt, obj);
          }
        }
      }
    }
    return ret;
  }
}