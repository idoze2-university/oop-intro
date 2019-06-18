package biuoop;

import java.awt.Graphics;
import java.awt.Image;
































































































class DrawImage
  implements Command
{
  int x;
  int y;
  Image image;
  
  public DrawImage(int x, int y, Image image)
  {
    this.x = x;
    this.y = y;
    this.image = image;
  }
  
  public void draw(Graphics g) {
    g.drawImage(image, x, y, null);
  }
}
