import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class Game {
  private double width;
  private double height;
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

  // Initialize a new game: create the Blocks and Ball (and Paddle)
  // and add them to the game.
  public void initialize() {
    gui = new GUI("myGame", (int) width, (int) height);
    environment = new GameEnvironment();
    sprites = new SpriteCollection();

    Ball ball = new Ball(170, 200, 15, Color.BLACK, environment);
    ball.setVelocity(Velocity.fromAngleAndSpeed(30, 100));
    ball.addToGame(this);

    int padding = 20;
    Block top = new Block(0, 0, width, padding, Color.BLUE);
    top.addToGame(this);

    Block left = new Block(0, 0, padding, height, Color.BLUE);
    left.addToGame(this);

    Block right = new Block(width - padding, 0, padding, height, Color.BLUE);
    right.addToGame(this);

    Block bottom = new Block(0, height - padding, width, padding, Color.BLUE);
    bottom.addToGame(this);

    Block b = new Block(50,60,60,100, Color.GREEN);
    b.addToGame(this);
  }

  // Run the game -- start the animation loop.
  public void run() {
    Sleeper sleeper = new Sleeper();
    int framesPerSecond = 300;
    int millisecondsPerFrame = 1000 / framesPerSecond;
    while (true) {
      long startTime = System.currentTimeMillis(); // timing

      DrawSurface d = gui.getDrawSurface();
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