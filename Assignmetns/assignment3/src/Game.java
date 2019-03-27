import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

public class Game {
  private double width;
  private double height;
  private double padding;
  private int ballCount;
  private SpriteCollection sprites;
  private GameEnvironment environment;
  private GUI gui;

  public Game(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public void addCollidable(Collidable c) {
    environment.addCollidable(c);
  }

  public void addSprite(Sprite s) {
    sprites.addSprite(s);
  }

  private void generateBorders() {
    Block top = new Block(0, 0, width, padding, Color.BLUE);
    top.addToGame(this);
    Block left = new Block(0, 0, padding, height, Color.BLUE);
    left.addToGame(this);
    Block right = new Block(width - padding, 0, padding, height, Color.BLUE);
    right.addToGame(this);
    Block bottom = new Block(0, height - padding, width, padding, Color.BLUE);
    bottom.addToGame(this);
  }

  private void generatePattern(int rows, double blockWidth, int blockHeight) {
    Random rand = new Random();
    int count = 3;
    for (int row = 0; row < rows; row++) {
      float[] hsbVals = Color.RGBtoHSB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), null);
      Color color = Color.getHSBColor(hsbVals[0], hsbVals[1], hsbVals[2]);
      double y = padding + 0.24 * (height - 2 * padding) + row * blockHeight;
      for (int col = 1; col <= 2 * rows - row; col++) {
        double x = width - padding - col * blockWidth;
        CountingBlock cb = new CountingBlock(x, y, blockWidth, blockHeight, color, rows + rand.nextInt(count));
        cb.addToGame(this);
      }
      if (row == 0) {
        count--;
      }
    }
  }

  public void addBall(int radius) {
    Ball ball = new Ball((width - radius) / 2.0, height - padding - radius - padding, radius, Color.white,
        environment);
    Random r = new Random();
    ball.setVelocity(Velocity.fromAngleAndSpeed(r.nextInt(360), r.nextInt(3) + 5));
    ball.addToGame(this);
  }

  // Initialize a new game: create the Blocks and Ball (and Paddle)
  // and add them to the game.
  public void initialize() {
    gui = new GUI("myGame", (int) width, (int) height);
    environment = new GameEnvironment();
    sprites = new SpriteCollection();

    padding = 2;
    generateBorders();

    KeyboardSensor keyboard = gui.getKeyboardSensor();

    double paddleWidth = width / 7;
    double paddleHeight = 20;
    Paddle p = new Paddle(width, height, paddleWidth, paddleHeight, padding, Color.ORANGE, keyboard);
    p.addToGame(this);
    ballCount = 1;
  }

  public void remove(Sprite object) {
    sprites.remove(object);
    if ((Collidable) object != null) {
      environment.remove((Collidable) object);
    }
  }

  // Run the game -- start the animation loop.
  public void run() {
    Sleeper sleeper = new Sleeper();
    int framesPerSecond = 120;
    int millisecondsPerFrame = 1000 / framesPerSecond;
    float bg = 0f;
    int level = 0;
    while (true) {
      long startTime = System.currentTimeMillis(); // timing
      DrawSurface d = gui.getDrawSurface();
      d.fillRectangle(0, 0, (int) width, (int) height);
      d.setColor(Color.getHSBColor(bg += 0.001, 100f, 100f));
      int textSize = 100;
      d.drawText((int)(width-textSize)/2, (int)(height-textSize)/2, String.valueOf(level), textSize);
      this.sprites.drawAllOn(d);
      gui.show(d);
      this.sprites.notifyAllTimePassed();

      if (sprites.count() < 5 + ballCount ) {
        addBall(5);
        ballCount++;
        level++;
        generatePattern(level, width / (2 * (level) + 1), 20);
      }

      // timing
      long usedTime = System.currentTimeMillis() - startTime;
      long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
      if (milliSecondLeftToSleep > 0) {
        sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}