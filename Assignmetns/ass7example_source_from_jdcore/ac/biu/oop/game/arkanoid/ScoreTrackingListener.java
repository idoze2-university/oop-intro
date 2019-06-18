package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.core.HitListener;







public class ScoreTrackingListener
  implements HitListener
{
  private Counter scoreCounter;
  
  public ScoreTrackingListener(Counter scoreCounter)
  {
    this.scoreCounter = scoreCounter;
  }
  

  public void hitEvent(Block beingHit, Ball hitter)
  {
    scoreCounter.increase(5);
    

    if (beingHit.getHitPoints() == 0) {
      scoreCounter.increase(10);
    }
  }
}
