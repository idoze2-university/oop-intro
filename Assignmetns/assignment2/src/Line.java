/**
 * The Line class implements an Line-Segment object, consisting of a starting
 * and and ending point, which enclose it.
 *
 * @author zeiraid.
 */
public class Line {
  private Point start;
  private Point end;

  /**
   * Default constructor of a Line object.
   *
   * @param start Starting point of the line segment.
   * @param end   Ending point of the line segment.
   */
  public Line(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Overloaded constructor of a Line object, Allows the direct use of x,y
   * coordinates for the starting point and ending point members. Oveloads the
   * default constructor.
   *
   * @param x1 X coordinate for Starting point.
   * @param y1 Y coordinate for Starting point.
   * @param x2 X coordinate for Ending point.
   * @param y2 Y coordinate for Ending point.
   */
  public Line(double x1, double y1, double x2, double y2) {
    this(new Point(x1, y1), new Point(x2, y2));
  }

  /**
   * Calculates th private Point start; private Point end; e length of the line.
   *
   * @return the length of the line.
   */
  public double length() {
    return end.distance(start);
  }

  /**
   * Calculates the middle point of the line.
   *
   * @return the middle point of the line.
   */
  public Point middle() {
    double midX = (start.getX() + end.getX()) / 2.0;
    double midY = (start.getY() + end.getY()) / 2.0;
    return new Point(midX, midY);
  }

  /**
   * @return the start point of the line.
   */
  public Point start() {
    return start;
  }

  /**
   * @return the end point of the line.
   */
  public Point end() {
    return end;
  }

  /**
   * @return the difference between X coordinates of start and end points.
   */
  private double deltaX() {
    return start.getX() - end.getX();
  }

  /**
   * @return the difference between Y coordinates of start and end points.
   */
  private double deltaY() {
    return start.getY() - end.getY();
  }

  /**
   * @return Wether or not the line is a vertical line.
   */
  private boolean isVertical() {
    return deltaX() == 0.0;
  }

  /**
   * @return The slope of the line.
   */
  private double slope() {
    return deltaY() / deltaX();
  }

  /**
   * @param other Line to compare with.
   * @return Wether or not the line is parralel to other.
   */
  private boolean isParralel(Line other) {
    if (other == null) {
      return false;
    }
    if (isVertical()) {
      return other.isVertical();
    } else {
      return slope() == other.slope() && !equals(other);
    }
  }

  /**
   * Calculates the point of intersection between the lines, as if they weren't
   * enclosed by starting/ending points.
   *
   * @param other Line to compare with.
   * @return Point of intersection between the lines.
   */
  private Point lineIntersectionWith(Line other) {
    if (other == null) {
      return null;
    }
    double slope = slope();
    double otherSlope = other.slope();
    double intersectionX = (slope * start.getX() - otherSlope * other.start.getX() + other.start.getY() - start.getY())
        / (slope - otherSlope);

    double intersectionY = slope * intersectionX - slope * start.getX() + start.getY();
    return new Point(intersectionX, intersectionY);
  }

  /**
   * @param p Point to check the condition on.
   * @return Wether or not p is on the line Segment between Start and End points.
   */
  private boolean isInLineSegment(Point p) {
    double slope = slope();
    boolean isInLine = p.getY() + 0.001 >= slope * p.getX() - slope * start.getX() + start.getY();
    boolean isInLineSegment = false;
    if (start.getX() < end.getX()) {
      isInLineSegment = p.getX() >= start.getX() && p.getX() <= end.getX();
    } else {
      isInLineSegment = p.getX() <= start.getX() && p.getX() >= end.getX();
    }
    return isInLine && isInLineSegment;
  }

  /**
   * Checks wether or not the lines do not equal nor are parralel to each other,
   * and then calculates the intersection points of the infinite linear lines with
   * the same slopes which pass through the starting points of each line, then
   * tests to see if such a point exists on the enclosed line segment.
   *
   * @param other Line to compare with.
   * @return true if the lines intersect, false otherwise
   */
  public boolean isIntersecting(Line other) {
    if (other == null) {
      return false;
    }
    return !equals(other) && !isParralel(other) && isInLineSegment(lineIntersectionWith(other))
        && other.isInLineSegment(lineIntersectionWith(other));

  }

  /**
   * @param other Line to compare with.
   * @return the intersection point if the lines intersect, and null otherwise.
   */
  public Point intersectionWith(Line other) {
    if (isIntersecting(other)) {
      Point inter = lineIntersectionWith(other);
      return inter;
    }
    return null;
  }

  /**
   * @param other Line to compare with.
   * @return true is the lines are equal, false otherwise.
   */
  public boolean equals(Line other) {
    if (other == null) {
      return false;
    } else {

      return start == other.start && end == other.end;
    }
  }

}