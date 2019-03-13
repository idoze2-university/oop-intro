import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * The BallsTest1 Class implements a static operation of printing 3 balls in a
 * 400x400 frame.
 */
public class BallsTest1 {
  /**
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("Balls Test 1", 400, 400);
    Frame container = new Frame(0, 0, 400, 400, Color.WHITE);
    DrawSurface d = gui.getDrawSurface();
    Ball b1 = new Ball(100, 100, 30, java.awt.Color.RED, container);
    Ball b2 = new Ball(100, 150, 10, java.awt.Color.BLUE, container);
    Ball b3 = new Ball(80, 249, 50, java.awt.Color.GREEN, container);
    container.drawOnSurface(d);
    b1.drawOn(d);
    b2.drawOn(d);
    b3.drawOn(d);
    gui.show(d);
  }
}
