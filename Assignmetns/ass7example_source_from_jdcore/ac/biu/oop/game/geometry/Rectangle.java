package ac.biu.oop.game.geometry;


public class Rectangle
{
  private Point topLeftPoint;
  
  private Point topRightPoint;
  
  private Point bottomLeftPoint;
  
  private Point bottomRightPoint;
  
  private Line left;
  
  private Line right;
  
  private Line top;
  private Line bottom;
  
  public Rectangle(double x, double y, double width, double height)
  {
    topLeftPoint = new Point(x, y);
    topRightPoint = new Point(x + width, y);
    bottomLeftPoint = new Point(x, y + height);
    bottomRightPoint = new Point(x + width, y + height);
    
    left = new Line(topLeftPoint, bottomLeftPoint);
    right = new Line(topRightPoint, bottomRightPoint);
    top = new Line(topLeftPoint, topRightPoint);
    bottom = new Line(bottomLeftPoint, bottomRightPoint);
  }
  
  public double getWidth()
  {
    return Math.abs(topLeftPoint.getX() - topRightPoint.getX());
  }
  
  public double getHeight() {
    return Math.abs(topLeftPoint.getY() - bottomLeftPoint.getY());
  }
  
  public Line getLeftEdge() {
    return left;
  }
  
  public Point getTopLeftPoint() {
    return topLeftPoint;
  }
  
  public Point getBottomLeftPoint() {
    return bottomLeftPoint;
  }
  
  public Point getTopRightPoint() {
    return topRightPoint;
  }
  
  public Point getBottomRightPoint() {
    return bottomRightPoint;
  }
  
  public Line getRightEdge() {
    return right;
  }
  
  public Line getTopEdge()
  {
    return top;
  }
  
  public Line getBottomEdge()
  {
    return bottom;
  }
  
  public Collision collideCircle(Line trajectory) {
    EdgeType edgeType;
    Point closestIntersection;
    EdgeType edgeType;
    if (trajectory.start().getX() > trajectory.end().getX()) {
      Point closestIntersection = right.intersectionWith(trajectory);
      edgeType = EdgeType.RIGHT;
    } else {
      closestIntersection = left.intersectionWith(trajectory);
      edgeType = EdgeType.LEFT;
    }
    
    if (closestIntersection == null) {
      if (trajectory.start().getY() > trajectory.end().getY()) {
        closestIntersection = bottom.intersectionWith(trajectory);
        edgeType = EdgeType.BOTTOM;
      } else {
        closestIntersection = top.intersectionWith(trajectory);
        edgeType = EdgeType.TOP;
      }
    }
    

    if (closestIntersection == null) {
      return null;
    }
    
    return new Collision(closestIntersection, edgeType);
  }
  
  public String toString()
  {
    return "Rectangle{left=" + left + ", right=" + right + ", top=" + top + ", bottom=" + bottom + '}';
  }
}
