package ac.biu.oop.game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;







public class KeyPressStoppableAnimation
  implements Animation
{
  private Animation decorated;
  private boolean done;
  private KeyboardSensor keyboardSensor;
  private String key;
  private boolean ignoreNextPress;
  private boolean isFirstFrame;
  
  public KeyPressStoppableAnimation(Animation decorated, KeyboardSensor keyboardSensor, String key)
  {
    this.decorated = decorated;
    done = false;
    this.key = key;
    this.keyboardSensor = keyboardSensor;
    isFirstFrame = true;
    ignoreNextPress = false;
  }
  
  public boolean shouldStop()
  {
    return done;
  }
  

  public void doOneFrame(DrawSurface ds, double dt)
  {
    if (isFirstFrame) {
      ignoreNextPress = keyboardSensor.isPressed(key);
      isFirstFrame = false;
    }
    
    decorated.doOneFrame(ds, dt);
    
    if (keyboardSensor.isPressed(key)) {
      if (!ignoreNextPress) {
        done = true;
      }
    } else {
      ignoreNextPress = false;
    }
  }
}
