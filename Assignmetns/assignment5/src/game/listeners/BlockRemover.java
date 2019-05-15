package game.listeners;

import game.component.Game;
import game.component.Block;
import game.component.CountingBlock;
import game.component.Ball;
import utillity.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener{
  private Game game;
  private Counter remainingBlocks;

  /**
   *
   * @param game
   * @param removedBlocks
   */
  public BlockRemover(Game game, Counter remainingBlocks) {
    this.game = game;
    this.remainingBlocks = remainingBlocks;
  }

  public void hitEvent(Block beingHit, Ball hitter) {
    if (((CountingBlock) beingHit).getHitPoints() == 0) {
      beingHit.removeHitListener(this);
      remainingBlocks.decrease(1);
      beingHit.removeFromGame(game);
    }
  }
}