package ac.biu.oop.game.animation;

import ac.biu.oop.game.arkanoid.HighScoreTable;
import ac.biu.oop.game.arkanoid.Player;
import ac.biu.oop.game.core.Counter;
import biuoop.DrawSurface;
import java.awt.Color;












public class HighScores
  implements Animation
{
  private static final int TITLE_LEFT = 50;
  private static final int TITLE_TOP = 50;
  private static final int TITLE_FONT_SIZE = 32;
  private static final int PLAYER_Y_GAP = 40;
  private static final int NAME_LEFT = 100;
  private static final int SCORE_LEFT = 450;
  private static final int PLAYER_LIST_HEADER_TOP = 120;
  private static final int PLAYER_LIST_TOP = 170;
  private static final int PLAYER_LIST_FONT_SIZE = 24;
  private HighScoreTable highScoreTable;
  
  public HighScores(HighScoreTable highScoreTable)
  {
    this.highScoreTable = highScoreTable;
  }
  

  public void doOneFrame(DrawSurface ds, double dt)
  {
    ds.setColor(Color.LIGHT_GRAY);
    ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());
    

    ds.setColor(Color.BLACK);
    ds.drawText(51, 50, "High Scores", 32);
    ds.drawText(49, 50, "High Scores", 32);
    ds.drawText(50, 51, "High Scores", 32);
    ds.drawText(50, 49, "High Scores", 32);
    ds.setColor(Color.YELLOW);
    ds.drawText(50, 50, "High Scores", 32);
    
    ds.setColor(Color.BLACK);
    ds.drawText(101, 120, "Player Name", 24);
    ds.drawText(99, 120, "Player Name", 24);
    ds.drawText(100, 121, "Player Name", 24);
    ds.drawText(100, 119, "Player Name", 24);
    ds.setColor(Color.GREEN);
    ds.drawText(100, 120, "Player Name", 24);
    
    ds.setColor(Color.BLACK);
    ds.drawText(451, 120, "Score", 24);
    ds.drawText(449, 120, "Score", 24);
    ds.drawText(450, 121, "Score", 24);
    ds.drawText(450, 119, "Score", 24);
    ds.setColor(Color.GREEN);
    ds.drawText(450, 120, "Score", 24);
    
    ds.setColor(Color.BLACK);
    ds.drawLine(100, 130, 700, 130);
    ds.setColor(Color.GREEN);
    ds.drawLine(100, 131, 700, 131);
    ds.setColor(Color.BLACK);
    ds.drawLine(100, 132, 700, 132);
    

    ds.setColor(Color.BLACK);
    for (int i = 0; i < highScoreTable.size(); i++) {
      Player player = highScoreTable.get(i);
      int nameX = 100;
      int scoreX = 450;
      int entryY = 170 + i * 40;
      
      ds.setColor(Color.BLACK);
      ds.drawText(nameX + 1, entryY, player.getName(), 24);
      ds.drawText(nameX - 1, entryY, player.getName(), 24);
      ds.drawText(nameX, entryY + 1, player.getName(), 24);
      ds.drawText(nameX, entryY - 1, player.getName(), 24);
      ds.setColor(Color.ORANGE);
      ds.drawText(nameX, entryY, player.getName(), 24);
      

      ds.setColor(Color.BLACK);
      ds.drawText(scoreX + 1, entryY, "" + player.getScoreCounter().getValue(), 24);
      ds.drawText(scoreX - 1, entryY, "" + player.getScoreCounter().getValue(), 24);
      ds.drawText(scoreX, entryY + 1, "" + player.getScoreCounter().getValue(), 24);
      ds.drawText(scoreX, entryY - 1, "" + player.getScoreCounter().getValue(), 24);
      
      ds.setColor(Color.ORANGE);
      ds.drawText(scoreX, entryY, "" + player.getScoreCounter().getValue(), 24);
    }
    
    String msg = "Press space to continue";
    ds.setColor(Color.BLACK);
    ds.drawText(199, 500, msg, 32);
    ds.setColor(Color.decode("#1788d0"));
    ds.drawText(200, 501, msg, 32);
    ds.setColor(Color.BLACK);
    ds.drawText(202, 502, msg, 32);
  }
  
  public boolean shouldStop()
  {
    return false;
  }
}
