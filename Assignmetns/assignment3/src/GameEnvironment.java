import java.util.ArrayList;

/**
 * The GameEnvironment Class implements an object which.
 */
public class GameEnvironment {

  private ArrayList<Collidable> objects;

  public GameEnvironment() {
    objects = new ArrayList<Collidable>();
  }

  public void remove(Collidable object) {
    objects.remove(object);
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
        if (pt != null) {
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