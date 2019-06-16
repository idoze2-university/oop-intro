package game.component;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The CountingBlock class extends the Block class and Adds the functionality of
 * counting hits.
 *
 * @author zeiraid 322607177
 */
public class CountingBlock extends Block {

  private int count;

  /**
   * default constructor.
   *
   * @param x         Upper Left Point's X Coordinate.
   * @param y         Upper Left Point's Y Coordinate
   * @param width     Block Rectangle Width.
   * @param height    Block Rectangle Height.
   * @param color     the color of the block.
   * @param baseCount the initial count of the block.
   */
  public CountingBlock(double x, double y, double width, double height, Color color, int baseCount) {
    super(x, y, width, height, color);
    count = baseCount;
  }

  /**
   * calculates the literal value of the hitpoint count, if 0 returns "X".
   *
   * @return literal value of the count.
   */
  public int getHitPoints() {
    return count;
  }

  /**
   * Draws the block to drawsurface d.
   *
   * @param d the surface on which the object should be drawn.
   */
  public void drawOn(DrawSurface d) {
    super.drawOn(d);
    Rectangle collisionRectangle = super.getCollisionRectangle();
    d.setColor(Color.BLACK);
    int x = (int) collisionRectangle.getUpperLeft().getX();
    int y = (int) collisionRectangle.getUpperLeft().getY();
    int width = (int) collisionRectangle.getWidth();
    int height = (int) collisionRectangle.getHeight();
    d.drawRectangle(x, y, width, height);
  }

  @Override
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    count--;
    return super.hit(hitter, collisionPoint, currentVelocity);

  }
}