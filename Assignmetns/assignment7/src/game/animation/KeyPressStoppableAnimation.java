package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * decorator-class that will wrap an existing animation and add a
 * "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

  private KeyboardSensor sensor;
  private String key;
  private Animation animation;
  private boolean isAlreadyPressed;

  /**
   * Default Constructor.
   *
   * @param sensor    KeyboardSensor to be used.
   * @param key       Key that'll end the animation.
   * @param animation Animation to wrap.
   */
  public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
    this.sensor = sensor;
    this.key = key;
    this.animation = animation;
    isAlreadyPressed = true;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    animation.doOneFrame(d);
  }

  @Override
  public boolean shouldStop() {
    if (isAlreadyPressed) {
      if (!sensor.isPressed(key)) {
        isAlreadyPressed = false;
      }
    } else if (sensor.isPressed(key)) {
      return true;
    }
    return false;
  }
  // ...
  // think about the implementations of doOneFrame and shouldStop.
}