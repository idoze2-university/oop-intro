package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.core.Counter;
import java.io.Serializable;








public class Player
  implements Serializable
{
  private transient Counter livesCounter;
  private transient int numberOfLives;
  private Counter scoreCounter;
  private String name;
  
  public Player(int numberOfLives)
  {
    this.numberOfLives = numberOfLives;
    reset();
  }
  
  public Counter getScoreCounter() {
    return scoreCounter;
  }
  
  public Counter getLivesCounter() {
    return livesCounter;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void reset() {
    livesCounter = new Counter(numberOfLives);
    scoreCounter = new Counter();
    name = null;
  }
}
