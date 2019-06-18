package ac.biu.oop.game.animation;

import ac.biu.oop.game.core.Counter;
import biuoop.DrawSurface;
import java.awt.Color;









public class EndScreen
  implements Animation
{
  private Counter scoreCounter;
  private boolean hasWon;
  
  public EndScreen(Counter scoreCounter, boolean hasWon)
  {
    this.hasWon = hasWon;
    this.scoreCounter = scoreCounter;
  }
  


  public void doOneFrame(DrawSurface ds, double dt)
  {
    ds.setColor(Color.LIGHT_GRAY);
    ds.fillRectangle(0, 0, ds.getWidth(), ds.getHeight());
    
    String msg1 = "You Won";
    if (!hasWon) {
      msg1 = "You Lost";
    }
    
    ds.setColor(Color.BLACK);
    ds.drawText(280, 150, msg1, 58);
    
    if (hasWon) {
      ds.setColor(Color.YELLOW);
    } else {
      ds.setColor(Color.RED);
    }
    ds.drawText(282, 151, msg1, 58);
    ds.setColor(Color.BLACK);
    ds.drawText(285, 152, msg1, 58);
    

    ds.setColor(Color.BLACK);
    ds.drawText(10, 590, "Final score:" + scoreCounter.getValue(), 12);
    ds.drawText(11, 590, "Final score:" + scoreCounter.getValue(), 12);
    

    String msg2 = "Press space to continue";
    
    ds.setColor(Color.BLACK);
    ds.drawText(199, 500, msg2, 32);
    ds.setColor(Color.decode("#1788d0"));
    ds.drawText(200, 501, msg2, 32);
    ds.setColor(Color.BLACK);
    ds.drawText(202, 502, msg2, 32);
  }
  
  public boolean shouldStop()
  {
    return false;
  }
}
