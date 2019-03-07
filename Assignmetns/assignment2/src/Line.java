public class Line {
    private Point start;
    private Point end;

    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    // Return the length of the line
    public double length() {
        return end.distance(start);
    }

    // Returns the middle point of the line
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    // Returns the start point of the line
    public Point start() {
        return start;
    }

    // Returns the end point of the line
    public Point end() {
        return end;
    }

    private double deltaX() {
        return start.getX() - end.getX();
    }

    private double deltaY() {
        return start.getY() - end.getY();
    }

    private boolean isVertical() {
        return deltaX() == 0.0;
    }

    private double slope() {
        return deltaY() / deltaX();
    }

    private boolean isParralel(Line other) {
        if (isVertical()) {
            return other.isVertical();
        } else {
            return slope() == other.slope() && !equals(other);
        }
    }

    private Point lineIntersectionWith(Line other) {
        double slope = slope();
        double otherSlope = other.slope();
        double intersectionX = (slope * start.getX() - otherSlope * other.start.getX() + other.start.getY()
                - start.getY()) / (slope - otherSlope);

        double intersectionY = slope * intersectionX - slope * start.getX() + start.getY();
        return new Point(intersectionX, intersectionY);
    }

    private boolean isInLineSegment(Point p) {
        double slope = slope();
        boolean isInLine = p.getY() == slope * p.getX() - slope * start.getX() + start.getY();
        boolean isInLineSegment = false;
        if (start.getX() < end.getX()) {
            isInLineSegment = p.getX() >= start.getX() && p.getX() <= end.getX();
        } else {
            isInLineSegment = p.getX() <= start.getX() && p.getX() >= end.getX();
        }
        return isInLine && isInLineSegment;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return !equals(other) && !isParralel(other) && isInLineSegment(lineIntersectionWith(other));

    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            return lineIntersectionWith(other);
        }
        return null;
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return start == other.start && end == other.end;
    }

}