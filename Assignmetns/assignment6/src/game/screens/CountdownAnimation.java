package game.screens;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.animation.Animation;
import game.component.SpriteCollection;

/**
 *
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds
 * seconds, and on top of them it will show a countdown from countFrom back to
 * 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * secods, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

  private int curr;
  private double numOfSeconds;
  private int countFrom;
  private SpriteCollection gameScreen;

  /**
   * Default constructor.
   *
   * @param numOfSeconds overall delay time.
   * @param countFrom    Number to count from.
   * @param gameScreen   game screen to be displayed in the background.
   */
  public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
    this.numOfSeconds = numOfSeconds;
    this.countFrom = countFrom;
    curr = countFrom;
    this.gameScreen = gameScreen;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    gameScreen.drawAllOn(d);
    if (curr != 0) {
      d.drawText(400, 300, String.valueOf(curr), 10);
    }
    Sleeper s = new Sleeper();
    if (curr != countFrom) {
      s.sleepFor((long) (numOfSeconds * 1000 / countFrom));
    }
    curr--;
  }

  @Override
  public boolean shouldStop() {
    return curr == -1;
  }
}
