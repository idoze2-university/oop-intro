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
   * default constructor.
   *
   * @param game           that holds the listener.
   * @param remainingBalls counter that indicates the amount of remaining balls.
   */
  public BallRemover(Game game, Counter remainingBalls) {
    this.game = game;
    this.remainingBalls = remainingBalls;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    remainingBalls.decrease(1);
    hitter.removeFromGame(game);
  }
}