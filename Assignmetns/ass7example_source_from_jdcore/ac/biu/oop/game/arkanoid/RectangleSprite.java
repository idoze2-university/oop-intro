package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;






public class RectangleSprite
  implements Sprite
{
  private Color color;
  private Rectangle r;
  
  public RectangleSprite(Color color, Rectangle r)
  {
    this.color = color;
    this.r = r;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.setColor(color);
    surface.fillRectangle((int)r.getTopLeftPoint().getX(), (int)r.getTopLeftPoint().getY(), (int)r.getWidth(), (int)r.getHeight());
  }
  
  public void timePassed(double dt) {}
}
