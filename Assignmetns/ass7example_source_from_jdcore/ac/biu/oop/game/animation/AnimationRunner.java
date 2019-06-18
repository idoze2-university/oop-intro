package ac.biu.oop.game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;







public class AnimationRunner
{
  private GUI gui;
  private int fps;
  private Sleeper sleeper;
  
  public AnimationRunner(GUI gui, int fps)
  {
    this.gui = gui;
    this.fps = fps;
    sleeper = new Sleeper();
  }
  
  public void play(Animation animation)
  {
    long milliSecondsPerTick = 1000 / fps;
    

    for (;;)
    {
      long start = System.currentTimeMillis();
      
      DrawSurface ds = gui.getDrawSurface();
      animation.doOneFrame(ds, milliSecondsPerTick / 1000.0D);
      if (animation.shouldStop()) break;
      gui.show(ds);
      

      long tickMilliSeconds = System.currentTimeMillis() - start;
      long milliSecondLeftToSleep = milliSecondsPerTick - tickMilliSeconds;
      
      if (milliSecondLeftToSleep > 0L) {
        sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}
