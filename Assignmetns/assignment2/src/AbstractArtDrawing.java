import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * The AbstractArtDrawing class implements the drawing of 10 random lines, their
 * intersection points and their middle points, using the GUI class in the
 * biuoop library.
 *
 * @author zeiraid
 */
public class AbstractArtDrawing {
  private Line[] lines;

  /**
   * Default constructor, basically has nothing to initialize, since the class has
   * no members.
   */
  public AbstractArtDrawing() {
    lines = new Line[10];

  }

  /**
   * Draws randomally-generated lines onto the surface d.
   *
   * @param d The DrawSurface to draw the lines to.
   */
  public void drawRandomLines(DrawSurface d) {
    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      Line line = generateRandomLine(rand);
      drawLine(line, d);
      lines[i] = line;
    }
  }

  /**
   * Generates a Line object between two points.
   *
   * @param rand The instance of random object to use for the calculations.
   * @return A randomally generated Line object.
   */
  private Line generateRandomLine(Random rand) {
    int x1 = rand.nextInt(400); // get integer in range 1-400
    int y1 = rand.nextInt(300); // get integer in range 1-300
    int x2 = rand.nextInt(400); // get integer in range 1-400
    int y2 = rand.nextInt(300); // get integer in range 1-300
    return new Line(x1, y1, x2, y2);
  }

  /**
   * Draws the Line 'line', it's middle point and his intersection points (if they
   * exsist) with the other lines in the 'lines' array, to the surface 'd'.
   *
   * @param line the Line object to draw.
   * @param d    the DrawSurface object to draw the line and the points to.
   */
  private void drawLine(Line line, DrawSurface d) {
    d.setColor(Color.BLACK);
    int startX = (int) line.start().getX();
    int startY = (int) line.start().getY();
    int endX = (int) line.end().getX();
    int endY = (int) line.end().getY();
    d.drawLine(startX, startY, endX, endY);
    drawPoint(line.middle(), Color.BLUE, d);
    for (int i = 0; i < lines.length; i++) {
      drawPoint(line.intersectionWith(lines[i]), Color.RED, d);
    }
  }

  /**
   * draws the Point p with the color 'color' to DrawSurface d.
   *
   * @param p     the Point object to draw.
   * @param color the color in which the point should be drawn.
   * @param d     the DrawSurface on which the point should be drawn.
   */
  private void drawPoint(Point p, Color color, DrawSurface d) {
    if (p != null) {
      d.setColor(color);
      d.fillCircle((int) p.getX(), (int) p.getY(), 3);
    }
  }

  /**
   * initializes a gui and draws 10 random lines, their middle points and their
   * intersection points to it.
   *
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    GUI gui = new GUI("title", 400, 300);
    DrawSurface d = gui.getDrawSurface();
    AbstractArtDrawing drawing = new AbstractArtDrawing();
    drawing.drawRandomLines(d);
    gui.show(d);
  }
}