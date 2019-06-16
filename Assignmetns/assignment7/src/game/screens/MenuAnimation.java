package game.screens;

import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import ui.Menu;

/**
 * The MenuAnimation provides an implementation of a Menu which can be run and
 * displayed through an animationrunner.
 *
 * @param <T> The type for each entry's status.
 */
public class MenuAnimation<T> implements Menu<T>, Animation {

  private T status;
  private KeyboardSensor sensor;
  private int count;
  private ArrayList<String> keys;
  private ArrayList<String> messages;
  private ArrayList<T> statusList;

  /**
   * default constructor.
   */
  public MenuAnimation(KeyboardSensor sensor) {
    this.sensor = sensor;
    status = null;
    count = 0;
    keys = new ArrayList<String>();
    messages = new ArrayList<String>();
    statusList = new ArrayList<T>();

  }

  @Override
  public T getStatus() {
    return status;
  }

  @Override
  public void doOneFrame(DrawSurface d) {

    for (int i = 0; i < count; i++) {
      String label = String.format("%s - %s", keys.get(i), messages.get(i));
      d.drawText(30, 40 * (i + 3), label, 20);
    }

    status = null;
    int i = 0;
    for (String key : keys) {
      if (sensor.isPressed(key)) {
        status = statusList.get(i);
      }
      i++;
    }

  }

  @Override
  public boolean shouldStop() {
    return status != null;
  }

  @Override
  public void addSelection(String key, String message, T returnVal) {
    keys.add(count, key);
    messages.add(count, message);
    statusList.add(count, returnVal);
    count++;

  }

}