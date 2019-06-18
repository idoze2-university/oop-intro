package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.core.HitListener;






public class BlockRemover
  implements HitListener
{
  private Counter remainingBlocks;
  private GameLevel gameLevel;
  
  public BlockRemover(GameLevel gameLevel, Counter remainingBlocks)
  {
    this.remainingBlocks = remainingBlocks;
    this.gameLevel = gameLevel;
  }
  
  public void hitEvent(Block beingHit, Ball hitter)
  {
    if (beingHit.getHitPoints() == 0) {
      beingHit.removeHitListener(this);
      beingHit.removeFrom(gameLevel);
      remainingBlocks.decrease(1);
    }
  }
}
