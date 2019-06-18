package ac.biu.oop.game.construction;


public class DecoratorsFactory
{
  private static final String HEIGHT_PROPERTY = "height";
  
  private static final String WIDTH_PROPERTY = "width";
  
  private static final String HIT_POINTS_PROPERTY = "hit_points";
  
  private static final String FILL_PROPERTY_PREFIX = "fill";
  
  private static final String STROKE_PROPERTY_PREFIX = "stroke";
  
  public DecoratorsFactory() {}
  
  public BlockCreator decorate(BlockCreator creator, String propKey, String propValue)
  {
    if ("height".equals(propKey))
      return new HeightDecorator(creator, propValue);
    if ("width".equals(propKey))
      return new WidthDecorator(creator, propValue);
    if ("hit_points".equals(propKey))
      return new HitPointsDecorator(creator, propValue);
    if ((propKey.startsWith("fill")) || (propKey.startsWith("stroke")))
    {
      Integer hitPointsValue = null;
      boolean isFill = propKey.startsWith("fill");
      
      int dividerIndex = propKey.indexOf("-");
      
      if (dividerIndex != -1) {
        hitPointsValue = Integer.valueOf(Integer.parseInt(propKey.substring(dividerIndex + 1)));
      }
      
      return new DrawingDecorator(creator, propValue, isFill, hitPointsValue);
    }
    
    throw new RuntimeException("Unsupported property: " + propKey + " with value:" + propValue);
  }
}
