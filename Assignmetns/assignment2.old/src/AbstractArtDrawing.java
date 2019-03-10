import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

public class AbstractArtDrawing {

  public void drawRandomLines(DrawSurface d) {
  }

  private Line generateRandomLine(Random rand) {
    int x1 = rand.nextInt(400); // get integer in range 1-400
    int y1 = rand.nextInt(300); // get integer in range 1-300
    int x2 = rand.nextInt(400); // get integer in range 1-400
    int y2 = rand.nextInt(300); // get integer in range 1-300
    return new Line(x1, y1, x2, y2);
  }

  private void drawLine(Line line, Line[] lines, DrawSurface d) {
    d.DrawLine(line.start().getX(), line.start().getY(), line.end().getX(), line.end().getY());
    Drawpoint(line.middle(), Color.RED, d);
    for (int i = 0; i < lines.length; i++) {
      if (line.isIntersecting(lines[i])) {

        DrawPoint(line.intersectionWith(lines[i]), Color.BLUE, d);
      }
    }
  }

  private void drawPoint(Point p, Color color, DrawSurface d) {
    d.setColor(color);
    d.fillCircle(p.getX(), p.getY(), 3);
  }

  public static void main(String[] args) {
    GUI gui = new GUI("Random Circles Example", 400, 300);
    DrawSurface d = gui.getDrawSurface();
    AbstractArtDrawing example = new AbstractArtDrawing();
    example.drawRandomLines(d);
  }
}