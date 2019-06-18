package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Collidable;
import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.core.Velocity;
import ac.biu.oop.game.geometry.Collision;
import ac.biu.oop.game.geometry.Line;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;








public class Ball
  implements Sprite
{
  private static final double EPSILON = 1.0E-4D;
  private Point point;
  private double radius;
  private Velocity velocity;
  private GameEnvironment state;
  
  public Ball(Point center, double radius, GameEnvironment state)
  {
    point = center;
    setVelocity(0.0D, 0.0D);
    this.radius = radius;
    this.state = state;
  }
  
  public void setVelocity(Velocity v) {
    velocity = v;
  }
  
  public void setVelocity(double dx, double dy) {
    velocity = new Velocity(dx, dy);
  }
  
  public Velocity getVelocity() {
    return velocity;
  }
  
  public void moveOneStep() {
    point = getVelocity().applyToPoint(point);
  }
  
  public double getX()
  {
    return point.getX();
  }
  
  public double getY() {
    return point.getY();
  }
  
  public void addTo(GameLevel level) {
    level.addSprite(this);
  }
  
  public void removeFrom(GameLevel level) {
    level.removeSprite(this);
  }
  

  public void drawOn(DrawSurface surface)
  {
    surface.setColor(Color.WHITE);
    surface.fillCircle((int)getX(), (int)getY(), (int)radius);
    surface.setColor(Color.BLACK);
    surface.drawCircle((int)getX(), (int)getY(), (int)radius);
  }
  




  public void setLocation(Point point)
  {
    this.point = point;
  }
  
  public void moveTowardsLocation(Point goal, double pathPercent)
  {
    double deltaX = goal.getX() - point.getX();
    double deltaY = goal.getY() - point.getY();
    
    double newX = point.getX() + deltaX * pathPercent;
    double newY = point.getY() + deltaY * pathPercent;
    
    point = new Point(newX, newY);
  }
  
  public void timePassed(double dt) {
    moveBall(dt);
  }
  

  private void moveBall(double dt)
  {
    Velocity scaledVelocity = velocity.scale(dt);
    

    Line ballTrajectory = new Line(point, scaledVelocity.applyToPoint(point));
    
    Collidable closestObject = state.getClosestCollision(ballTrajectory);
    double traveledPercentage;
    if (closestObject == null) {
      setLocation(ballTrajectory.end());
    }
    else {
      Collision collisionInfo = closestObject.getCollisionRectangle().collideCircle(ballTrajectory);
      

      moveTowardsLocation(collisionInfo.getPoint(), 0.9999D);
      

      Velocity newVelocity = closestObject.hit(this, collisionInfo, velocity);
      

      velocity = newVelocity;
      

      traveledPercentage = ballTrajectory.start().distance(collisionInfo.getPoint()) / ballTrajectory.length();
    }
  }
}
