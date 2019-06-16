package game.animation;

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

/**
 * The AnimationRunner class implements a runner which runs an animation in a
 * consistent FPS.
 */
public class AnimationRunner {
  private GUI gui;
  private int framesPerSecond;
  private Sleeper sleeper;

  /**
   * Default constructor.
   *
   * @param framesPerSecond frames per second to be used.
   * @param gui             gui to be used.
   */
  public AnimationRunner(int framesPerSecond, GUI gui) {
    this.sleeper = new Sleeper();
    this.gui = gui;
    setFPS(framesPerSecond);
  }

  /**
   * @param gui gui to be used.
   */
  public AnimationRunner(GUI gui) {
    this(60, gui);
  }

  /**
   * Sets framesPerSecond.
   *
   * @param fps value to be set.
   */
  public void setFPS(int fps) {
    this.framesPerSecond = fps;
  }

  /**
   * Runs the animation through the runner.
   *
   * @param animation The animation to be run.
   */
  public void run(Animation animation) {
    int millisecondsPerFrame = 1000 / framesPerSecond;
    while (!animation.shouldStop()) {
      long startTime = System.currentTimeMillis(); // timing
      DrawSurface d = gui.getDrawSurface();
      animation.doOneFrame(d);
      gui.show(d);

      long usedTime = System.currentTimeMillis() - startTime;
      long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
      if (milliSecondLeftToSleep > 0) {
        this.sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}