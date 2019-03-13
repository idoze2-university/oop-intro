import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The Ball class implements a Ball object.
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Frame container;

    /**
     * Default constructor.
     *
     * @param center    Point representing the center coordinates.
     * @param radius    Size of the ball radius.
     * @param color     Color of the ball.
     * @param container The frame in which to draw the ball.
     */
    public Ball(Point center, int radius, Color color, Frame container) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.container = container;
    }


    /**
     * Overloading constructor, reads point as X and Y values.
     *
     * @param x         X coordinate of the center point.
     * @param y         Y coordinate of the center point.
     * @param radius    Size of the ball radius.
     * @param color     Color of the ball.
     * @param container The frame in which to draw the ball.
     */
    public Ball(double x, double y, int radius, Color color, Frame container) {
        this(new Point(x, y), radius, color, container);
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

    /**
     * Moves the ball one step using the Velocity.
     *
     * @param rand random initializer.
     */
    public void moveOneStep(Random rand) {
        if (velocity == null) {
            setRandomVelocity(rand);
        } else {
            if (center.getX() + radius >= container.getRightBound()) {
                velocity = new Velocity(-Math.abs(velocity.getDx()), velocity.getDy());
            }
            if (center.getY() + radius >= container.getLowerBound()) {
                velocity = new Velocity(velocity.getDx(), -Math.abs(velocity.getDy()));
            }
            if (center.getX() - radius <= container.getLeftBound()) {
                velocity = new Velocity(Math.abs(velocity.getDx()), velocity.getDy());
            }
            if (center.getY() - radius <= container.getUpperBound()) {
                velocity = new Velocity(velocity.getDx(), Math.abs(velocity.getDy()));
            }
        }
        center = velocity.applyToPoint(center);
    }

    private double massToSpeed() {
        int modifier = 50;
        if (radius >= 50) {
            return modifier / 50;
        }
        return (double) modifier / (double) radius;
    }

    private void setRandomVelocity(Random rand) {
        Velocity v = new Velocity(0, 0);
        do {
            v = Velocity.fromAngleAndSpeed(rand.nextInt(720) - 360, massToSpeed());
        } while (!container.isInBounds(v.applyToPoint(center)));
        setVelocity(v);
    }

}