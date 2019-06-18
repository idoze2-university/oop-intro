package biuoop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;



class KeyboardSensorImpl
  implements KeyListener, KeyboardSensor
{
  private static final Map<Integer, String> KEY_CODE_TO_BUTTON_MAP = Collections.unmodifiableMap(new HashMap() {});
  



  private Set<String> pressedButtons;
  




  public KeyboardSensorImpl(JFrame frame)
  {
    pressedButtons = Collections.synchronizedSet(new HashSet());
    frame.addKeyListener(this);
  }
  


  public void keyTyped(KeyEvent keyEvent) {}
  

  public void keyPressed(KeyEvent keyEvent)
  {
    if (KEY_CODE_TO_BUTTON_MAP.keySet().contains(Integer.valueOf(keyEvent.getKeyCode()))) {
      pressedButtons.add(KEY_CODE_TO_BUTTON_MAP.get(Integer.valueOf(keyEvent.getKeyCode())));
    } else {
      pressedButtons.add(String.valueOf(keyEvent.getKeyChar()));
    }
  }
  

  public void keyReleased(KeyEvent keyEvent)
  {
    if (KEY_CODE_TO_BUTTON_MAP.keySet().contains(Integer.valueOf(keyEvent.getKeyCode()))) {
      pressedButtons.remove(KEY_CODE_TO_BUTTON_MAP.get(Integer.valueOf(keyEvent.getKeyCode())));
    } else {
      pressedButtons.remove(String.valueOf(keyEvent.getKeyChar()));
    }
  }
  
  public boolean isPressed(String c) {
    return pressedButtons.contains(c);
  }
}
