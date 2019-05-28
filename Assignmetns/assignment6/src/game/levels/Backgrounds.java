package game.levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.component.Sprite;

/**
 * The Backgrounds class provides the implementations of the different
 * Backgrounds of each level.
 */
public class Backgrounds {
  /**
   * Background for Level 1: Direct Hit.
   */
  public static class DirectHit implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
      d.setColor(Color.BLACK);
      d.fillRectangle(0, 0, 800, 600);
      d.setColor(Color.RED);
      d.fillRectangle(190, 60, 10, 10);
      d.setColor(Color.blue);
      for (int i = 0; i < 3; i++) {
        int radius = 20 + i * 25;
        d.drawCircle(200, 70, radius);
      }
    }

    @Override
    public void timePassed() {
      // do nothing
    }
  }

  /**
   * Background for Level 1: Direct Hit.
   */
  public static class WideEasy implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed() {
      // do nothing
    }
  }

  /**
   * Background for Level 1: Direct Hit.
   */
  public static class Green3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed() {
      // do nothing
    }
  }

  /**
   * Background for Level 4: Direct Hit.
   */
  public static class FinalFour implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed() {
      // do nothing
    }
  }
}