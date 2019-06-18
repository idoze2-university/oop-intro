package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HighScoreTable implements java.io.Serializable
{
  private static final int SCORES_TO_KEEP = 5;
  private static final String SCORES_FILE_NAME = "highscores";
  private List<Player> topPlayers;
  private int numPlayersToHold;
  
  public static HighScoreTable load() throws IOException
  {
    ObjectInputStream is = null;
    try
    {
      is = new ObjectInputStream(new FileInputStream("highscores"));
      return (HighScoreTable)is.readObject();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Can't find class?!", e);
    }
    catch (FileNotFoundException e) {
      return new HighScoreTable(5);
    } finally {
      if (is != null) {
        is.close();
      }
    }
  }
  


  private HighScoreTable(int numPlayersToHold)
  {
    topPlayers = new ArrayList(numPlayersToHold + 1);
    this.numPlayersToHold = numPlayersToHold;
  }
  
  public void add(Player player) {
    topPlayers.add(player);
    java.util.Collections.sort(topPlayers, new java.util.Comparator()
    {
      public int compare(Player player, Player player1) {
        return player1.getScoreCounter().getValue() - player.getScoreCounter().getValue();
      }
    });
    

    if (topPlayers.size() > numPlayersToHold) {
      topPlayers.remove(numPlayersToHold);
    }
  }
  
  public boolean isWorthy(Player player) {
    int score = player.getScoreCounter().getValue();
    
    if (topPlayers.size() < numPlayersToHold)
      return true;
    if (((Player)topPlayers.get(topPlayers.size() - 1)).getScoreCounter().getValue() < score)
    {
      return true;
    }
    
    return false;
  }
  
  public int size() {
    return topPlayers.size();
  }
  
  public Player get(int i) {
    return (Player)topPlayers.get(i);
  }
  


  public void save()
    throws IOException
  {
    ObjectOutputStream os = null;
    try
    {
      os = new ObjectOutputStream(new FileOutputStream("highscores"));
      os.writeObject(this);
    } finally {
      if (os != null) {
        os.close();
      }
    }
  }
}
