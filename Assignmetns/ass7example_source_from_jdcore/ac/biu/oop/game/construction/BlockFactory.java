package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;
import java.util.HashMap;
import java.util.Map;








public class BlockFactory
{
  private Map<String, BlockCreator> registry;
  
  public BlockFactory()
  {
    registry = new HashMap();
  }
  
  public void registerCreator(String symbol, BlockCreator blockCreator) {
    registry.put(symbol, blockCreator);
  }
  
  public Block create(String symbol, int x, int y) {
    if (registry.containsKey(symbol)) {
      ((BlockCreator)registry.get(symbol)).create(x, y);
    }
    
    return null;
  }
}
