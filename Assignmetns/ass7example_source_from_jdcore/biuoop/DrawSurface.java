package biuoop;

import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;

public abstract interface DrawSurface
{
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  public abstract void setColor(Color paramColor);
  
  public abstract void drawLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void drawOval(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void fillOval(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void drawRectangle(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void fillRectangle(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void drawImage(int paramInt1, int paramInt2, Image paramImage);
  
  public abstract void drawCircle(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void fillCircle(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void drawText(int paramInt1, int paramInt2, String paramString, int paramInt3);
  
  public abstract void drawPolygon(Polygon paramPolygon);
  
  public abstract void fillPolygon(Polygon paramPolygon);
}
