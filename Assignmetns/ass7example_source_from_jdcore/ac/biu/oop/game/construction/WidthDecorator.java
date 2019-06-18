package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;






public class WidthDecorator
  extends BlockCreatorDecorator
{
  private int width;
  
  public WidthDecorator(BlockCreator decorated, String propertyValue)
  {
    super(decorated);
    
    width = Integer.parseInt(propertyValue);
  }
  
  public Block create(int x, int y)
  {
    Block b = super.create(x, y);
    b.setWidth(width);
    return b;
  }
}
