import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

public class BouncingBallAnimation {
  /**
   * @param args ball sizes read from the command line.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("title", 200, 200);
    DrawSurface d = gui.getDrawSurface();

    Frame containerG = new Frame(0, 0, 200, 200, Color.WHITE);
    MultipleBouncingBallFrame animationG = new MultipleBouncingBallFrame(containerG);
    MultipleFrameBouncingBallAnimation.loadBalls(args, animationG, false);

    Sleeper sleeper = new Sleeper();
    while (true) {
      d = gui.getDrawSurface();
      animationG.drawStep(d);
      gui.show(d);
      sleeper.sleepFor(15);
    }
  }
}