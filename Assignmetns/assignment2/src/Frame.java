import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;

public class Frame {
    private Point zero;
    private int width;
    private int height;
    private Color color;

    public Frame(Point zero, int width, int height, Color color) {
        this.zero = zero;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Point getCenter() {
        return new Point((zero.getX() + width) / 2, (zero.getY() + height) / 2);
    }

    public Frame(Point zero, Point end, Color color) {
        this.zero = zero;
        this.width = (int) (end.getX() - zero.getX());
        this.width = (int) (end.getY() - zero.getY());
    }

    public Frame(double x, double y, int width, int height, Color color) {
        this(new Point(x, y), width, height, color);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getZeroPoint() {
        return zero;
    }

    public double getLeftBound() {
        return zero.getX();
    }

    public double getUpperBound() {
        return zero.getY();
    }

    public double getLowerBound() {
        return zero.getY() + height;
    }

    public double getRightBound() {
        return zero.getX() + width;
    }

    public boolean isInBounds(Point p) {
        boolean rightBound = p.getX() <= zero.getX() + width;
        boolean leftBound = p.getX() >= zero.getX();
        boolean lowerBound = p.getY() <= zero.getY() + height;
        boolean upperBound = p.getY() >= zero.getY();
        return rightBound && leftBound && lowerBound && upperBound;
    }

    public Point randomPoint(Random rand, int radius) {
        int xLowerBound = (int) zero.getX();
        int xUpperBound = (int) zero.getX() + width;
        int yLowerBound = (int) zero.getY();
        int yUpperBound = (int) zero.getY() + height;
        Point p = Point.random(rand, xUpperBound - (xLowerBound + radius), yUpperBound - (yLowerBound + radius));
        Velocity move = new Velocity(xLowerBound + radius, yLowerBound + radius);
        return move.applyToPoint(p);
    }

    public Point randomPoint(Random rand) {
        return randomPoint(rand, 0);
    }

    public void drawOnSurface(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) zero.getX(), (int) zero.getY(), width, height);
    }
}