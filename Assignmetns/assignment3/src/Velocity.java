/**
 * The velocity class provides an implementation for a velocity object, holding
 * a dx and dy fields, capable of moving a point one step in a given velocity.
 *
 * @author zeiraid
 */
public class Velocity {
  private double dx;
  private double dy;

  /**
   * Default constructor.
   *
   * @param dx the movement in X axis.
   * @param dy the movement in Y axis.
   */
  public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }

  /**
   * @return dx value.
   */
  public double getDx() {
    return dx;
  }

  /**
   * @return dy value.
   */
  public double getDy() {
    return dy;
  }

  /**
   * applies dx and dy in one step to point p.
   *
   * @param p Point to appy to.
   * @return Point with applies dx and dy.
   */
  public Point applyToPoint(Point p) {
    return p.add(dx,dy);
  }

  /**
   * Calculates a velocity object for given angle and speed.
   *
   * @param angle Direction of the velocity vector.
   * @param speed Size of the velocity vector.
   * @return dx and dy coordinates of the velocity vector in a form of a Velocity
   *         object.
   */
  public static Velocity fromAngleAndSpeed(double angle, double speed) {
    double anglerd = Math.toRadians(angle);
    double dx = Math.cos(anglerd) * speed;
    double dy = Math.sin(anglerd) * -speed;
    return new Velocity(dx, dy);
  }
}