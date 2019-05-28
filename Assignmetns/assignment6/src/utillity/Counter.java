package utillity;

/**
 * The Counter class is a simple counter.
 */
public class Counter {
  private int c;

  /**
   * initializes a counter.
   */
  public Counter() {
    c = 0;
  }

  /**
   * initializes a counter.
   *
   * @param c def. count.
   */
  public Counter(int c) {
    this.c = c;
  }

  /**
   * add number to current count.
   *
   * @param number number to be added.
   */
  public void increase(int number) {
    c += number;
  }

  /**
   * subtract number from current count.
   *
   * @param number number to be added.
   */
  public void decrease(int number) {
    c -= number;
  }

  /**
   * @return current count.
   */
  public int getValue() {
    return c;
  }
}