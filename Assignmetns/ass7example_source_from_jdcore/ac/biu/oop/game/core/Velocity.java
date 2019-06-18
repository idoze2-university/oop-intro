package ac.biu.oop.game.core;

import ac.biu.oop.game.geometry.Point;




public class Velocity
{
  private double dx;
  private double dy;
  
  public static Velocity fromAngleAndSpeed(double angle, double speed)
  {
    double dx = Math.cos((angle - 90.0D) / 180.0D * 3.141592653589793D) * speed;
    double dy = Math.sin((angle - 90.0D) / 180.0D * 3.141592653589793D) * speed;
    
    return new Velocity(dx, dy);
  }
  


  public Velocity(double dx, double dy)
  {
    this.dx = dx;
    this.dy = dy;
  }
  

  public Point applyToPoint(Point p)
  {
    return new Point(p.getX() + dx, p.getY() + dy);
  }
  
  public Velocity scale(double scale) {
    return deflect(scale, scale);
  }
  
  public Velocity deflect(double xChange, double yChange) {
    return new Velocity(dx * xChange, dy * yChange);
  }
  
  public Velocity newAngle(double angle) {
    double speed = Math.sqrt(dx * dx + dy * dy);
    
    return fromAngleAndSpeed(angle, speed);
  }
  
  public double getDx() {
    return dx;
  }
  
  public double getDy() {
    return dy;
  }
  
  public String toString()
  {
    return "Velocity{dx=" + dx + ", dy=" + dy + '}';
  }
}
