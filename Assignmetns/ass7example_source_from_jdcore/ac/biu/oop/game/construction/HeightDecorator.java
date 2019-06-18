package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;






public class HeightDecorator
  extends BlockCreatorDecorator
{
  private int height;
  
  public HeightDecorator(BlockCreator decorated, String propertyValue)
  {
    super(decorated);
    
    height = Integer.parseInt(propertyValue);
  }
  
  public Block create(int x, int y)
  {
    Block b = super.create(x, y);
    b.setHeight(height);
    return b;
  }
}
