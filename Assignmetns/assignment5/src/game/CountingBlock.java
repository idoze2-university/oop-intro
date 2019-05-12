package game;

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
  private String getStringValue() {
    String value = "X";
    if (count > 0) {
      value = String.valueOf(count);
    }
    return value;
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

    // Print the count literal value in the middle.
    int textSize = 13;
    Point pt = collisionRectangle.getUpperLeft().add((collisionRectangle.getWidth() - textSize) / 2.0,
        (collisionRectangle.getHeight() + textSize) / 2.0);

    d.drawText((int) pt.getX(), (int) pt.getY(), getStringValue(), textSize);
  }

  /**
   * Notify the object that we collided with it at collisionPoint with a given
   * velocity. The return is the new velocity expected after the hit (based on the
   * force the object inflicted on us).
   *
   * @param collisionPoint  Point at which we collided with the object.
   * @param currentVelocity Velocity with which we collided with the object.
   * @return the new velocity expected after the hit
   */
  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    if (count > 0) {
      count--;
    }
    return super.hit(collisionPoint, currentVelocity);

  }
}