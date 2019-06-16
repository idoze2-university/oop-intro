package game.listeners;

import utillity.Counter;
import game.component.Block;
import game.component.CountingBlock;
import game.component.Ball;

/**
 * The ScoreTrackingListener implements the HitListener and listens to hits in
 * order to keep track of the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
  private Counter currentScore;

  /**
   * default constructor.
   *
   * @param scoreCounter counter of score.
   */
  public ScoreTrackingListener(Counter scoreCounter) {
    this.currentScore = scoreCounter;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    currentScore.increase(5);
    if (((CountingBlock) beingHit).getHitPoints() == 0) {
      currentScore.increase(10);
    }
  }
}