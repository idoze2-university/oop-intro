package game.screens;

import biuoop.DrawSurface;
import game.animation.Animation;

/**
 * The PauseScreen class implements the Animation interface and is a screen that
 * can be used as a break indicator.
 */
public class EndScreen implements Animation {
  private boolean win;
  private int score;

  /**
   * Default constructor.
   *
   * @param win   wether or not the game was won.
   * @param score score the game ended with.
   */
  public EndScreen(boolean win, int score) {
    this.win = win;
    this.score = score;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    String message = "Game Over. Your score is ";
    if (win) {
      message = "You Win! Your score is ";
    }
    d.drawText(10, d.getHeight() / 2, message + String.valueOf(score), 32);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}