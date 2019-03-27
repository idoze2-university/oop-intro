import biuoop.DrawSurface;
import java.util.ArrayList;

public class SpriteCollection {
  private ArrayList<Sprite> sprites;

  public SpriteCollection() {
    sprites = new ArrayList<Sprite>();
  }

  public void addSprite(Sprite s) {
    sprites.add(s);
  }

  public int count()
{
  return sprites.size();
}
  public void remove(Sprite s) {
    sprites.remove(s);
  }

  // call timePassed() on all sprites.
  public void notifyAllTimePassed() {
    try {
      for (Sprite s : sprites) {
        s.timePassed();
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    ;
  }

  // call drawOn(d) on all sprites.
  public void drawAllOn(DrawSurface d) {
    for (Sprite s : sprites) {
      s.drawOn(d);
    }
    ;
  }
}