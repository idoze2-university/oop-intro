package ac.biu.oop.game.animation;

import biuoop.DrawSurface;

public abstract interface Animation
{
  public abstract boolean shouldStop();
  
  public abstract void doOneFrame(DrawSurface paramDrawSurface, double paramDouble);
}
