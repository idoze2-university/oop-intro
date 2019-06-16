package game.listeners;

import game.component.Ball;
import game.component.Block;

/**
 * The HitListener interface provides a protocol for implementing a Listener
 * that holds an event in which a ball hits a block.
 */
public interface HitListener {
  /**
   * This method is called whenever the beingHit object is hit.
   *
   * @param beingHit Block that has been hit.
   * @param hitter   the Ball that's doing the hitting.
   */
  void hitEvent(Block beingHit, Ball hitter);
}