package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.core.HitListener;







public class BallRemover
  implements HitListener
{
  private GameLevel gameLevel;
  private Counter ballsCounter;
  
  public BallRemover(GameLevel gameLevel, Counter ballsCounter)
  {
    this.gameLevel = gameLevel;
    this.ballsCounter = ballsCounter;
  }
  
  public void hitEvent(Block beingHit, Ball hitter)
  {
    hitter.removeFrom(gameLevel);
    ballsCounter.decrease(1);
  }
}
