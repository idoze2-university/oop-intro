package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Collidable;
import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.core.Velocity;
import ac.biu.oop.game.geometry.Collision;
import ac.biu.oop.game.geometry.EdgeType;
import ac.biu.oop.game.geometry.Line;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;












public class Paddle
  implements Sprite, Collidable
{
  private Rectangle rectangle;
  private int minX;
  private int maxX;
  private KeyboardSensor keyboard;
  private String leftButton;
  private String rightButton;
  private int speed;
  
  public Paddle(int x, int y, int width, int height, int minX, int maxX, KeyboardSensor keyboard, String leftButton, String rightButton, int speed)
  {
    this.minX = minX;
    this.maxX = maxX;
    this.speed = speed;
    
    rectangle = new Rectangle(x, y, width, height);
    
    this.keyboard = keyboard;
    this.leftButton = leftButton;
    this.rightButton = rightButton;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.setColor(Color.ORANGE);
    surface.fillRectangle((int)getX(), (int)getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
    surface.setColor(Color.BLACK);
    surface.drawRectangle((int)getX(), (int)getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
  }
  
  public void moveLeft(double dt) {
    int dx = (int)(speed * dt);
    
    setX(Math.max(getX() - dx, minX));
  }
  
  public void moveRight(double dt) {
    int dx = (int)(speed * dt);
    
    setX(Math.min(getX() + dx, maxX));
  }
  
  public void timePassed(double dt) {
    if (keyboard.isPressed(leftButton)) {
      moveLeft(dt);
    }
    if (keyboard.isPressed(rightButton)) {
      moveRight(dt);
    }
  }
  
  public void setX(double x) {
    double y = rectangle.getTopLeftPoint().getY();
    double width = rectangle.getWidth();
    double height = rectangle.getHeight();
    rectangle = new Rectangle(x, y, width, height);
  }
  
  public void addTo(GameLevel container) {
    container.addCollidable(this);
    container.addSprite(this);
  }
  
  public void removeFrom(GameLevel container) {
    container.removeCollidable(this);
    container.removeSprite(this);
  }
  
  public Rectangle getCollisionRectangle()
  {
    return rectangle;
  }
  
  public double getX() {
    return rectangle.getTopLeftPoint().getX();
  }
  
  public double getY() {
    return rectangle.getTopLeftPoint().getY();
  }
  

  public Velocity hit(Ball hitter, Collision collisionInfo, Velocity velocity)
  {
    if (collisionInfo.getEdgeType() == EdgeType.TOP)
    {
      double distanceFromLeft = collisionInfo.getPoint().distance(getCollisionRectangle().getTopLeftPoint());
      
      double regionSize = rectangle.getTopEdge().length() / 5.0D;
      
      int region = (int)(distanceFromLeft / regionSize);
      
      switch (region) {
      case 0: 
        return velocity.newAngle(-50.0D);
      case 1: 
        return velocity.newAngle(-25.0D);
      case 2: 
        return velocity.deflect(1.0D, -1.0D);
      case 3: 
        return velocity.newAngle(25.0D);
      case 4: 
        return velocity.newAngle(50.0D);
      }
      
      return velocity.newAngle(50.0D);
    }
    

    return velocity.deflect(-1.0D, 1.0D);
  }
}
