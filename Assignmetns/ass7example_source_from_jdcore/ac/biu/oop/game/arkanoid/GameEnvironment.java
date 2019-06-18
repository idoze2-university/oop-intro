package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Collidable;
import ac.biu.oop.game.geometry.Collision;
import ac.biu.oop.game.geometry.Line;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;






public class GameEnvironment
{
  private List<Collidable> collidableList;
  
  public GameEnvironment()
  {
    collidableList = new ArrayList();
  }
  
  public void addCollidable(Collidable c)
  {
    collidableList.add(c);
  }
  
  public void removeCollidable(Collidable c)
  {
    collidableList.remove(c);
  }
  




  public Collidable getClosestCollision(Line trajectory)
  {
    double closestDistance = Double.MAX_VALUE;
    Collidable closestObject = null;
    
    for (Collidable go : collidableList) {
      Collision intersection = go.getCollisionRectangle().collideCircle(trajectory);
      
      if (intersection != null) {
        double distance = trajectory.start().distance(intersection.getPoint());
        if (distance < closestDistance) {
          closestDistance = distance;
          closestObject = go;
        }
      }
    }
    
    return closestObject;
  }
}
