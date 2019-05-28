package game.animation;

import biuoop.DrawSurface;

/**
 * The Animation interface provides a protocol for implementing an animation
 * consisting of frames.
 */
public interface Animation {
  /**
   * Call the next frame to be drawn of DrawSurface d.
   *
   * @param d The DrawSurface on which to draw the frame.
   */
  void doOneFrame(DrawSurface d);

  /**
   * @return Wether or not the animation should pause.
   */
  boolean shouldStop();
}