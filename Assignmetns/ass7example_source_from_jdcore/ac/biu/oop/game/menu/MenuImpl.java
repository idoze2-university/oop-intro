package ac.biu.oop.game.menu;

import ac.biu.oop.game.animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MenuImpl<T>
  implements Menu<T>
{
  private static final int MENU_TITLE_LEFT = 50;
  private static final int MENU_TITLE_TOP = 50;
  private static final int MENU_TITLE_FONT_SIZE = 32;
  private static final int MENU_OPTIONS_Y_GAP = 40;
  private static final int MENU_OPTIONS_LEFT = 100;
  private static final int MENU_OPTIONS_TOP = 120;
  private static final int MENU_OPTIONS_FONT_SIZE = 24;
  private AnimationRunner runner;
  private KeyboardSensor keyboardSensor;
  private String title;
  private List<T> menuReturnValues;
  private List<String> menuItemNames;
  private List<String> menuItemKeys;
  private List<Boolean> isSubMenu;
  private List<Menu<T>> subMenus;
  private T status;
  private boolean done;
  
  public MenuImpl(String title, AnimationRunner runner, KeyboardSensor keyboardSensor)
  {
    this.title = title;
    this.runner = runner;
    this.keyboardSensor = keyboardSensor;
    
    menuItemKeys = new ArrayList();
    menuItemNames = new ArrayList();
    menuReturnValues = new ArrayList();
    isSubMenu = new ArrayList();
    subMenus = new ArrayList();
    
    reset();
  }
  
  public void addSelection(String key, String message, T returnVal)
  {
    menuItemKeys.add(key);
    menuItemNames.add(message);
    menuReturnValues.add(returnVal);
    isSubMenu.add(Boolean.valueOf(false));
    subMenus.add(null);
  }
  
  public T getStatus()
  {
    return status;
  }
  
  public void reset()
  {
    status = null;
    done = false;
  }
  
  public boolean shouldStop()
  {
    return status != null;
  }
  


  public void doOneFrame(DrawSurface ds, double dt)
  {
    ds.setColor(Color.LIGHT_GRAY);
    ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());
    

    ds.setColor(Color.BLACK);
    ds.drawText(51, 50, title, 32);
    ds.drawText(49, 50, title, 32);
    ds.drawText(50, 51, title, 32);
    ds.drawText(50, 49, title, 32);
    ds.setColor(Color.YELLOW);
    ds.drawText(50, 50, title, 32);
    

    for (int i = 0; i < menuItemNames.size(); i++) {
      int optionX = 100;
      int optionY = 120 + i * 40;
      String optionText = "(" + (String)menuItemKeys.get(i) + ") " + (String)menuItemNames.get(i);
      
      ds.setColor(Color.BLACK);
      ds.drawText(optionX + 1, optionY, optionText, 24);
      ds.drawText(optionX - 1, optionY, optionText, 24);
      ds.drawText(optionX, optionY + 1, optionText, 24);
      ds.drawText(optionX, optionY - 1, optionText, 24);
      
      ds.setColor(Color.GREEN);
      ds.drawText(optionX, optionY, optionText, 24);
    }
    

    for (int i = 0; i < menuReturnValues.size(); i++) {
      if (keyboardSensor.isPressed((String)menuItemKeys.get(i))) {
        if (!((Boolean)isSubMenu.get(i)).booleanValue()) {
          status = menuReturnValues.get(i);
          done = true; break;
        }
        Menu<T> subMenu = (Menu)subMenus.get(i);
        runner.play(subMenu);
        status = subMenu.getStatus();
        done = true;
        subMenu.reset();
        
        break;
      }
    }
  }
  
  public void addSubMenu(String key, String message, Menu<T> subMenu)
  {
    menuItemKeys.add(key);
    menuItemNames.add(message);
    menuReturnValues.add(null);
    isSubMenu.add(Boolean.valueOf(true));
    subMenus.add(subMenu);
  }
}
