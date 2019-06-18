package game.screens;

import java.util.ArrayList;
import java.util.HashMap;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
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
  private HashMap<String,Menu<T>> keyToSubMenu;
  private AnimationRunner runner;

  /**
   * default constructor.
   *
   * @param sensor the keyboard sensor to read the keys.
   */
  public MenuAnimation(AnimationRunner runner,KeyboardSensor sensor) {
    this.runner = runner;
    this.sensor = sensor;
    status = null;
    count = 0;
    keys = new ArrayList<String>();
    messages = new ArrayList<String>();
    statusList = new ArrayList<T>();
    keyToSubMenu = new HashMap<String,Menu<T>>();

  }

  @Override
  public T getStatus() {
    T temp = status;
    status = null;
    return temp;
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
        if(keyToSubMenu.containsKey(key)){
          runner.run(keyToSubMenu.get(key));
          status = keyToSubMenu.get(key).getStatus();
          break;
        }
        else
        {
          status = statusList.get(i);
        }
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

  @Override
  public void addSubMenu(String key, String message, Menu<T> subMenu) {
    addSelection(key, message, null);
    keyToSubMenu.put(key, subMenu);
  }

}