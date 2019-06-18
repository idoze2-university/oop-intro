package ac.biu.oop.game.geometry;



public class Line
{
  private Point start;
  

  private Point end;
  

  private double length;
  

  public Line(Point start, Point end)
  {
    this.start = start;
    this.end = end;
    length = start.distance(end);
  }
  
  public Line(double x1, double y1, double x2, double y2) {
    this(new Point(x1, y1), new Point(x2, y2));
  }
  
  public Point start() {
    return start;
  }
  
  public Point middle() {
    double middleX = (start.getX() + end.getX()) / 2.0D;
    double middleY = (start.getY() + end.getY()) / 2.0D;
    return new Point(middleX, middleY);
  }
  
  public Point end() {
    return end;
  }
  
  public double length() {
    return length;
  }
  
  public boolean isIntersecting(Line other) {
    return intersectionWith(other) != null;
  }
  
  public Point intersectionWith(Line other)
  {
    Point p1 = start();
    Point p2 = end();
    Point p3 = other.start();
    Point p4 = other.end();
    
    return intersectionWith(p1, p2, p3, p4);
  }
  
  private Point intersectionWith(Point p1, Point p2, Point p3, Point p4) {
    double denominator = (p4.getY() - p3.getY()) * (p2.getX() - p1.getX()) - (p4.getX() - p3.getX()) * (p2.getY() - p1.getY());
    

    if (denominator != 0.0D) {
      double ua = ((p4.getX() - p3.getX()) * (p1.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p1.getX() - p3.getX())) / denominator;
      

      if ((ua >= 0.0D) && (ua <= 1.0D))
      {
        double ub = ((p2.getX() - p1.getX()) * (p1.getY() - p3.getY()) - (p2.getY() - p1.getY()) * (p1.getX() - p3.getX())) / denominator;
        
        if ((ub >= 0.0D) && (ub <= 1.0D))
        {
          int x = (int)(p1.getX() + ua * (p2.getX() - p1.getX()));
          int y = (int)(p1.getY() + ua * (p2.getY() - p1.getY()));
          return new Point(x, y);
        }
      }
    }
    
    return null;
  }
  
  public String toString()
  {
    return "Line{start=" + start + ", end=" + end + '}';
  }
}
