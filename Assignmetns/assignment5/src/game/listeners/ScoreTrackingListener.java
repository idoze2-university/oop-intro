package game.listeners;

import utillity.Counter;
import game.component.Block;
import game.component.CountingBlock;
import game.component.Ball;

public class ScoreTrackingListener implements HitListener {
  private Counter currentScore;

  public ScoreTrackingListener(Counter scoreCounter) {
    this.currentScore = scoreCounter;
  }

  public void hitEvent(Block beingHit, Ball hitter) {
    currentScore.increase(5);
    if (((CountingBlock) beingHit).getHitPoints() == 0) {
      currentScore.increase(10);
    }
  }
}