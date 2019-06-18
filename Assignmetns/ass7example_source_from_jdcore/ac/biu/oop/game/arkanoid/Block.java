package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Collidable;
import ac.biu.oop.game.core.HitListener;
import ac.biu.oop.game.core.HitNotifier;
import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.core.Velocity;
import ac.biu.oop.game.geometry.Collision;
import ac.biu.oop.game.geometry.EdgeType;
import ac.biu.oop.game.geometry.LineOrientation;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;






public class Block
  implements Sprite, Collidable, HitNotifier
{
  private static final int DEFAULT_WIDTH = 1;
  private static final int DEFAULT_HEIGHT = 1;
  private static final int DEFAULT_HIT_POINTS = 1;
  private Rectangle rectangle;
  private Drawer defaultStrokeDrawer;
  private Map<Integer, Drawer> strokeDrawers;
  private Drawer defaultFillDrawer;
  private Map<Integer, Drawer> fillDrawers;
  private int hitPoints;
  private List<HitListener> listeners;
  
  public Block(int x, int y, int width, int height, int hitPoints, Color bg, Color fg)
  {
    this(x, y);
    setWidth(width);
    setHeight(height);
    setHitPoints(hitPoints);
    
    defaultFillDrawer = new FillDrawer(bg);
    defaultStrokeDrawer = new StrokeDrawer(fg);
  }
  
  public Block(int x, int y) {
    rectangle = new Rectangle(x, y, 1.0D, 1.0D);
    hitPoints = 1;
    
    strokeDrawers = new HashMap();
    fillDrawers = new HashMap();
    defaultFillDrawer = new NullDrawer(null);
    defaultStrokeDrawer = new NullDrawer(null);
    
    listeners = new LinkedList();
  }
  
  public void addStrokeDrawer(int hitPointsValue, Drawer d) {
    strokeDrawers.put(Integer.valueOf(hitPointsValue), d);
  }
  
  public void addFillDrawer(int hitPointsValue, Drawer d) {
    fillDrawers.put(Integer.valueOf(hitPointsValue), d);
  }
  
  public void setDefaultStrokeDrawer(Drawer d) {
    defaultStrokeDrawer = d;
  }
  
  public void setDefaultFillDrawer(Drawer d) {
    defaultFillDrawer = d;
  }
  
  public void setWidth(int value) {
    rectangle = new Rectangle(rectangle.getTopLeftPoint().getX(), rectangle.getTopLeftPoint().getY(), value, rectangle.getHeight());
  }
  


  public void setHeight(int value)
  {
    rectangle = new Rectangle(rectangle.getTopLeftPoint().getX(), rectangle.getTopLeftPoint().getY(), rectangle.getWidth(), value);
  }
  


  public void setHitPoints(int value)
  {
    hitPoints = value;
  }
  
  public void addTo(GameLevel level) {
    level.addCollidable(this);
    level.addSprite(this);
  }
  
  public void removeFrom(GameLevel level) {
    level.removeCollidable(this);
    level.removeSprite(this);
  }
  



  public void drawOn(DrawSurface surface)
  {
    if (fillDrawers.containsKey(Integer.valueOf(hitPoints))) {
      ((Drawer)fillDrawers.get(Integer.valueOf(hitPoints))).drawAt(surface, rectangle);
    } else {
      defaultFillDrawer.drawAt(surface, rectangle);
    }
    

    if (strokeDrawers.containsKey(Integer.valueOf(hitPoints))) {
      ((Drawer)strokeDrawers.get(Integer.valueOf(hitPoints))).drawAt(surface, rectangle);
    } else {
      defaultStrokeDrawer.drawAt(surface, rectangle);
    }
  }
  
  public Velocity hit(Ball hitter, Collision collisionInfo, Velocity velocity)
  {
    hitPoints -= 1;
    notifyHit(hitter);
    
    if (collisionInfo.getEdgeType().getOrientation() == LineOrientation.X) {
      return velocity.deflect(-1.0D, 1.0D);
    }
    return velocity.deflect(1.0D, -1.0D);
  }
  


  public void addHitListener(HitListener hl)
  {
    listeners.add(hl);
  }
  
  public void removeHitListener(HitListener hl)
  {
    listeners.remove(hl);
  }
  
  private void notifyHit(Ball hitter) {
    for (HitListener listener : new LinkedList(listeners)) {
      listener.hitEvent(this, hitter);
    }
  }
  
  public Rectangle getCollisionRectangle()
  {
    return rectangle;
  }
  
  public int getHitPoints() {
    return hitPoints;
  }
  

  public void timePassed(double dt) {}
  
  public double getX()
  {
    return getCollisionRectangle().getTopLeftPoint().getX();
  }
  
  public double getY() {
    return getCollisionRectangle().getTopLeftPoint().getY();
  }
  
  public static abstract interface Drawer
  {
    public abstract void drawAt(DrawSurface paramDrawSurface, Rectangle paramRectangle);
  }
  
  public static class FillDrawer implements Block.Drawer
  {
    private Color color;
    
    public FillDrawer(Color color)
    {
      this.color = color;
    }
    
    public void drawAt(DrawSurface ds, Rectangle r)
    {
      ds.setColor(color);
      ds.fillRectangle((int)r.getTopLeftPoint().getX(), (int)r.getTopLeftPoint().getY(), (int)r.getWidth(), (int)r.getHeight());
    }
  }
  


  public static class StrokeDrawer
    implements Block.Drawer
  {
    private Color color;
    

    public StrokeDrawer(Color color)
    {
      this.color = color;
    }
    
    public void drawAt(DrawSurface ds, Rectangle r)
    {
      ds.setColor(color);
      ds.drawRectangle((int)r.getTopLeftPoint().getX(), (int)r.getTopLeftPoint().getY(), (int)r.getWidth(), (int)r.getHeight());
    }
  }
  


  public static class ImageDrawer
    implements Block.Drawer
  {
    private Image img;
    

    public ImageDrawer(Image img)
    {
      this.img = img;
    }
    
    public void drawAt(DrawSurface ds, Rectangle r)
    {
      ds.drawImage((int)r.getTopLeftPoint().getX(), (int)r.getTopLeftPoint().getY(), img);
    }
  }
  
  private static class NullDrawer
    implements Block.Drawer
  {
    private NullDrawer() {}
    
    public void drawAt(DrawSurface ds, Rectangle r) {}
  }
}
