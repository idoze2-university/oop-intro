package game.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;

/**
 * The PauseScreen class implements the Animation interface and is a screen that
 * can be used as a break indicator.
 */
public class PauseScreen implements Animation {
  private KeyboardSensor keyboard;
  private boolean stop;

  /**
   * Default constructor.
   *
   * @param k KeyBoardSensor to be used as a pause key indicator.
   */
  public PauseScreen(KeyboardSensor k) {
    this.keyboard = k;
    this.stop = false;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
      this.stop = true;
    }
  }

  @Override
  public boolean shouldStop() {
    return this.stop;
  }
}