package game.listeners;

import game.component.Game;
import game.component.Block;
import game.component.Ball;
import utillity.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
  private Game game;
  private Counter remainingBalls;

  /**
   *
   * @param game
   * @param removedBlocks
   */
  public BallRemover(Game game, Counter remainingBalls) {
    this.game = game;
    this.remainingBalls = remainingBalls;
  }

  public void hitEvent(Block beingHit, Ball hitter) {
    remainingBalls.decrease(1);
    hitter.removeFromGame(game);
  }
}