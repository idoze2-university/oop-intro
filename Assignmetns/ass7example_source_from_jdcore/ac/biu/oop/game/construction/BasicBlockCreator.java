package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;





public class BasicBlockCreator
  implements BlockCreator
{
  public BasicBlockCreator() {}
  
  public Block create(int x, int y)
  {
    return new Block(x, y);
  }
}
