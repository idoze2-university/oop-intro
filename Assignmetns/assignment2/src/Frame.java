import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The Frame class implements a frame object, handling frame boundries and
 * mapping points in a fixed rectangle. can be draws to a drawsurface.
 *
 * @author zeiraid
 */
public class Frame {
  private Point zero;
  private int width;
  private int height;
  private Color color;

  /**
   * Default constructor.
   *
   * @param zero   Zero point of the frame.
   * @param width  Width of the frame.
   * @param height Height of the frame.
   * @param color  Color of the frame.
   */
  public Frame(Point zero, int width, int height, Color color) {
    this.zero = zero;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * Overloaded constructor for initializing a frame with x and y coordinates
   * instead of a zero point.
   *
   * @param x      X coordinate for zero point.
   * @param y      Y coordinate for zero point.
   * @param width  Width of the frame.
   * @param height Height of the frame.
   * @param color  Color of the frame.
   */
  public Frame(double x, double y, int width, int height, Color color) {
    this(new Point(x, y), width, height, color);
  }

  /**
   * @return Central point of the frame rectangle.
   */
  public Point getCenter() {
    return new Point((zero.getX() + width) / 2, (zero.getY() + height) / 2);
  }

  /**
   * @return Zero point of the frame.
   */
  public Point getZeroPoint() {
    return zero;
  }

  /**
   * @return X cordinate of the left boundry.
   */
  public double getLeftBound() {
    return zero.getX();
  }

  /**
   * @return Y coordinate of the upper boundry.
   */
  public double getUpperBound() {
    return zero.getY();
  }

  /**
   * @return Y coordinate of the lower boundry.
   */
  public double getLowerBound() {
    return zero.getY() + height;
  }

  /**
   * @return X coordinate of the right boundry.
   */
  public double getRightBound() {
    return zero.getX() + width;
  }

  /**
   * @param p Point to test.
   * @return Wether or not the point 'p' is in the frame area.
   */
  public boolean isInBounds(Point p) {
    boolean rightBound = p.getX() <= zero.getX() + width;
    boolean leftBound = p.getX() >= zero.getX();
    boolean lowerBound = p.getY() <= zero.getY() + height;
    boolean upperBound = p.getY() >= zero.getY();
    return rightBound && leftBound && lowerBound && upperBound;
  }

  /**
   * @param rand   Random object for random calculations.
   * @param radius minimum distance from the frame boundries.
   * @return random point in the frame area.
   */
  public Point randomPoint(Random rand, int radius) {
    int xlowerBound = (int) zero.getX();
    int xupperBound = (int) zero.getX() + width;
    int ylowerBound = (int) zero.getY();
    int yupperBound = (int) zero.getY() + height;
    Point p = Point.random(rand, xupperBound - (xlowerBound + radius), yupperBound - (ylowerBound + radius));
    Velocity move = new Velocity(xlowerBound + radius, ylowerBound + radius);
    return move.applyToPoint(p);
  }

  /**
   * @param rand Random object for random calculations.
   * @return random point in the frame area with maximum of zero distance from the
   *         boundries.
   *
   */
  public Point randomPoint(Random rand) {
    return randomPoint(rand, 0);
  }

  /**
   * Draws the rectangle of the frame area on 'd'.
   *
   * @param d DrawSurface to draw the rectangle to.
   */
  public void drawOnSurface(DrawSurface d) {
    d.setColor(color);
    d.fillRectangle((int) zero.getX(), (int) zero.getY(), width, height);
  }
}