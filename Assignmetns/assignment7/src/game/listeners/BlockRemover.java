package game.listeners;

import game.component.GameLevel;
import game.component.Block;
import game.component.CountingBlock;
import game.component.Ball;
import utillity.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
  private GameLevel game;
  private Counter remainingBlocks;

  /**
   * default constructor.
   *
   * @param game            that holds the listener.
   * @param remainingBlocks counter that indicates the amount of remaining balls.
   */
  public BlockRemover(GameLevel game, Counter remainingBlocks) {
    this.game = game;
    this.remainingBlocks = remainingBlocks;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    if (((CountingBlock) beingHit).getHitPoints() == 0) {
      beingHit.removeHitListener(this);
      remainingBlocks.decrease(1);
      beingHit.removeFromGame(game);
    }
  }
}