package game.component;

import java.awt.Color;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Velocity;

/**
 * The Paddle class extends the Block class and adds the functionallity of
 * boundry-instructed movement and a different implementation of hit().
 *
 * @author zeiraid 322607177
 */
public class Paddle extends Block {
  private KeyboardSensor keyboard;
  private double speed;
  private double xLBound;
  private double xRBound;

  /**
   * Default constructor.
   *
   * @param screenWidth   width of the screen bounding the paddle movement.
   * @param screenHeight  height of the screen bounding the paddle movement.
   * @param width         width of the paddle.
   * @param height        height of the paddle.
   * @param screenPadding spacing to keep with the screen bounds.
   * @param keyboard      KeyboardSensor object which contains the information
   *                      about which keys are pressed.
   * @param speed         the speed in which the paddle should move.
   */
  public Paddle(double screenWidth, double screenHeight, double width, double height, double screenPadding,
      KeyboardSensor keyboard, double speed) {
    super((screenWidth - width) / 2.0, screenHeight - screenPadding - height, width, height, Color.ORANGE);
    xLBound = screenPadding;
    xRBound = screenWidth - screenPadding;
    this.keyboard = keyboard;
    this.speed = speed;
  }

  @Override
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    double retangle = 150;
    double retspeed = currentVelocity.getTrajectory().getLength();
    double colx = collisionPoint.getX();
    for (int i = 1; i < 5; i++) {
      if (colx >= getCollisionRectangle().getUpperLeft().getX() + i * (getCollisionRectangle().getWidth() / 5.0)) {
        retangle -= 30;
      }
    }
    return Velocity.fromAngleAndSpeed(retangle, retspeed);
  }

  /**
   * Invokes a tick event, moves according to keypresses.
   */
  public void timePassed() {
    double fixedspeed = 0;
    if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
      fixedspeed = Math.min(speed, Math.abs(getCollisionRectangle().getUpperLeft().getX() - xLBound));
      moveLeft(fixedspeed);
    }
    if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
      fixedspeed = Math.min(speed,
          Math.abs(getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth() - xRBound));
      moveRight(fixedspeed);
    }
  }
}