class Block implements Collidable {

  private Rectangle container;

  public Block(Rectangle container) {
    this.container = container;
  }

  /**
   * @return the "collision shape" of the object.
   */
  public Rectangle getCollisionRectangle() {
    return container;
  }

  /**
   * Notify the object that we collided with it at collisionPoint with a given
   * velocity. The return is the new velocity expected after the hit (based on the
   * force the object inflicted on us).
   *
   * @param collisionPoint
   *                          Point at which we collided with the object.
   * @param currentVelocity
   *                          Velocity with which we collided with the object.
   * @return the new velocity expected after the hit
   */
  public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    return currentVelocity;
  }
}