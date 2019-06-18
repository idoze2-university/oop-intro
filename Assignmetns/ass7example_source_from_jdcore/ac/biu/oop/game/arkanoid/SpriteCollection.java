package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;








public class SpriteCollection
{
  private List<Sprite> sprites;
  
  public SpriteCollection()
  {
    sprites = new ArrayList();
  }
  
  public void addSprite(Sprite s) {
    sprites.add(s);
  }
  
  public void removeSprite(Sprite s) {
    sprites.remove(s);
  }
  
  public void notifyAllTimePassed(double dt)
  {
    for (Sprite s : new ArrayList(sprites)) {
      s.timePassed(dt);
    }
  }
  
  public void drawAllOn(DrawSurface d)
  {
    for (Sprite s : new ArrayList(sprites)) {
      s.drawOn(d);
    }
  }
}
