package ac.biu.oop.game.geometry;




public class Point
{
  private double x;
  


  private double y;
  



  public Point(double x, double y)
  {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public double distance(Point other) {
    return Math.sqrt(Math.pow(getX() - other.getX(), 2.0D) + Math.pow(getY() - other.getY(), 2.0D));
  }
  
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if ((o == null) || (getClass() != o.getClass())) { return false;
    }
    Point point = (Point)o;
    
    if (Double.compare(x, x) != 0) return false;
    if (Double.compare(y, y) != 0) { return false;
    }
    return true;
  }
  


  public int hashCode()
  {
    long temp = x != 0.0D ? Double.doubleToLongBits(x) : 0L;
    int result = (int)(temp ^ temp >>> 32);
    temp = y != 0.0D ? Double.doubleToLongBits(y) : 0L;
    result = 31 * result + (int)(temp ^ temp >>> 32);
    return result;
  }
  
  public String toString()
  {
    return "Point{x=" + x + ", y=" + y + '}';
  }
}
