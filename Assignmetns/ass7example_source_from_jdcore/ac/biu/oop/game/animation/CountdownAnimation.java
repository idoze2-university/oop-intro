package ac.biu.oop.game.animation;

import ac.biu.oop.game.arkanoid.SpriteCollection;
import ac.biu.oop.game.core.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;














public class CountdownAnimation
  implements Animation
{
  private double numOfSeconds;
  private int countFrom;
  private SpriteCollection gameScreen;
  private Sprite backgroundSprite;
  private boolean done;
  private boolean firstFrame;
  private long startMillisecond;
  
  public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite backgroundSprite)
  {
    this.numOfSeconds = numOfSeconds;
    this.countFrom = countFrom;
    this.gameScreen = gameScreen;
    this.backgroundSprite = backgroundSprite;
    done = false;
    firstFrame = true;
  }
  
  public boolean shouldStop()
  {
    return done;
  }
  

  public void doOneFrame(DrawSurface ds, double dt)
  {
    if (firstFrame) {
      startMillisecond = System.currentTimeMillis();
      firstFrame = false;
    }
    
    if (backgroundSprite != null) {
      backgroundSprite.drawOn(ds);
    }
    
    gameScreen.drawAllOn(ds);
    
    long currentMillisecond = System.currentTimeMillis();
    
    double singleCountLength = numOfSeconds / countFrom;
    
    int currentNumber = (int)(1 + countFrom - (currentMillisecond - startMillisecond) / (singleCountLength * 1000.0D));
    
    if (currentNumber > 0)
    {

      ds.setColor(Color.BLACK);
      ds.drawText(ds.getWidth() / 2 + 2, ds.getHeight() / 2 + 100, "" + currentNumber, 48);
      ds.drawText(ds.getWidth() / 2 - 2, ds.getHeight() / 2 + 100, "" + currentNumber, 48);
      ds.drawText(ds.getWidth() / 2, ds.getHeight() / 2 + 102, "" + currentNumber, 48);
      ds.drawText(ds.getWidth() / 2, ds.getHeight() / 2 + 98, "" + currentNumber, 48);
      

      ds.setColor(Color.WHITE);
      ds.drawText(ds.getWidth() / 2, ds.getHeight() / 2 + 100, "" + currentNumber, 48);
    }
    
    if (currentMillisecond - startMillisecond > numOfSeconds * 1000.0D) {
      done = true;
    }
  }
}
