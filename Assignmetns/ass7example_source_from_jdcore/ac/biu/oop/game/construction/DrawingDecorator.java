package ac.biu.oop.game.construction;

import ac.biu.oop.game.arkanoid.Block;
import ac.biu.oop.game.arkanoid.Block.Drawer;
import ac.biu.oop.game.arkanoid.Block.FillDrawer;
import ac.biu.oop.game.arkanoid.Block.ImageDrawer;
import ac.biu.oop.game.arkanoid.Block.StrokeDrawer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import javax.imageio.ImageIO;









public class DrawingDecorator
  extends BlockCreatorDecorator
{
  private static final String RGB_PREFIX = "color(RGB(";
  private static final String RGB_POSTFIX = "))";
  private static final String COLOR_PREFIX = "color(";
  private static final String COLOR_POSTFIX = ")";
  private static final String IMAGE_PREFIX = "image(";
  private static final String IMAGE_POSTFIX = ")";
  private boolean isFill;
  private Integer hitPoints;
  private Block.Drawer drawer;
  
  public DrawingDecorator(BlockCreator decorated, String propertyValue, boolean isFill, Integer hitPoints)
  {
    super(decorated);
    
    this.hitPoints = hitPoints;
    this.isFill = isFill;
    


    drawer = parseDrawer(propertyValue, isFill);
  }
  
  private Block.Drawer parseDrawer(String propValue, boolean isFill)
  {
    Block.Drawer result = null;
    
    if ((propValue.startsWith("color(RGB(")) && (propValue.endsWith("))"))) {
      String param = extractParam(propValue, "color(RGB(", "))");
      String[] parts = param.split(",");
      int r = Integer.parseInt(parts[0].trim());
      int g = Integer.parseInt(parts[1].trim());
      int b = Integer.parseInt(parts[2].trim());
      Color color = new Color(r, g, b);
      
      if (isFill) {
        result = new Block.FillDrawer(color);
      } else {
        result = new Block.StrokeDrawer(color);
      }
    }
    else if ((propValue.startsWith("color(")) && (propValue.endsWith(")"))) {
      String param = extractParam(propValue, "color(", ")");
      Color color = null;
      try {
        Field field = Color.class.getField(param);
        color = (Color)field.get(null);
      } catch (NoSuchFieldException e) {
        throw new RuntimeException("Unsupported color name: " + param);
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Unsupported color name: " + param);
      }
      
      if (isFill) {
        result = new Block.FillDrawer(color);
      } else {
        result = new Block.StrokeDrawer(color);
      }
    } else {
      if ((propValue.startsWith("image(")) && (propValue.endsWith(")"))) {
        String param = extractParam(propValue, "image(", ")");
        
        if (!isFill)
        {
          throw new RuntimeException("Image type not supported for stroke");
        }
        
        InputStream is = null;
        try {
          is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
          BufferedImage image = ImageIO.read(is);
          result = new Block.ImageDrawer(image);
        } catch (IOException e) {
          throw new RuntimeException("Failed loading image: " + param, e);
        } finally {
          if (is != null) {
            try {
              is.close();
            } catch (IOException e) {
              throw new RuntimeException("Failed loading image: " + param, e);
            }
          }
        }
      }
      
      throw new RuntimeException("Unsupported definition: " + propValue);
    }
    
    return result;
  }
  
  private String extractParam(String propValue, String prefix, String postfix) {
    return propValue.substring(prefix.length(), propValue.length() - postfix.length());
  }
  
  public Block create(int x, int y)
  {
    Block b = super.create(x, y);
    if (isFill) {
      if (hitPoints == null) {
        b.setDefaultFillDrawer(drawer);
      } else {
        b.addFillDrawer(hitPoints.intValue(), drawer);
      }
    }
    else if (hitPoints == null) {
      b.setDefaultStrokeDrawer(drawer);
    } else {
      b.addStrokeDrawer(hitPoints.intValue(), drawer);
    }
    
    return b;
  }
}
