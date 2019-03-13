import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

public class MultipleFrameBouncingBallAnimation {

  public static void loadBalls(String[] sizes, MultipleBouncingBallFrame f, boolean RandomColorBalls) {
    Random rand = new Random();
    for (int i = 0; i < sizes.length; i++) {
      int size = Integer.parseInt(sizes[i]);
      Ball b = f.randomBall(rand, size, RandomColorBalls);
      f.addBall(b);
    }
  }

  public static String[] SubArray(String[] origin, int start, int end) {
    int fixedEnd = Math.min(end, origin.length);
    int fixedStart = Math.max(start, 0);
    int fixedLength = fixedEnd - fixedStart;
    String[] sub = new String[fixedLength];
    for (int i = 0; i < fixedLength; i++) {
      sub[i] = origin[i + fixedStart];
    }
    return sub;
  }

  public static void main(String[] args) {
    GUI gui = new GUI("title", 600, 600);
    DrawSurface d = gui.getDrawSurface();
    Boolean randomColorBalls = false;

    // Create Gray Frame
    Frame containerG = new Frame(50, 50, 500, 500, Color.GRAY);
    MultipleBouncingBallFrame animationG = new MultipleBouncingBallFrame(containerG);
    String[] firstHalf = SubArray(args, 0, (int) Math.floor((args.length) / 2.0));
    loadBalls(firstHalf, animationG, randomColorBalls);

    // Create Yellow Frame
    Frame containerY = new Frame(450, 450, 150, 150, Color.YELLOW);
    MultipleBouncingBallFrame animationY = new MultipleBouncingBallFrame(containerY);
    String[] secondHalf = SubArray(args, (int) Math.ceil((args.length) / 2.0), args.length);
    loadBalls(secondHalf, animationY, randomColorBalls);

    Sleeper sleeper = new Sleeper();
    while (true) {
      d = gui.getDrawSurface();
      animationG.drawStep(d);
      animationY.drawStep(d);
      gui.show(d);
      sleeper.sleepFor(15);
    }
  }
}