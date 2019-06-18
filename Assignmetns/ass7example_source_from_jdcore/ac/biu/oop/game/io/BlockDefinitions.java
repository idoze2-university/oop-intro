package ac.biu.oop.game.io;

import ac.biu.oop.game.arkanoid.Block;
import ac.biu.oop.game.construction.BasicBlockCreator;
import ac.biu.oop.game.construction.BlockCreator;
import ac.biu.oop.game.construction.DecoratorsFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BlockDefinitions
{
  private static final String DEFAULT_DEFINITION_LINE_PREFIX = "default";
  private static final String BLOCK_DEFINITION_LINE_PREFIX = "bdef";
  private static final String SPACER_DEFINITION_LINE_PREFIX = "sdef";
  private static final String SYMBOL_PROPERTY = "symbol";
  private static final String WIDTH_PROPERTY = "width";
  private Map<Character, Integer> spacerWidths;
  private Map<Character, BlockCreator> blockCreators;
  
  public static BlockDefinitions fromFile(String fileName) throws IOException
  {
    return fromReader(new FileReader(fileName));
  }
  
  public static BlockDefinitions fromReader(Reader reader) throws IOException
  {
    LineNumberReader lineReader = null;
    BlockDefinitions result = new BlockDefinitions();
    DecoratorsFactory decoratorsFactory = new DecoratorsFactory();
    try
    {
      lineReader = new LineNumberReader(reader);
      
      Map<String, String> defaultsMap = Collections.emptyMap();
      
      String line = null;
      while ((line = lineReader.readLine()) != null)
      {

        line = line.trim();
        

        if ((!"".equals(line)) && (!line.startsWith("#")))
        {


          if (line.startsWith("sdef")) {
            String propertiesString = line.substring("sdef".length()).trim();
            Map<String, String> spacerMap = parseProperties(propertiesString);
            

            char symbol = popSymbol(spacerMap);
            
            int width = Integer.parseInt((String)spacerMap.get("width"));
            
            result.addSpacer(symbol, width);
          }
          else if (line.startsWith("bdef")) {
            String propertiesString = line.substring("bdef".length()).trim();
            Map<String, String> blockMap = parseProperties(propertiesString);
            

            Map<String, String> propertiesMap = new HashMap(defaultsMap);
            propertiesMap.putAll(blockMap);
            

            char symbol = popSymbol(propertiesMap);
            
            BlockCreator blockCreator = new BasicBlockCreator();
            
            for (String key : propertiesMap.keySet())
            {
              blockCreator = decoratorsFactory.decorate(blockCreator, key, (String)propertiesMap.get(key));
            }
            

            result.addBlockCreator(symbol, blockCreator);
          }
          else if (line.startsWith("default")) {
            String propertiesString = line.substring("default".length()).trim();
            defaultsMap = parseProperties(propertiesString);
          } else {
            throw new RuntimeException("Unsupported line format: " + line);
          }
        }
      }
    }
    finally {
      if (lineReader != null) {
        lineReader.close();
      }
    }
    

    return result;
  }
  

  private static char popSymbol(Map<String, String> properties)
  {
    String symbol = (String)properties.remove("symbol");
    
    if (symbol.length() != 1) {
      throw new RuntimeException("Symbol must be a single character: " + symbol);
    }
    
    return symbol.charAt(0);
  }
  
  private static Map<String, String> parseProperties(String str) {
    Map<String, String> result = new HashMap();
    
    String[] pairs = str.split(" ");
    
    for (String pair : pairs) {
      String[] keyValue = pair.split(":");
      if (keyValue.length != 2) {
        throw new RuntimeException("Incorrect Properties format : " + str);
      }
      result.put(keyValue[0], keyValue[1]);
    }
    
    return result;
  }
  


  public BlockDefinitions()
  {
    spacerWidths = new HashMap();
    blockCreators = new HashMap();
  }
  
  private void addBlockCreator(char symbol, BlockCreator creator) {
    blockCreators.put(Character.valueOf(symbol), creator);
  }
  
  private void addSpacer(char symbol, int width) {
    spacerWidths.put(Character.valueOf(symbol), Integer.valueOf(width));
  }
  
  public boolean isBlock(char symbol) {
    return blockCreators.containsKey(Character.valueOf(symbol));
  }
  
  public boolean isSpacer(char symbol) {
    return spacerWidths.containsKey(Character.valueOf(symbol));
  }
  
  public int getSpacerWidth(char symbol) {
    return ((Integer)spacerWidths.get(Character.valueOf(symbol))).intValue();
  }
  
  public Block createBlock(char symbol, int x, int y) {
    if (blockCreators.containsKey(Character.valueOf(symbol))) {
      return ((BlockCreator)blockCreators.get(Character.valueOf(symbol))).create(x, y);
    }
    return null;
  }
}
