package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;







public class LevelNameIndicator
  implements Sprite
{
  private int x;
  private int y;
  private String levelName;
  
  public LevelNameIndicator(int x, int y, String levelName)
  {
    this.x = x;
    this.y = y;
    this.levelName = levelName;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.setColor(Color.BLACK);
    

    surface.drawText(x, y, "Level Name: " + levelName, 15);
  }
  
  public void addTo(GameLevel level) {
    level.addSprite(this);
  }
  
  public void removeFrom(GameLevel level) {
    level.removeSprite(this);
  }
  
  public void timePassed(double dt) {}
}
