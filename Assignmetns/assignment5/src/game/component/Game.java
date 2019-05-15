package game.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Velocity;
import game.collision.Collidable;
import game.listeners.HitListener;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.ScoreTrackingListener;
import utillity.Counter;

/**
 * The Game class implements a simple arkanoid game.
 *
 * @author zeiraid 322607177
 */
public class Game {
  private double width;
  private double height;
  private double padding;
  private double paddleHeight;
  private SpriteCollection sprites;
  private GameEnvironment environment;
  private Counter remainingBlocks;
  private Counter remainingBalls;
  private Counter scoreCounter;
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

  public void removeCollidable(Collidable c) {
    environment.remove(c);
  }

  /**
   * Adds a sprite object to the board.
   *
   * @param s the object to add.
   */
  public void addSprite(Sprite s) {
    sprites.addSprite(s);
  }

  public void removeSprite(Sprite s) {
    sprites.remove(s);
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
    Block bottom = new Block(0, height, width, 1, color);
    bottom.addHitListener(new BallRemover(this, remainingBalls));
    bottom.addToGame(this);
  }

  /**
   * Generates a block pattern for the specified level.
   *
   * @param rows        the seed for the size of the pattern.
   * @param blockWidth  Width of each block in the pattern.
   * @param blockHeight Height of each block in the pattern.
   * @param listeners   HitListeners to listen for all the pattern's created
   *                    blocks.
   */
  private void generatePattern(int rows, double blockWidth, int blockHeight, ArrayList<HitListener> listeners) {
    Random rand = new Random();
    int count = 3;
    for (int row = 0; row < rows; row++) {
      float[] hsbVals = Color.RGBtoHSB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), null); // Generates a
      Color color = Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]); // random Color.
      double y = padding + 0.24 * (height - 2 * padding) + row * blockHeight;
      for (int col = 1; col <= 2 * rows - row; col++) {
        double x = width - padding - col * blockWidth;
        CountingBlock cb = new CountingBlock(x, y, blockWidth, blockHeight, color, count);
        for (HitListener listener : listeners) {
          cb.addHitListener(listener);
        }
        cb.addToGame(this);
        remainingBlocks.increase(1);
      }
      if (row == 0) {
        count--;
      }
    }
  }

  /**
   * Adds a ball with a specified radius to the ball, with a random velocity
   * direction.
   *
   * @param radius the radius for the ball.
   */
  private void addBall(int radius) {
    Ball ball = new Ball((width - radius) / 2.0, height - padding - radius - paddleHeight * 2, radius, Color.white,
        environment);
    Random r = new Random();
    ball.setVelocity(Velocity.fromAngleAndSpeed(r.nextInt(90) + 45, 7));
    remainingBalls.increase(1);
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
    sprites.addSprite(new LivesIndicator(null, 0));
    padding = 14;
    scoreCounter = new Counter();
    this.paddleHeight = 20;
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    remainingBalls = new Counter();
    generateBorders(Color.BLACK);
    for (int i = 0; i < 3; i++) {
      addBall(5);
    }
    addPaddle(width / 7, paddleHeight, keyboard);
    remainingBlocks = new Counter();
    ArrayList<HitListener> blockListeners = new ArrayList<HitListener>();
    blockListeners.add(new BlockRemover(this, remainingBlocks));
    blockListeners.add(new ScoreTrackingListener(scoreCounter));
    generatePattern(6, width / 13, 20, blockListeners);
  }

  /**
   * Run the game -- start the animation loop.
   */
  public void run() {
    playOneTurn();
    gui.close();
  }

  /**
   * Run a single turn.
   */
  public void playOneTurn() {
    Sleeper sleeper = new Sleeper();
    int framesPerSecond = 120;
    int millisecondsPerFrame = 1000 / framesPerSecond;
    while (remainingBalls.getValue() > 0) {

      long startTime = System.currentTimeMillis(); // timing
      DrawSurface d = gui.getDrawSurface();
      d.setColor(Color.DARK_GRAY);
      d.fillRectangle(0, 0, (int) width, (int) height);
      d.setColor(Color.WHITE);
      int textSize = 100;
      Point pt = new Point(0, 0).add((width - textSize) / 2.0, (height + textSize) / 2.0);
      d.drawText((int) pt.getX(), (int) pt.getY(), String.valueOf(scoreCounter.getValue()), textSize);
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
      if (remainingBlocks.getValue() == 0) {
        scoreCounter.increase(100);
        break;
      }
    }
  }
}
