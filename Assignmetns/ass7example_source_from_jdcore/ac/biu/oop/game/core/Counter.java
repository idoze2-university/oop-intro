package ac.biu.oop.game.core;

import java.io.Serializable;






public class Counter
  implements Serializable
{
  private int value;
  
  public Counter()
  {
    this(0);
  }
  
  public Counter(int initialValue) {
    value = initialValue;
  }
  
  public void increase(int number)
  {
    value += number;
  }
  
  public void decrease(int number) {
    value -= number;
  }
  
  public int getValue() {
    return value;
  }
}
