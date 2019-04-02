import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * The Game class implements a simple arkanoid game.
 *
 * @author zeiraid 322607177
 */
public class Game {
  private double width;
  private double height;
  private double padding;
  private double paddleWidth;
  private SpriteCollection sprites;
  private GameEnvironment environment;
  private GUI gui;

  /**
   * Defualt Constructor.
   *
   * @param width  Width of the board.
   * @param height Height of the board.
   */
  public Game(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Adds a collidable object to the board.
   *
   * @param c the object to add.
   */
  public void addCollidable(Collidable c) {
    environment.addCollidable(c);
  }

  /**
   * Adds a sprite object to the board.
   *
   * @param s the object to add.
   */
  public void addSprite(Sprite s) {
    sprites.addSprite(s);
  }

  /**
   * generates the border blocks bounding the game board.
   *
   * @param color the color for the borders.
   */
  private void generateBorders(Color color) {
    Block top = new Block(0, 0, width, padding, color);
    top.addToGame(this);
    Block left = new Block(0, 0, padding, height, color);
    left.addToGame(this);
    Block right = new Block(width - padding, 0, padding, height, color);
    right.addToGame(this);
    Block bottom = new Block(0, height - padding, width, padding, color);
    bottom.addToGame(this);
  }

  /**
   * Generates a block pattern for the specified level.
   *
   * @param rows        the seed for the size of the pattern.
   * @param blockWidth  Width of each block in the pattern.
   * @param blockHeight Height of each block in the pattern.
   */
  private void generatePattern(int rows, double blockWidth, int blockHeight) {
    Random rand = new Random();
    int count = 3;
    for (int row = 0; row < rows; row++) {
      float[] hsbVals = Color.RGBtoHSB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), null); // Generates a
      Color color = Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]); // random Color.
      double y = padding + 0.24 * (height - 2 * padding) + row * blockHeight;
      for (int col = 1; col <= 2 * rows - row; col++) {
        double x = width - padding - col * blockWidth;
        CountingBlock cb = new CountingBlock(x, y, blockWidth, blockHeight, color, count);
        cb.addToGame(this);
      }
      if (row == 0) {
        count--;
      }
    }
  }

  /**
   * Adds a ball with a specified radius to the ball, with a random velocity
   * (-30<=angle<=-120 & 5<=speed<=8).
   *
   * @param radius the radius for the ball.
   */
  private void addBall(int radius) {
    Ball ball = new Ball((width - radius) / 2.0, height - padding - radius - paddleWidth, radius, Color.white,
        environment);
    Random r = new Random();
    ball.setVelocity(Velocity.fromAngleAndSpeed(r.nextInt(90) - 120, r.nextInt(3) + 50));
    ball.addToGame(this);
  }

  /**
   * Creates a movable collidable paddle.
   *
   * @param pWidth   width of the paddle.
   * @param pHeight  height of the paddle.
   * @param keyboard KeyboardSensor object which contains the information about
   *                 which keys are pressed.
   */
  private void addPaddle(double pWidth, double pHeight, KeyboardSensor keyboard) {
    Paddle p = new Paddle(width, height, pWidth, pHeight, padding, Color.ORANGE, keyboard);
    this.paddleWidth = pWidth;
    p.addToGame(this);
  }

  /**
   * Initialize a new game: create the Blocks and Ball (and Paddle) and add them
   * to the game.
   */
  public void initialize() {
    gui = new GUI("myGame", (int) width, (int) height);
    environment = new GameEnvironment();
    sprites = new SpriteCollection();
    padding = 2;

    double paddleHeight = 20;
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    addPaddle(width / 7, paddleHeight, keyboard);
    generateBorders(Color.BLACK);
    for (int i = 0; i < 9; i++) {
      addBall(5);
    }
    generatePattern(6, width / 13, 20);

  }

  /**
   * Run the game -- start the animation loop.
   */
  public void run() {
    Sleeper sleeper = new Sleeper();
    int framesPerSecond = 300;
    int millisecondsPerFrame = 1000 / framesPerSecond;
    while (true) {
      long startTime = System.currentTimeMillis(); // timing
      DrawSurface d = gui.getDrawSurface();
      d.setColor(Color.DARK_GRAY);
      d.fillRectangle(0, 0, (int) width, (int) height);
      d.setColor(Color.RED);
      this.sprites.drawAllOn(d);
      gui.show(d);
      this.sprites.notifyAllTimePassed();

      // timing
      long usedTime = System.currentTimeMillis() - startTime;
      long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
      if (milliSecondLeftToSleep > 0) {
        sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}