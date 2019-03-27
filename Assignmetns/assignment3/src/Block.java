import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Block class implements a block object which implements the Collidable
 * interface.
 */
class Block implements Collidable, Sprite {

  protected Rectangle collisionRectangle;
  protected Color color;
  protected Game game;

  /**
   * Default constructor.
   *
   * @param collisionRectangle the Collision rectangle of the block.
   */
  public Block(Rectangle collisionRectangle, Color color) {
    this.collisionRectangle = collisionRectangle;
    this.color = color;
  }

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

  public void drawOn(DrawSurface d) {
    d.setColor(color);
    int x = (int) collisionRectangle.getUpperLeft().getX();
    int y = (int) collisionRectangle.getUpperLeft().getY();
    int width = (int) collisionRectangle.getWidth();
    int height = (int) collisionRectangle.getHeight();
    d.fillRectangle(x, y, width, height);
  }

  public void timePassed() {
    // nothing to do.
  }
  public void addToGame(Game g) {
    this.game = g;
    g.addSprite(this);
    g.addCollidable(this);
  }
}