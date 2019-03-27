import java.awt.Color;
import biuoop.DrawSurface;

public class CountingBlock extends Block {

  private int count;

  public CountingBlock(double x, double y, double width, double height, Color color, int baseCount) {
    super(x, y, width, height, color);
    count = baseCount;
  }

  public void drawOn(DrawSurface d) {
    super.drawOn(d);
    d.setColor(color.BLACK);
    int x = (int) collisionRectangle.getUpperLeft().getX();
    int y = (int) collisionRectangle.getUpperLeft().getY();
    int width = (int) collisionRectangle.getWidth();
    int height = (int) collisionRectangle.getHeight();
    d.drawRectangle(x, y, width, height);
    Point pt = collisionRectangle.getUpperLeft().add(collisionRectangle.getWidth() / 2.0,
        collisionRectangle.getHeight() / 2.0);
    String value = "X";
    if (count > 0) {
      value = String.valueOf(count);
    }
    d.drawText((int) pt.getX(), (int) pt.getY(), value, 13);
  }

  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    if (count > 0) {
      count--;
    } else if (count == 0) {
      game.remove(this);
    }
    return super.hit(collisionPoint, currentVelocity);

  }
}