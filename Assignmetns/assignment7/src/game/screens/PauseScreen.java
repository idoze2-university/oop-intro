package game.screens;

import biuoop.DrawSurface;
import game.animation.Animation;

/**
 * The PauseScreen class implements the Animation interface and is a screen that
 * can be used as a break indicator.
 */
public class PauseScreen implements Animation {
  /**
   * Default constructor.
   *
   */
  public PauseScreen() {
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}