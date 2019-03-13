import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * The MultipleBouncingBallFrame implements an Object for animating multiple
 * balls bouncing in a frame, can be displayed on a drawSurface.
 *
 * @author zeiraid
 */
public class AnimationFrame {
  private Frame frame;
  private Ball[] balls;

  /**
   * default constructor.
   *
   * @param frame frame containing the animation.
   */
  public AnimationFrame(Frame frame) {
    this.frame = frame;
    balls = null;
  }

  /**
   * adds a ball to the araay.
   *
   * @param b The ball to add to the array.
   */
  public void addBall(Ball b) {
    Ball[] extended = new Ball[1];
    if (balls != null) {
      extended = new Ball[balls.length + 1];
      for (int i = 0; i < balls.length; i++) {
        extended[i] = balls[i];
      }
    }
    extended[extended.length - 1] = b;
    balls = extended;
  }

  /**
   * Generates a random ball inside the frame.
   *
   * @param rand        Random object for random calculations.
   * @param size        Size to give the generated ball.
   * @param randomColor boolean indicating whether or not to draw the balls in
   *                    random color, Would draw in black if false.
   * @return returns a random ball.
   */
  public Ball randomBall(Random rand, int size, boolean randomColor) {
    Color color = Color.BLACK;
    if (randomColor) {
      color = Color.getHSBColor((rand.nextInt(10000) / 10000.0f), 1.0f, 1.0f);
    }
    return new Ball(frame.randomPoint(rand, size), size, color, frame);
  }

  /**
   * Draws the balls and moves them one step.
   *
   * @param d DrawSurface to draw the balls to.
   */
  public void drawBalls(DrawSurface d) {
    Random rand = new Random();
    for (int i = 0; i < balls.length; i++) {
      balls[i].drawOn(d);
      balls[i].moveOneStep(rand);
    }
  }

  /**
   * Draws an iteration of the frame to 'd' and iterates the next one.
   *
   * @param d DrawSurface to Draw the frame to.
   */
  public void drawStep(DrawSurface d) {
    frame.drawOnSurface(d);
    drawBalls(d);
  }
}