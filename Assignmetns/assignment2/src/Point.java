/**
 * The Point class provides an implememntation of a Point object, with the
 * members: X coordinate, Y coordinate.
 *
 * @author zeiraid
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
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * @param other the other point to compare with.
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return x == other.x && y == other.y;
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
     * The get method for the Y member.
     *
     * @return the y value of this point
     */
    public double getY() {
        return y;
    }
}