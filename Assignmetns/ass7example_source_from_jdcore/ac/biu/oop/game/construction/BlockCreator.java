package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;

public abstract interface BlockCreator
{
  public abstract Block create(int paramInt1, int paramInt2);
}
