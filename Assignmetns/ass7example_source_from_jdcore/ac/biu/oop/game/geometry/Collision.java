package ac.biu.oop.game.geometry;



public class Collision
{
  private Point closestPoint;
  

  private EdgeType edgeType;
  


  public Collision(Point closestPoint, EdgeType edgeType)
  {
    this.closestPoint = closestPoint;
    this.edgeType = edgeType;
  }
  
  public Point getPoint() {
    return closestPoint;
  }
  
  public EdgeType getEdgeType() {
    return edgeType;
  }
}
