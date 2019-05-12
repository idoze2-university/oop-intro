package game;

import biuoop.DrawSurface;
import java.awt.Color;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The Block class implements a block object which implements the Collidable
 * interface.
 *
 * @author zeiraid 322607177
 */
class Block implements Collidable, Sprite {

  private Rectangle collisionRectangle;
  private Color color;
  private Game game;

  /**
   * Default constructor.
   *
   * @param collisionRectangle the Collision rectangle of the block.
   * @param color              the color of the block.
   */
  public Block(Rectangle collisionRectangle, Color color) {
    this.collisionRectangle = collisionRectangle;
    this.color = color;
  }

  /**
   * The overloading constructor.
   *
   * @param x      Upper Left Point's X Coordinate.
   * @param y      Upper Left Point's Y Coordinate
   * @param width  Block Rectangle Width.
   * @param height Block Rectangle Height.
   * @param color  the color of the block.
   */
  public Block(double x, double y, double width, double height, Color color) {
    this(new Rectangle(x, y, width, height), color);
  }

  /**
   * @return the "collision shape" of the object.
   */
  public Rectangle getCollisionRectangle() {
    return collisionRectangle;
  }

  /**
   * @return The game which contains the block.
   */
  protected Game getGame() {
    return game;
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
    Velocity velocity = currentVelocity;
    if (collisionPoint.getX() <= collisionRectangle.getUpperLeft().getX()) {
      velocity = new Velocity(-Math.abs(velocity.getDx()), velocity.getDy());
    } else if (collisionPoint.getX() >= collisionRectangle.getUpperLeft().getX() + collisionRectangle.getWidth()) {
      velocity = new Velocity(Math.abs(velocity.getDx()), velocity.getDy());
    }
    if (collisionPoint.getY() <= collisionRectangle.getUpperLeft().getY() + 0.00001) {
      velocity = new Velocity(velocity.getDx(), -Math.abs(velocity.getDy()));
    } else if (collisionPoint.getY() + 0.00001 >= collisionRectangle.getUpperLeft().getY()
        + collisionRectangle.getHeight()) {
      velocity = new Velocity(velocity.getDx(), Math.abs(velocity.getDy()));
    }
    return velocity;
  }

  /**
   * Draws the block to drawsurface d.
   *
   * @param d the surface on which the object should be drawn.
   */
  public void drawOn(DrawSurface d) {
    d.setColor(color);
    int x = (int) collisionRectangle.getUpperLeft().getX();
    int y = (int) collisionRectangle.getUpperLeft().getY();
    int width = (int) collisionRectangle.getWidth();
    int height = (int) collisionRectangle.getHeight();
    d.fillRectangle(x, y, width, height);
  }

  /**
   * moves the object delta times to the left.
   *
   * @param dx the distance to move.
   */
  public void moveLeft(double dx) {
    moveRight(-dx);
  }

  /**
   * moves the object delta times to the right.
   *
   * @param dx the distance to move.
   */
  public void moveRight(double dx) {
    collisionRectangle.move(dx, 0);
  }

  /**
   * Invokes a tick event.
   */
  public void timePassed() {
    // nothing to do.
  }

  /**
   * Adds the block to g's sprite and collidable object arrays.
   *
   * @param g the game to which the object should be added.
   */
  public void addToGame(Game g) {
    this.game = g;
    g.addSprite(this);
    g.addCollidable(this);
  }
}