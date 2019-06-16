package game.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.collision.Collidable;
import game.levels.LevelInformation;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.HitListener;
import game.listeners.ScoreTrackingListener;
import game.screens.CountdownAnimation;
import game.screens.PauseScreen;
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
  private LevelInformation currentLevelInfo;
  private LivesIndicator lives;
  private Counter scoreCounter;

  private Sprite levelNameLabel;
  private Sprite scoreLabel;
  private KeyboardSensor keyboard;
  private boolean running;
  private AnimationRunner runner;

  /**
   * Default Constructor.
   *
   * @param currentLevelInfo Level to be run.
   * @param keyboardSensor   Keyboard Sensor to be used.
   * @param animationRunner  animation runner to be used.
   * @param scoreCounter     scoreCounter to be used.
   * @param lives            the Lives counter to be used.
   */
  public GameLevel(LevelInformation currentLevelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
      Counter scoreCounter, LivesIndicator lives) {
    this.width = 800;
    this.height = 600;
    this.currentLevelInfo = currentLevelInfo;
    this.keyboard = keyboardSensor;
    this.runner = animationRunner;
    this.scoreCounter = scoreCounter;
    this.lives = lives;
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
    Block top = new Block(0, 0, width, padding * 2, color);
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
   * @param blocks array of blocks to load.
   */
  private void loadBlocks(List<Block> blocks) {
    remainingBlocks = new Counter();
    ArrayList<HitListener> listeners = new ArrayList<HitListener>();
    listeners.add(new BlockRemover(this, remainingBlocks));
    listeners.add(new ScoreTrackingListener(scoreCounter));
    for (Block cb : blocks) {
      for (HitListener listener : listeners) {
        cb.addHitListener(listener);
      }
      cb.addToGame(this);
      remainingBlocks.increase(1);
    }
  }

  /**
   * Adds a ball with a specified radius to the ball, with a random velocity
   * direction.
   *
   * @param radius the radius for the ball.
   * @param v      the velocity for the ball.
   */
  private void addBall(int radius, Velocity v) {
    Ball b = new Ball(width / 2, height - paddleHeight - radius - padding, radius, Color.WHITE, environment);
    b.setVelocity(v);
    b.addToGame(this);
    remainingBalls.increase(1);
  }

  /**
   * Creates a movable collidable paddle.
   *
   * @param pWidth  width of the paddle.
   * @param pHeight height of the paddle.
   * @param speed   the speed of the paddle.
   */
  private void addPaddle(double pWidth, double pHeight, double speed) {
    if (currentPaddle != null) {
      currentPaddle.removeFromGame(this);
    }
    currentPaddle = new Paddle(width, height, pWidth, pHeight, padding, keyboard, speed);
    currentPaddle.addToGame(this);
  }

  /**
   * Initialize a new game: create the Blocks and Balls (and Paddle) and add them
   * to the game.
   */
  public void initGame() {
    remainingBalls = new Counter();
    levelNameLabel = new Sprite() {
      private String name = "";

      @Override
      public void timePassed() {
        try {
          name = currentLevelInfo.levelName();
        } catch (Exception exception) {
          name = "";
        }
      }

      @Override
      public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(390, 20, "Current Level: " + name, 18);
      }
    };
    scoreLabel = new Sprite() {
      @Override
      public void timePassed() {
        // Nothing.
      }

      @Override
      public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(240, 20, "Score: " + scoreCounter.getValue(), 18);

      }
    };
  }

  /**
   * Initializes the turn according to the level specifications.
   *
   */
  public void initialize() {
    initGame();
    environment = new GameEnvironment();
    sprites = new SpriteCollection();
    addSprite(currentLevelInfo.getBackground());
    padding = 15;
    generateBorders(Color.BLACK);
    addSprite(lives);
    levelNameLabel.timePassed();
    addSprite(levelNameLabel);
    addSprite(scoreLabel);
    paddleHeight = 15;
    loadBlocks(currentLevelInfo.blocks());
    this.running = true;
  }

  /**
   * Initialize a new turn: create the Blocks and Balls (and Paddle) and add them
   * to the game.
   *
   */
  public void initTurn() {
    for (int i = 0; i < currentLevelInfo.numberOfBalls(); i++) {
      Velocity v = currentLevelInfo.initialBallVelocities().get(i);
      addBall(5, v);
    }
    addPaddle(currentLevelInfo.paddleWidth(), paddleHeight, currentLevelInfo.paddleSpeed());
  }

  /**
   * Run a single turn.
   */
  public void playOneTurn() {
    initTurn();
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
      PauseScreen p = new PauseScreen();
      this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", p));
    }
  }

  /**
   *
   * @return Wether or not the player has any remaining blocks to break.
   */
  public boolean hasBlocks() {
    return remainingBlocks.getValue() != 0;
  }
}
