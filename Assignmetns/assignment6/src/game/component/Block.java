package game.component;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import game.collision.Collidable;
import game.collision.HitNotifier;
import game.listeners.HitListener;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * The Block class implements a block object which implements the Collidable
 * interface.
 *
 * @author zeiraid 322607177
 */
public class Block implements Collidable, Sprite, HitNotifier {

  private Rectangle collisionRectangle;
  private Color color;
  private GameLevel game;
  private ArrayList<HitListener> hitListeners;

  /**
   * Default constructor.
   *
   * @param collisionRectangle the Collision rectangle of the block.
   * @param color              the color of the block.
   */
  public Block(Rectangle collisionRectangle, Color color) {
    hitListeners = new ArrayList<HitListener>();
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

  @Override
  public Rectangle getCollisionRectangle() {
    return collisionRectangle;
  }

  /**
   * @return The game which contains the block.
   */
  protected GameLevel getGame() {
    return game;
  }

  @Override
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
    notifyHit(hitter);
    return velocity;
  }

  @Override
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

  @Override
  public void timePassed() {
    // nothing to do.
  }

  /**
   * Adds the block to g's sprite and collidable object arrays.
   *
   * @param g the game to which the object should be added.
   */
  public void addToGame(GameLevel g) {
    this.game = g;
    g.addSprite(this);
    g.addCollidable(this);
  }

  /**
   * removes the block from the game.
   *
   * @param g The game to be removed from.
   */
  public void removeFromGame(GameLevel g) {
    g.removeSprite(this);
    g.removeCollidable(this);
  }

  @Override
  public void addHitListener(HitListener hl) {
    hitListeners.add(hl);
  }

  @Override
  public void removeHitListener(HitListener hl) {
    hitListeners.remove(hl);
  }

  /**
   * Notifies all listeners that a hit has been made.
   *
   * @param hitter ball that made the hit.
   */
  private void notifyHit(Ball hitter) {
    // Make a copy of the hitListeners before iterating over them.
    ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
    // Notify all listeners about a hit event:
    for (HitListener hl : listeners) {
      hl.hitEvent(this, hitter);
    }
  }
}