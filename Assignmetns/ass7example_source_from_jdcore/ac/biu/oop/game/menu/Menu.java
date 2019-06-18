package ac.biu.oop.game.menu;

import ac.biu.oop.game.animation.Animation;

public abstract interface Menu<T>
  extends Animation
{
  public abstract void reset();
  
  public abstract void addSelection(String paramString1, String paramString2, T paramT);
  
  public abstract T getStatus();
  
  public abstract void addSubMenu(String paramString1, String paramString2, Menu<T> paramMenu);
}
