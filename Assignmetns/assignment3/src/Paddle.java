import java.awt.Color;

import biuoop.KeyboardSensor;

public class Paddle extends Block implements Sprite, Collidable {
  private KeyboardSensor keyboard;
  private double xLBound;
  private double xRBound;

  public Paddle(double screenWidth, double screenHeight, double width, double height, double screenPadding, Color color,
      KeyboardSensor keyboard) {
    super((screenWidth - width) / 2.0, screenHeight - screenPadding - height, width, height, color);
    xLBound = screenPadding;
    xRBound = screenWidth - screenPadding;
    this.keyboard = keyboard;
  }

  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    double retangle = 150;
    double retspeed = currentVelocity.getTrajectory().getLength();
    double colx = collisionPoint.getX();
    for (int i = 1; i < 5; i++) {
      if (colx >= collisionRectangle.getUpperLeft().getX() + i * (collisionRectangle.getWidth() / 5.0)) {
        retangle -= 30;
      }
    }
    return Velocity.fromAngleAndSpeed(retangle, retspeed);
  }

  public void timePassed() {
    double speed = 7.0;
    if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
      speed = Math.min(speed, Math.abs(collisionRectangle.getUpperLeft().getX() - xLBound));
      moveLeft(speed);
    }
    if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
      speed = Math.min(speed,
          Math.abs(collisionRectangle.getUpperLeft().getX() + collisionRectangle.getWidth() - xRBound));
      moveRight(speed);
    }
  }

  public void moveLeft(double dx) {
    moveRight(-dx);
  }

  public void moveRight(double dx) {
    collisionRectangle.move(dx, 0);
  }

  // Add this paddle to the game.
  public void addToGame(Game g) {
    g.addSprite(this);
    g.addCollidable(this);
  }
}