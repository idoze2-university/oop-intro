package ac.biu.oop.game.core;

import biuoop.DrawSurface;

public abstract interface Sprite
{
  public abstract void drawOn(DrawSurface paramDrawSurface);
  
  public abstract void timePassed(double paramDouble);
}
