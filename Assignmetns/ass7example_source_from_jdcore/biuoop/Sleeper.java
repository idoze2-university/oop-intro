package biuoop;




public class Sleeper
{
  public Sleeper() {}
  


  public void sleepFor(long milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
