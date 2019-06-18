package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;






public abstract class BlockCreatorDecorator
  implements BlockCreator
{
  private BlockCreator decorated;
  
  public BlockCreatorDecorator(BlockCreator decorated)
  {
    this.decorated = decorated;
  }
  
  public Block create(int x, int y)
  {
    return decorated.create(x, y);
  }
}
