import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.List;

/**
 * The Ball class implements a Ball object.
 *
 * @author zeiraid
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
    Point pt = velocity.applyToPoint(center);
    surface.fillCircle((int) pt.getX(), (int) pt.getY(), 4);
    for (Line t : getTrajectory()) {
      t.drawOn(surface);
    }
  }

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
   * Sets the velocity of the ball to be v.
   *
   * @param dx the dx of the velocity to set on the ball.
   * @param dy the dy of the velocity to set on the ball.
   */
  public void setVelocity(double dx, double dy) {
    setVelocity(new Velocity(dx, dy));
  }

  /**
   * @return The Velocity object of the ball.
   */
  public Velocity getVelocity() {
    return velocity;
  }

  private Line[] getTrajectory() {
    Line trajectory = new Line(center, velocity.applyToPoint(center));
    Line[] trajs = { trajectory.extend(radius), trajectory.getHorizonalComponent().extend(radius),
        trajectory.getVerticalComponent().extend(radius) };
    return trajs;
  }

  /**
   *
   */
  public void moveOneStep() {
    boolean colided = false;
    Velocity reducedVelocity = velocity;
    Line[] trajectories = getTrajectory();
    for (Line trajectory : trajectories) {
      CollisionInfo col = map.getClosestCollision(trajectory);
      if (col != null) {
        colided = true;
        Point colPt = col.collisionPoint();
        double approachSpeed = center.distance(colPt) - radius;
        double approachAngle = trajectories[0].getAngle();
        if (approachSpeed < center.distance(reducedVelocity.applyToPoint(center))) {
          reducedVelocity = Velocity.fromAngleAndSpeed(approachAngle, approachSpeed);
          velocity = col.collisionObject().hit(colPt, velocity);
        }
      }
    }
    if (colided) {
      center = reducedVelocity.applyToPoint(center);
    } else {
      center = velocity.applyToPoint(center);
    }
  }

  public void addToGame(Game g) {
    g.addSprite(this);
  }
}