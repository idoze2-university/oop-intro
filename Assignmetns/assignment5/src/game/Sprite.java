package game;
import biuoop.DrawSurface;

/**
 * The Sprite interface implements an object which could be drawn to the screen.
 *
 * @author zeiraid 322607177
 */
public interface Sprite {
  /**
   * Draws the block to drawsurface d.
   *
   * @param d the surface on which the object should be drawn.
   */
  void drawOn(DrawSurface d);

  /**
   * Invokes a tick event.
   */
  void timePassed();
}