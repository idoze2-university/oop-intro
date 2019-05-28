package geometry;

import java.util.Random;

/**
 * The Point class provides an implememntation of a Point object, with the
 * members: X coordinate, Y coordinate.
 *
 * @author zeiraid 322607177
 */
public class Point {
  private double x = 0;
  private double y = 0;

  /**
   * The default Point constructor.
   *
   * @param x value to initialize the X coordinate.
   * @param y value to initialize the Y coordinate.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * calculates the distance between two points.
   *
   * @param other the other point to compare with.
   * @return the distance of this point to the other point.
   */
  public double distance(Point other) {
    if (other == null) {
      return -1;
    }
    return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
  }

  /**
   * avoids Floating Point Exception.
   *
   * @param other the other point to compare with.
   * @return true is the points are equal, false otherwise
   */
  public boolean equals(Point other) {
    return Math.abs(x - other.x) <= 0.00001 && Math.abs(y - other.y) <= 0.00001;
  }

  /**
   * The get method for the X member.
   *
   * @return the x value of this point
   */
  public double getX() {
    return x;
  }

  /**
   * moves the point by dx and dy.
   *
   * @param dx x-axis movement.
   * @param dy y-axis movement.
   * @return moved point.
   */
  public Point add(double dx, double dy) {
    return new Point(x + dx, y + dy);
  }

  /**
   * moves the point by dx.
   *
   * @param dx x-axis movement.
   * @return moved point.
   */
  public Point addX(double dx) {
    return add(dx, 0);
  }

  /**
   * moves the point by dy.
   *
   * @param dy y-axis movement.
   * @return moved point.
   */
  public Point addY(double dy) {
    return add(0, dy);
  }

  /**
   * The get method for the Y member.
   *
   * @return the y value of this point
   */
  public double getY() {
    return y;
  }

  /**
   * @param rand   Random object for random calculations.
   * @param xbound max value for X coordinate.
   * @param ybound max value for Y coordinate.
   * @return random point in the given x and y boundries.
   */
  public static Point random(Random rand, int xbound, int ybound) {
    return new Point(rand.nextInt(xbound), rand.nextInt(ybound));
  }
}