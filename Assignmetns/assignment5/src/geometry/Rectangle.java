package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class implements a rectangular object.
 *
 * @author zeiraid 322607177
 */
public class Rectangle {

  private Point upperLeft;
  private double width;
  private double height;

  /**
   * Create a new rectangle with location and width/height.
   *
   * @param upperLeft Location of the rectangle.
   * @param width     Width of the rectangle.
   * @param height    Height of the rectangle.
   */
  public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
  }

  /**
   * Creates a new rectangle with location and width/height.
   *
   * @param x      upperLeft x coordinate.
   * @param y      upperLeft y coordinate.
   * @param width  Width of the rectangle.
   * @param height Height of the rectangle.
   */
  public Rectangle(double x, double y, double width, double height) {
    this(new Point(x, y), width, height);
  }

  /**
   * @param line line to test intersection with.
   * @return a (possibly empty) List of intersection points between the boundry
   *         lines and the specified line.
   */
  public List<Point> intersectionPoints(Line line) {
    ArrayList<Point> lst = new ArrayList<Point>();
    Line[] boundries = // Array of Boundry Lines.
        { new Line(upperLeft, upperLeft.addX(width)), // Upper Boundry.
            new Line(upperLeft, upperLeft.addY(height)), // Left Boundry.
            new Line(upperLeft.addY(height), upperLeft.add(width, height)), // Lower Boundry.
            new Line(upperLeft.addX(width), upperLeft.add(width, height)) }; // Right Boundry.
    for (Line l : boundries) {
      Point pt = line.intersectionWith(l);
      if (pt != null) {
        lst.add(pt);
      }
    }
    return lst;
  }

  /**
   * @return the upperLeft.
   */
  public Point getUpperLeft() {
    return upperLeft;
  }

  /**
   * @return the width.
   */
  public double getWidth() {
    return width;
  }

  /**
   * @return the height.
   */
  public double getHeight() {
    return height;
  }

  /**
   * moves the rectangle by dx and dy.
   *
   * @param dx x-axis movement.
   * @param dy y-axis movement.
   */
  public void move(double dx, double dy) {
    Velocity transformation = new Velocity(dx, dy);
    upperLeft = transformation.applyToPoint(upperLeft);
  }
}