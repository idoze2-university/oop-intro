package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;








public class ScoreIndicator
  implements Sprite
{
  private int x;
  private int y;
  private Counter scoreCounter;
  
  public ScoreIndicator(int x, int y, Counter scoreCounter)
  {
    this.x = x;
    this.y = y;
    this.scoreCounter = scoreCounter;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.setColor(Color.BLACK);
    

    surface.drawText(x, y, "Score: " + scoreCounter.getValue(), 15);
  }
  
  public void addTo(GameLevel level)
  {
    level.addSprite(this);
  }
  
  public void removeFrom(GameLevel level) {
    level.removeSprite(this);
  }
  
  public void timePassed(double dt) {}
}
