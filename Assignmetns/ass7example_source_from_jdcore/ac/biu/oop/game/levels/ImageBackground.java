package ac.biu.oop.game.levels;

import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.awt.Image;







public class ImageBackground
  implements Sprite
{
  private Image image;
  
  public ImageBackground(Image image)
  {
    this.image = image;
  }
  
  public void drawOn(DrawSurface surface)
  {
    surface.drawImage(0, 0, image);
  }
  
  public void timePassed(double dt) {}
}
