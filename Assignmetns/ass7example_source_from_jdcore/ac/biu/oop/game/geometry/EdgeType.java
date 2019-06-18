package ac.biu.oop.game.geometry;








public enum EdgeType
{
  LEFT(LineOrientation.X), 
  RIGHT(LineOrientation.X), 
  TOP(LineOrientation.Y), 
  BOTTOM(LineOrientation.Y);
  
  private LineOrientation orientation;
  
  private EdgeType(LineOrientation orientation) {
    this.orientation = orientation;
  }
  
  public LineOrientation getOrientation() {
    return orientation;
  }
}
