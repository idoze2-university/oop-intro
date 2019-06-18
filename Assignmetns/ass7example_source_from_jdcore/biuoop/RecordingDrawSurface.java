package biuoop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


























































































































































class RecordingDrawSurface
  implements DrawSurface
{
  private List<Command> commands;
  private int width;
  private int height;
  private boolean rendered;
  
  public RecordingDrawSurface(int width, int height)
  {
    commands = new ArrayList();
    
    this.width = width;
    this.height = height;
    rendered = false;
  }
  
  boolean isRendered() {
    return rendered;
  }
  
  void setRendered(boolean rendered) {
    this.rendered = rendered;
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void clear() {
    commands.clear();
  }
  
  private void validateRender() {
    if (isRendered()) {
      throw new DrawSurfaceAlreadyRenderedException("You can not use the same surface after show() has been called");
    }
  }
  
  public void setColor(Color color)
  {
    validateRender();
    
    commands.add(new SetColor(color));
  }
  
  public void drawLine(int x1, int y1, int x2, int y2) {
    validateRender();
    
    commands.add(new DrawLine(x1, y1, x2, y2));
  }
  
  public void drawOval(int x, int y, int width, int height) {
    validateRender();
    
    commands.add(new DrawOval(x, y, width, height));
  }
  
  public void fillOval(int x, int y, int width, int height) {
    validateRender();
    
    commands.add(new FillOval(x, y, width, height));
  }
  
  public void drawRectangle(int x, int y, int width, int height) {
    validateRender();
    
    commands.add(new DrawRectangle(x, y, width, height));
  }
  
  public void fillRectangle(int x, int y, int width, int height) {
    validateRender();
    
    commands.add(new FillRectangle(x, y, width, height));
  }
  
  public void drawImage(int x, int y, Image image) {
    validateRender();
    
    commands.add(new DrawImage(x, y, image));
  }
  
  public void drawCircle(int x, int y, int r) {
    validateRender();
    
    commands.add(new DrawOval(x - r, y - r, r * 2, r * 2));
  }
  
  public void fillCircle(int x, int y, int r) {
    validateRender();
    
    commands.add(new FillOval(x - r, y - r, r * 2, r * 2));
  }
  
  public void drawText(int x, int y, String text, int fontSize) {
    validateRender();
    
    commands.add(new DrawText(x, y, text, fontSize));
  }
  
  public void drawPolygon(Polygon polygon)
  {
    validateRender();
    
    commands.add(new DrawPolygon(clone(polygon)));
  }
  
  public void fillPolygon(Polygon polygon)
  {
    validateRender();
    
    commands.add(new FillPolygon(clone(polygon)));
  }
  
  private Polygon clone(Polygon polygon) {
    int[] xPoints = Arrays.copyOf(xpoints, xpoints.length);
    int[] yPoints = Arrays.copyOf(ypoints, ypoints.length);
    
    return new Polygon(xPoints, yPoints, npoints);
  }
  
  public void paint(Graphics g) {
    for (Command cmd : commands) {
      cmd.draw(g);
    }
  }
}
