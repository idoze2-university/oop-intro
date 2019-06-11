package game.component;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Point;
import utillity.Counter;

/**
 * The LivesIndicator class implements a sprite which displays the amount of
 * lives remaining to the game.
 */
class LivesIndicator implements Sprite {
  private Counter counter;
  private Point location;

  /**
   * default constructor.
   *
   * @param location      location in which to display the bar.
   * @param startingLives default lives.
   */
  public LivesIndicator(Point location, int startingLives) {
    counter = new Counter(startingLives);
    this.location = location;
  }

  /**
   * @return the value of the life counter.
   */
  public int getLives() {
    return counter.getValue();
  }

  /**
   * decrease the HP count.
   */
  public void decrease() {
    counter.decrease(1);
  }

  /**
   * increase the HP count.
   */
  public void increase() {
    counter.increase(1);
  }

  @Override
  public void drawOn(DrawSurface d) {
    int size = 9;
    d.setColor(Color.WHITE);
    d.drawText((int) location.getX(), (int) location.getY() + (size * 2) / 3, "Lives:", size * 2);
    for (int i = 0; i < counter.getValue(); i++) {
      d.setColor(Color.WHITE);
      d.fillCircle((int) location.getX() + 7 * size + (2 * size) * i, (int) location.getY(), size);
    }
  }

  @Override
  public void timePassed() {
    // nothing to do.
  }

}