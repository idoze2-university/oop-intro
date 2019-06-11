package game.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.collision.Collidable;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.HitListener;
import game.listeners.ScoreTrackingListener;
import game.screens.CountdownAnimation;
import game.screens.PauseScreen;
import geometry.Point;
import geometry.Velocity;
import utillity.Counter;

/**
 * The Game class implements a simple arkanoid game.
 *
 * @author zeiraid 322607177
 */
public class GameLevel implements Animation {
  private double width;
  private double height;
  private double padding;
  private double paddleHeight;
  private SpriteCollection sprites;
  private GameEnvironment environment;
  private Paddle currentPaddle;
  private Counter remainingBlocks;
  private Counter remainingBalls;
  private Counter scoreCounter;
  private LivesIndicator lives;
  private KeyboardSensor keyboard;
  private boolean running;
  private AnimationRunner runner;
  private GUI gui;

  /**
   * Defualt Constructor.
   *
   * @param width  Width of the board.
   * @param height Height of the board.
   */
  public GameLevel(double width, double height) {
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
   * Removes a collidable object to the board.
   *
   * @param c the object to Remove.
   */
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

  /**
   * Removes a sprite object to the board.
   *
   * @param s the object to remove.
   */
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
   * @param blockWidth  Width of each block in the pattern.
   * @param blockHeight Height of each block in the pattern.
   */
  private void generatePattern(double blockWidth, int blockHeight) {
    Random rand = new Random();
    remainingBlocks = new Counter();
    ArrayList<HitListener> listeners = new ArrayList<HitListener>();
    listeners.add(new BlockRemover(this, remainingBlocks));
    listeners.add(new ScoreTrackingListener(scoreCounter));
    int rows = 4;
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
  private void addBall(Ball b) {
    b.addToGame(this);
    remainingBalls.increase(1);
    ball.addToGame(this);
  }

  /**
   * Creates a movable collidable paddle.
   *
   * @param pWidth  width of the paddle.
   * @param pHeight height of the paddle.
   */
  private void addPaddle(double pWidth, double pHeight) {
    if (currentPaddle != null) {
      currentPaddle.removeFromGame(this);
    }
    currentPaddle = new Paddle(width, height, pWidth, pHeight, padding, Color.ORANGE, keyboard);
    currentPaddle.addToGame(this);
  }

  /**
   * Initialize a new game: create the Blocks and Balls (and Paddle) and add them
   * to the game.
   */
  public void initGame() {
    gui = new GUI("myGame", (int) width, (int) height);
    environment = new GameEnvironment();
    sprites = new SpriteCollection();
    sprites.addSprite(new Block(0, 0, this.width, this.height, Color.GRAY));
    scoreCounter = new Counter();
    remainingBalls = new Counter();
    lives = new LivesIndicator(new Point(0, 10), 4);
    keyboard = gui.getKeyboardSensor();
    this.runner = new AnimationRunner(gui);
    padding = 10;
    generateBorders(Color.BLACK);
    sprites.addSprite(lives);
    paddleHeight = 15;
    generatePattern(width / 13, 20);
  }

  /**
   * Initialize a new turn: create the Blocks and Balls (and Paddle) and add them
   * to the game.
   *
   * @param balls the amount of balls to generate.
   */
  public void initTurn(int balls) {
    for (int i = 0; i < balls; i++) {
      addBall(5);
    }
    addPaddle(width / 7, paddleHeight);
  }

  /**
   * Run the game -- start the animation loop.
   */
  public void run() {
    initGame();
    while (lives.getLives() > 0) {
      initTurn(2);
      playOneTurn();
    }
    gui.close();
  }

  /**
   * Run a single turn.
   */
  public void playOneTurn() {
    this.runner.run(new CountdownAnimation(2, 3, sprites));
    this.running = true;
    this.runner.run(this);
  }

  @Override
  public boolean shouldStop() {
    return !this.running;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    d.setColor(Color.WHITE);
    d.setColor(Color.RED);
    this.sprites.drawAllOn(d);
    this.sprites.notifyAllTimePassed();
    if (remainingBlocks.getValue() == 0) {
      this.running = false;
    }
    if (remainingBalls.getValue() == 0) {
      lives.decrease();
      this.running = false;
    }
    if (this.keyboard.isPressed("p")) {
      this.runner.run(new PauseScreen(this.keyboard));
    }
  }
}
