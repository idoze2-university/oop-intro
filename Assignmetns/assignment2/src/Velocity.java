public class Velocity {
    private double dx;
    private double dy;

    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //double anglerd = Math.toRadians(angle);
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * -speed;
        return new Velocity(dx, dy);
    }
}