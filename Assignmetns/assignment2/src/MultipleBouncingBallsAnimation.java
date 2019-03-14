import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The BouncingBallsAnimation class implements a static main operation wich
 * utilizes the AnimationFrame object for demonstrating the physics of multiple
 * bouncing balls in a 200x200 frame.
 *
 * @author zeiraid
 */
public class MultipleBouncingBallsAnimation {
  /**
   * @param args ball sizes read from the command line.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("Bouncing Ball Animation", 200, 200);
    DrawSurface d = gui.getDrawSurface();

    Frame containerG = new Frame(0, 0, 200, 200, Color.WHITE);
    AnimationFrame animationG = new AnimationFrame(containerG);
    MultipleFramesBouncingBallsAnimation.loadBalls(args, animationG, false);

    Sleeper sleeper = new Sleeper();
    while (true) {
      d = gui.getDrawSurface();
      animationG.drawStep(d);
      gui.show(d);
      sleeper.sleepFor(15);
    }
  }
}