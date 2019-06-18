package ac.biu.oop.game.levels;

import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;







public class SingleColorBackground
  implements Sprite
{
  private Color color;
  
  public SingleColorBackground(Color color)
  {
    this.color = color;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.setColor(color);
    surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
  }
  
  public void timePassed(double dt) {}
}
