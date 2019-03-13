import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

public class MultipleBouncingBallFrame {
  private Frame frame;
  private Ball[] balls;

  public MultipleBouncingBallFrame(Frame frame) {
    this.frame = frame;
    balls = null;
  }

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

  public Ball randomBall(Random rand, int size, boolean RandomColor) {
    Color color = Color.BLACK;
    if (RandomColor) {
      color = Color.getHSBColor((rand.nextInt(10000) / 10000.0f), 1.0f, 1.0f);
    }
    return new Ball(frame.randomPoint(rand, size), size, color, frame);
  }

  /**
   *
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