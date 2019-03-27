import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Ball class implements a Ball object.
 *
 * @author zeiraid 322607177
 */
public class Ball implements Sprite {
  private Point center;
  private int radius;
  private Color color;
  private GameEnvironment map;
  private Velocity velocity;

  /**
   * Default constructor.
   *
   * @param center Point representing the center coordinates.
   * @param radius Size of the ball radius.
   * @param color  Color of the ball.
   * @param map    The GameEnvironment in which the ball travels.
   */
  public Ball(Point center, int radius, Color color, GameEnvironment map) {
    this.center = center;
    this.radius = radius;
    this.color = color;
    this.map = map;
  }

  /**
   * Overloading constructor, reads point as X and Y values.
   *
   * @param x      X coordinate of the center point.
   * @param y      Y coordinate of the center point.
   * @param radius Size of the ball radius.
   * @param color  Color of the ball.
   * @param map    The GameEnvironment in which the ball travels.
   */
  public Ball(double x, double y, int radius, Color color, GameEnvironment map) {
    this(new Point(x, y), radius, color, map);
  }

  /**
   * @return X coordinate of the center point.
   */
  public double getX() {
    return center.getX();
  }

  /**
   * @return Radius of the ball.
   */
  public int getSize() {
    return radius;
  }

  /**
   * @return Y coordinate of the center point.
   */
  public double getY() {
    return center.getY();
  }

  /**
   * @return Color of the ball.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Draws the ball on the surface 'surface'.
   *
   * @param surface The DrawSurface on which to draw the ball.
   */
  public void drawOn(DrawSurface surface) {
    surface.setColor(color);
    surface.drawCircle((int) getX(), (int) getY(), radius);
    surface.fillCircle((int) getX(), (int) getY(), radius);
  }

  /**
   * Invokes a tick event.
   */
  public void timePassed() {
    moveOneStep();
  }

  /**
   * Sets the velocity of the ball to be v.
   *
   * @param v the velocity to set on the ball.
   */
  public void setVelocity(Velocity v) {
    this.velocity = v;
  }

  /**
   * @return The Velocity object of the ball.
   */
  public Velocity getVelocity() {
    return velocity;
  }

  /**
   * calculates the trajectory line of the velocity and both of it's polar
   * components.
   *
   * @return an array of lines consisting of the main vector, horizonal component,
   *         vertical component.
   */
  private Line[] getTrajectoryComponents() {
    Line trajectory = new Line(center, velocity.applyToPoint(center));
    Line[] trajs = {trajectory.extend(radius), trajectory.getHorizonalComponent().extend(radius),
        trajectory.getVerticalComponent().extend(radius) };
    return trajs;
  }

  /**
   * 1) compute the ball trajectory (the trajectory is "how the ball will move
   * without any obstacles" -- its a line starting at current location, and ending
   * where the velocity will take the ball if no collisions will occur).
   *
   * 2) Check (using the game environment) if moving on this trajectory will hit
   * anything.
   *
   * 2.1) If no, then move the ball to the end of the trajectory.
   *
   * 2.2) Otherwise (there is a hit): 2.2.2) move the ball to "almost" the hit
   * point, but just slightly before it. 2.2.3) notify the hit object (using its
   * hit() method) that a collision occurred. 2.2.4) update the velocity to the
   * new velocity returned by the hit() method.
   */
  private void moveOneStep() {
    boolean colided = false;
    CollisionInfo col = null;
    Velocity reducedVelocity = velocity;
    Line[] trajectories = getTrajectoryComponents();
    for (Line trajectory : trajectories) {
      // Try to find a closest collision.
      CollisionInfo currentCol = map.getClosestCollision(trajectory);
      if (currentCol != null) {
        // mark a collision found.
        colided = true;
        Point colPt = currentCol.collisionPoint();
        double approachSpeed = center.distance(colPt) - radius;
        double approachAngle = trajectories[0].getAngle();
        // if the collision in this component is closer than the component before,
        // prefer to step less in the current trajectory.
        if (approachSpeed < center.distance(reducedVelocity.applyToPoint(center))) {
          reducedVelocity = Velocity.fromAngleAndSpeed(approachAngle, approachSpeed);
          // save the found collision to 'col'.
          col = currentCol;
        }
      }
    }
    if (colided) {
      // move near collision point.
      center = reducedVelocity.applyToPoint(center);
      // save the returned velocity upon hit.
      velocity = col.collisionObject().hit(col.collisionPoint(), velocity);
    } else {
      center = velocity.applyToPoint(center);
    }
  }

  /**
   * adds the ball to game g's sprite list.
   *
   * @param g the game to which the ball should be added.
   */
  public void addToGame(Game g) {
    g.addSprite(this);
  }
}