package ac.biu.oop.game.menu;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;







public class LevelSets
{
  private List<LevelSet> levelSetList;
  
  public static LevelSets fromReader(Reader reader)
    throws IOException
  {
    LevelSets result = new LevelSets();
    
    LevelSet currentSet = null;
    LineNumberReader lineReader = null;
    try {
      lineReader = new LineNumberReader(reader);
      
      String line = null;
      while ((line = lineReader.readLine()) != null)
      {
        if (lineReader.getLineNumber() % 2 == 0)
        {
          currentSet.setLevelDefinitionPath(line.trim());
          
          result.addLevelSet(currentSet);
          currentSet = null;
        } else {
          currentSet = new LevelSet();
          
          String[] lineParts = line.trim().split(":");
          currentSet.setKey(lineParts[0]);
          currentSet.setMessage(lineParts[1]);
        }
      }
    }
    finally
    {
      if (lineReader != null) {
        lineReader.close();
      }
    }
    
    return result;
  }
  

  public LevelSets()
  {
    levelSetList = new ArrayList();
  }
  
  public void addLevelSet(LevelSet levelSet) {
    levelSetList.add(levelSet);
  }
  
  public List<LevelSet> getLevelSetList() {
    return levelSetList;
  }
  
  public static class LevelSet
  {
    private String message;
    private String key;
    private String levelDefinitionPath;
    
    public LevelSet() {}
    
    public void setMessage(String message) {
      this.message = message;
    }
    
    public void setKey(String key) {
      this.key = key;
    }
    
    public void setLevelDefinitionPath(String levelDefinitionPath) {
      this.levelDefinitionPath = levelDefinitionPath;
    }
    
    public String getMessage() {
      return message;
    }
    
    public String getKey() {
      return key;
    }
    
    public String getLevelDefinitionPath() {
      return levelDefinitionPath;
    }
  }
}
