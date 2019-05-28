package game.collision;

import game.listeners.HitListener;

/**
 * The HitNotifier interface indicates that objects that implement it send
 * notifications when they are being hit.
 */
public interface HitNotifier {
  /**
   * Add hl as a listener to hit events.
   *
   * @param hl Listener to add.
   */
  void addHitListener(HitListener hl);

  /**
   * Remove hl from the list of listeners to hit events.
   *
   * @param hl Listener to remove.
   */
  void removeHitListener(HitListener hl);
}