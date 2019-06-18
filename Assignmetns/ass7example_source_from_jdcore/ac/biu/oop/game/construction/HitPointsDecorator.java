package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;






public class HitPointsDecorator
  extends BlockCreatorDecorator
{
  private int hitPoints;
  
  public HitPointsDecorator(BlockCreator decorated, String propertyValue)
  {
    super(decorated);
    
    hitPoints = Integer.parseInt(propertyValue);
  }
  
  public Block create(int x, int y)
  {
    Block b = super.create(x, y);
    b.setHitPoints(hitPoints);
    return b;
  }
}
