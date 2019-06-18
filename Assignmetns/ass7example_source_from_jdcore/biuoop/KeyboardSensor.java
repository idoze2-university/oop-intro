package biuoop;

public abstract interface KeyboardSensor
{
  public static final String LEFT_KEY = "left";
  public static final String RIGHT_KEY = "right";
  public static final String UP_KEY = "up";
  public static final String DOWN_KEY = "down";
  public static final String SPACE_KEY = "space";
  public static final String ENTER_KEY = "enter";
  public static final String RETURN_KEY = "return";
  
  public abstract boolean isPressed(String paramString);
}
