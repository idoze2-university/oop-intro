package ac.biu.oop.game.core;

public abstract interface HitNotifier
{
  public abstract void addHitListener(HitListener paramHitListener);
  
  public abstract void removeHitListener(HitListener paramHitListener);
}
