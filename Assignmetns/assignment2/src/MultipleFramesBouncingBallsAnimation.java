import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * The MultipleFramesBouncingBallsAnimation utilizes the AnimationFrame, and
 * implements a way to Load multiple frames with balls for each of them.
 *
 * @author zeiraid
 */
public class MultipleFramesBouncingBallsAnimation {

  /**
   * reads from String array of sizes and inserts random balls with given sizes to
   * AnimationFrame f.
   *
   * @param sizes            the sizes to be read.
   * @param f                the frame to load the balls to.
   * @param randomColorBalls boleean indicating wether or not to draw the balls in
   *                         a random color.
   */
  public static void loadBalls(String[] sizes, AnimationFrame f, boolean randomColorBalls) {
    Random rand = new Random();
    for (int i = 0; i < sizes.length; i++) {
      int size = Integer.parseInt(sizes[i]);
      Ball b = f.randomBall(rand, size, randomColorBalls);
      f.addBall(b);
    }
  }

  /**
   * Splits the array origin to a subarray from start to end.
   *
   * @param origin the String array to take values from.
   * @param start  the starting index of the subArray.
   * @param end    the ending index of the subArray (Exclusive).
   * @return a subArray from 'start' to 'end'.
   */
  public static String[] subArray(String[] origin, int start, int end) {
    int fixedEnd = Math.min(end, origin.length);
    int fixedStart = Math.max(start, 0);
    int fixedLength = fixedEnd - fixedStart;
    String[] sub = new String[fixedLength];
    for (int i = 0; i < fixedLength; i++) {
      sub[i] = origin[i + fixedStart];
    }
    return sub;
  }

  /**
   * Draws two frams: A gray 500x500 frame starting in position (50,50) and a
   * yellow 150x150 frame starting in position (450,450). draws first half of
   * given ball sizes to the gray one, and the other half to the yellow one,
   * iterates an animation for both of them simultaniously.
   *
   * @param args sizes for the balls.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("title", 600, 600);
    DrawSurface d = gui.getDrawSurface();
    Boolean randomColorBalls = true;

    // Create Gray Frame
    Frame containerG = new Frame(50, 50, 500, 500, Color.GRAY);
    AnimationFrame grayFrame = new AnimationFrame(containerG);
    String[] firstHalf = subArray(args, 0, (int) Math.floor((args.length) / 2.0));
    loadBalls(firstHalf, grayFrame, randomColorBalls);

    // Create Yellow Frame
    Frame containerY = new Frame(450, 450, 150, 150, Color.YELLOW);
    AnimationFrame yellowFrame = new AnimationFrame(containerY);
    String[] secondHalf = subArray(args, (int) Math.ceil((args.length) / 2.0), args.length);
    loadBalls(secondHalf, yellowFrame, randomColorBalls);

    Sleeper sleeper = new Sleeper();
    while (true) {
      d = gui.getDrawSurface();
      grayFrame.drawStep(d);
      yellowFrame.drawStep(d);
      gui.show(d);
      sleeper.sleepFor(15);
    }
  }
}