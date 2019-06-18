package ac.biu.oop.game.io;

import ac.biu.oop.game.arkanoid.Block;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class DynamicLevelInformation implements ac.biu.oop.game.levels.LevelInformation
{
  private static final String START_LEVEL = "START_LEVEL";
  private static final String END_LEVEL = "END_LEVEL";
  private static final String BLOCK_DEFINITIONS = "block_definitions";
  private static final String ROW_HEIGHT = "row_height";
  private static final String BLOCKS_START_X = "blocks_start_x";
  private static final String BLOCKS_START_Y = "blocks_start_y";
  private static final String START_BLOCKS = "START_BLOCKS";
  private static final String END_BLOCKS = "END_BLOCKS";
  private static final String LEVEL_NAME = "level_name";
  private static final String BACKGROUND = "background";
  private static final String BALL_VELOCITIES = "ball_velocities";
  private static final String PADDLE_SPEED = "paddle_speed";
  private static final String PADDLE_WIDTH = "paddle_width";
  private static final String NUM_BLOCKS = "num_blocks";
  private static final String RGB_PREFIX = "color(RGB(";
  private static final String RGB_POSTFIX = "))";
  private static final String COLOR_PREFIX = "color(";
  private static final String COLOR_POSTFIX = ")";
  private static final String IMAGE_PREFIX = "image(";
  private static final String IMAGE_POSTFIX = ")";
  private List<ac.biu.oop.game.core.Velocity> velocities;
  private int paddleSpeed;
  private int paddleWidth;
  private String levelName;
  private List<Block> blocks;
  private int numberOfBlocksToClear;
  private ac.biu.oop.game.core.Sprite backgroundSprite;
  
  public static List<ac.biu.oop.game.levels.LevelInformation> fromFile(String fileName) throws IOException
  {
    return fromReader(new java.io.FileReader(fileName));
  }
  
  public static List<ac.biu.oop.game.levels.LevelInformation> fromReader(java.io.Reader reader) throws IOException {
    List<ac.biu.oop.game.levels.LevelInformation> levels = new java.util.LinkedList();
    java.util.Map<String, BlockDefinitions> fileDefinitions = new java.util.HashMap();
    DynamicLevelInformation currentLevel = null;
    BlockDefinitions currentLevelBlockDefinitions = null;
    Integer currentLevelRowHeight = null;
    Integer currentLevelRowIndex = null;
    Integer currentLevelBlocksStartX = null;
    Integer currentLevelBlocksStartY = null;
    
    java.io.LineNumberReader lineReader = null;
    boolean readingBlocks = false;
    try {
      lineReader = new java.io.LineNumberReader(reader);
      
      String line = null;
      while ((line = lineReader.readLine()) != null)
      {

        line = line.trim();
        

        if ((!"".equals(line)) && (!line.startsWith("#")))
        {
          if (!readingBlocks) {
            if ("START_LEVEL".equals(line)) {
              currentLevel = new DynamicLevelInformation();
            } else if ("END_LEVEL".equals(line)) {
              levels.add(currentLevel);
              currentLevel = null;
              currentLevelBlockDefinitions = null;
              currentLevelRowHeight = null;
              currentLevelRowIndex = null;
              currentLevelBlocksStartX = null;
              currentLevelBlocksStartY = null;
            } else if ("START_BLOCKS".equals(line)) {
              readingBlocks = true;
              currentLevelRowIndex = Integer.valueOf(0);
            }
            else {
              String[] parts = line.split(":");
              String key = parts[0];
              String value = parts[1];
              
              if ("block_definitions".equals(key)) {
                if (fileDefinitions.containsKey(value)) {
                  currentLevelBlockDefinitions = (BlockDefinitions)fileDefinitions.get(value);
                } else {
                  try {
                    currentLevelBlockDefinitions = BlockDefinitions.fromReader(new java.io.InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(value)));

                  }
                  catch (Exception e)
                  {

                    throw new RuntimeException("Failed loading block definitions from: " + value, e);
                  }
                  fileDefinitions.put(value, currentLevelBlockDefinitions);
                }
              }
              else if ("row_height".equals(key)) {
                currentLevelRowHeight = Integer.valueOf(Integer.parseInt(value));
              } else if ("blocks_start_x".equals(key)) {
                currentLevelBlocksStartX = Integer.valueOf(Integer.parseInt(value));
              } else if ("blocks_start_y".equals(key)) {
                currentLevelBlocksStartY = Integer.valueOf(Integer.parseInt(value));
              } else {
                setLevelProperty(currentLevel, key, value);
              }
              
            }
          }
          else if ("END_BLOCKS".equals(line)) {
            readingBlocks = false;
          } else {
            int currentX = currentLevelBlocksStartX.intValue();
            for (int i = 0; i < line.length(); i++) {
              char symbol = line.charAt(i);
              int currentY = currentLevelRowIndex.intValue() * currentLevelRowHeight.intValue() + currentLevelBlocksStartY.intValue();
              if (currentLevelBlockDefinitions.isSpacer(symbol)) {
                currentX += currentLevelBlockDefinitions.getSpacerWidth(symbol);
              }
              else {
                Block b = currentLevelBlockDefinitions.createBlock(symbol, currentX, currentY);
                if (b == null) {
                  throw new RuntimeException("Failed creating block of type:" + symbol);
                }
                currentX = (int)(currentX + b.getCollisionRectangle().getWidth());
                currentLevel.addBlock(b);
              }
            }
            currentLevelRowIndex = Integer.valueOf(currentLevelRowIndex.intValue() + 1);
          }
        }
      }
    } finally {
      if (lineReader != null) {
        lineReader.close();
      }
    }
    

    return levels;
  }
  
















  private static void setLevelProperty(DynamicLevelInformation currentLevel, String key, String value)
  {
    if ("level_name".equals(key)) {
      currentLevel.setLevelName(value);
    } else { if ("background".equals(key)) {
        if ((value.startsWith("color(RGB(")) && (value.endsWith("))"))) {
          String param = extractParam(value, "color(RGB(", "))");
          String[] parts = param.split(",");
          int r = Integer.parseInt(parts[0].trim());
          int g = Integer.parseInt(parts[1].trim());
          int b = Integer.parseInt(parts[2].trim());
          Color color = new Color(r, g, b);
          currentLevel.setBackgroundSprite(new ac.biu.oop.game.levels.SingleColorBackground(color));
        } else if ((value.startsWith("color(")) && (value.endsWith(")"))) {
          String param = extractParam(value, "color(", ")");
          try {
            java.lang.reflect.Field field = Color.class.getField(param);
            Color color = (Color)field.get(null);
            currentLevel.setBackgroundSprite(new ac.biu.oop.game.levels.SingleColorBackground(color));
          } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unsupported color name: " + param);
          } catch (IllegalAccessException e) {
            throw new RuntimeException("Unsupported color name: " + param);
          }
        } else if ((value.startsWith("image(")) && (value.endsWith(")"))) {
          String param = extractParam(value, "image(", ")");
          java.io.InputStream is = null;
          try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(is);
            currentLevel.setBackgroundSprite(new ac.biu.oop.game.levels.ImageBackground(image));
          } catch (IOException e) {
            throw new RuntimeException("Failed loading image: " + param, e);
          } finally {
            if (is != null)
              try {
                is.close();
              } catch (IOException e) {
                throw new RuntimeException("Failed loading image: " + param, e);
              }
          }
        }
      }
      if ("ball_velocities".equals(key)) {
        String[] velocitiesDef = value.split(" ");
        for (String velDef : velocitiesDef) {
          String[] props = velDef.split(",");
          
          currentLevel.addVelocity(ac.biu.oop.game.core.Velocity.fromAngleAndSpeed(Double.parseDouble(props[0]), Double.parseDouble(props[1])));

        }
        

      }
      else if ("paddle_speed".equals(key)) {
        currentLevel.setPaddleSpeed(Integer.parseInt(value));
      } else if ("paddle_width".equals(key)) {
        currentLevel.setPaddleWidth(Integer.parseInt(value));
      } else if ("num_blocks".equals(key)) {
        currentLevel.setNumberOfBlocksToClear(Integer.parseInt(value));
      } else {
        throw new RuntimeException("Unknown key encountered:" + key);
      }
    }
  }
  
  private static String extractParam(String propValue, String prefix, String postfix) { return propValue.substring(prefix.length(), propValue.length() - postfix.length()); }
  








  public DynamicLevelInformation()
  {
    velocities = new java.util.LinkedList();
    blocks = new java.util.LinkedList();
  }
  
  public void setBackgroundSprite(ac.biu.oop.game.core.Sprite backgroundSprite) {
    this.backgroundSprite = backgroundSprite;
  }
  
  public void setNumberOfBlocksToClear(int numberOfBlocksToClear) {
    this.numberOfBlocksToClear = numberOfBlocksToClear;
  }
  
  public void addBlock(Block block) {
    blocks.add(block);
  }
  
  public void addVelocity(ac.biu.oop.game.core.Velocity velocity) {
    velocities.add(velocity);
  }
  
  public void setPaddleSpeed(int paddleSpeed) {
    this.paddleSpeed = paddleSpeed;
  }
  
  public void setPaddleWidth(int paddleWidth) {
    this.paddleWidth = paddleWidth;
  }
  
  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }
  
  public int numberOfBalls()
  {
    return velocities.size();
  }
  
  public List<ac.biu.oop.game.core.Velocity> initialBallVelocities()
  {
    return velocities;
  }
  
  public int paddleSpeed()
  {
    return paddleSpeed;
  }
  
  public int paddleWidth()
  {
    return paddleWidth;
  }
  
  public String levelName()
  {
    return levelName;
  }
  
  public ac.biu.oop.game.core.Sprite getBackgroundSprite()
  {
    return backgroundSprite;
  }
  
  public List<Block> blocks()
  {
    return blocks;
  }
  
  public int numberOfBlocksToRemove()
  {
    return numberOfBlocksToClear;
  }
}
