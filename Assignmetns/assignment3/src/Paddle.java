import java.awt.Color;
import biuoop.KeyboardSensor;

/**
 * The Paddle class extends the Block class and adds the functionallity of
 * boundry-instructed movement and a different implementation of hit().
 *
 * @author zeiraid 322607177
 */
public class Paddle extends Block {
  private KeyboardSensor keyboard;
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
   * @param color         color of the paddle.
   * @param keyboard      KeyboardSensor object which contains the information
   *                      about which keys are pressed.
   */
  public Paddle(double screenWidth, double screenHeight, double width, double height, double screenPadding, Color color,
      KeyboardSensor keyboard) {
    super((screenWidth - width) / 2.0, screenHeight - screenPadding - height, width, height, color);
    xLBound = screenPadding;
    xRBound = screenWidth - screenPadding;
    this.keyboard = keyboard;
  }

  /**
   * In order for the game to be more enjoyable, we require the following behavior
   * from the paddle: think of the paddle as having 5 equally-spaced regions. The
   * behavior of the ball's bounce depends on where it hits the paddle. Let's
   * denote the left-most region as 1 and the rightmost as 5 (so the middle region
   * is 3). If the ball hits the middle region (region 3), it should keep its
   * horizontal direction and only change its vertical one (like when hitting a
   * block). However, if we hit region 1, the ball should bounce back with an
   * angle of 300 degrees (-60), regardless of where it came from. Remember, angle
   * 0 = 360 is "up", so 300 means "a lot to the left". Similarly, for region 2 is
   * should bounce back 330 degrees (a little to the left), for region 4 it should
   * bounce in 30 degrees, and for region 5 in 60 degrees.
   *
   * @param collisionPoint  Point at which we collided with the object.
   * @param currentVelocity Velocity with which we collided with the object.
   * @return the new velocity expected after the hit
   */
  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
    double speed = 7.0;
    if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
      speed = Math.min(speed, Math.abs(getCollisionRectangle().getUpperLeft().getX() - xLBound));
      moveLeft(speed);
    }
    if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
      speed = Math.min(speed,
          Math.abs(getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth() - xRBound));
      moveRight(speed);
    }
  }
}