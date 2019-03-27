import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The SpriteCollection class implements a dynamic collection of sprites which
 * can be interacted.
 */
public class SpriteCollection {
  private ArrayList<Sprite> sprites;

  /**
   * the default constructor.
   */
  public SpriteCollection() {
    sprites = new ArrayList<Sprite>();
  }

  /**
   * adds a sprite to the collection.
   *
   * @param s the sprite to add.
   */
  public void addSprite(Sprite s) {
    sprites.add(s);
  }

  /**
   * @return the amount of sprites in the collection.
   */
  public int count() {
    return sprites.size();
  }

  /**
   * call timePassed() on all sprites.
   */
  public void notifyAllTimePassed() {
    for (int i = 0; i < sprites.size(); i++) {
      Sprite s = sprites.get(i);
      s.timePassed();
    }
  }

  /**
   * call drawOn(d) on all sprites.
   *
   * @param d The drawSurface to draw the sprites to.
   */
  public void drawAllOn(DrawSurface d) {
    for (Sprite s : sprites) {
      s.drawOn(d);
    }
  }
}