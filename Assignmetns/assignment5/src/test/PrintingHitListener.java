package test;

import game.component.CountingBlock;
import game.listeners.HitListener;
import game.component.Block;
import game.component.Ball;

public class PrintingHitListener implements HitListener {

  public void hitEvent(Block beingHit, Ball hitter) {
    if (beingHit instanceof CountingBlock) {
      System.out.println("A Block with " + ((CountingBlock) beingHit).getHitPoints() + " points was hit.");
    }
  }
}