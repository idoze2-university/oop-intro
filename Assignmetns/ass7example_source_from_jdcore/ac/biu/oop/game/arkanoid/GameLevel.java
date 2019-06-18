package ac.biu.oop.game.arkanoid;

import ac.biu.oop.game.animation.Animation;
import ac.biu.oop.game.animation.AnimationRunner;
import ac.biu.oop.game.animation.CountdownAnimation;
import ac.biu.oop.game.animation.KeyPressStoppableAnimation;
import ac.biu.oop.game.animation.PauseScreen;
import ac.biu.oop.game.core.Collidable;
import ac.biu.oop.game.core.Counter;
import ac.biu.oop.game.core.Sprite;
import ac.biu.oop.game.core.Velocity;
import ac.biu.oop.game.geometry.Point;
import ac.biu.oop.game.geometry.Rectangle;
import ac.biu.oop.game.levels.LevelInformation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;





public class GameLevel
  implements Animation
{
  private int WALLS_WIDTH = 25;
  private int WALLS_TOP_OFFSET = 18;
  private int PADDLE_HEIGHT = 18;
  
  private SpriteCollection sprites;
  
  private GameEnvironment environment;
  
  private LevelInformation levelInfo;
  
  private KeyboardSensor keyboardSensor;
  private AnimationRunner runner;
  private Counter remainingBlocksCounter;
  private Counter ballsCounter;
  private Counter scoreCounter;
  private Counter remainingLivesCounter;
  private boolean done;
  private Paddle paddle;
  private int gameWidth;
  private int gameHeight;
  
  public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner runner, Counter scoreCounter, Counter remainingLivesCounter, int gameWidth, int gameHeight)
  {
    this.levelInfo = levelInfo;
    this.keyboardSensor = keyboardSensor;
    this.runner = runner;
    this.scoreCounter = scoreCounter;
    this.remainingLivesCounter = remainingLivesCounter;
    this.gameWidth = gameWidth;
    this.gameHeight = gameHeight;
    

    sprites = new SpriteCollection();
    environment = new GameEnvironment();
    
    remainingBlocksCounter = new Counter(levelInfo.numberOfBlocksToRemove());
    ballsCounter = new Counter();
  }
  
  public int getRemainingBlocks() {
    return remainingBlocksCounter.getValue();
  }
  
  public void addCollidable(Collidable c) {
    environment.addCollidable(c);
  }
  
  public void addSprite(Sprite s) { sprites.addSprite(s); }
  
  public void removeCollidable(Collidable c)
  {
    environment.removeCollidable(c);
  }
  
  public void removeSprite(Sprite s) { sprites.removeSprite(s); }
  




  public void initialize()
  {
    sprites.addSprite(new RectangleSprite(Color.WHITE, new Rectangle(0.0D, 0.0D, gameWidth, WALLS_TOP_OFFSET)));
    
    initializeWalls();
    
    initializeDeathRegion();
    
    initializeBlocks();
    
    initializePaddle();
    
    initializeIndicators();
  }
  



  private void initializeIndicators()
  {
    LivesIndicator livesIndicator = new LivesIndicator(100, 15, remainingLivesCounter);
    livesIndicator.addTo(this);
    
    ScoreIndicator scoreIndicator = new ScoreIndicator(300, 15, scoreCounter);
    scoreIndicator.addTo(this);
    
    LevelNameIndicator levelNameIndicator = new LevelNameIndicator(500, 15, levelInfo.levelName());
    levelNameIndicator.addTo(this);
  }
  
  private void initializePaddle() {
    paddle = new Paddle(gameWidth / 2 - levelInfo.paddleWidth() / 2, gameHeight - 35, levelInfo.paddleWidth(), PADDLE_HEIGHT, WALLS_WIDTH, gameWidth - WALLS_WIDTH - levelInfo.paddleWidth(), keyboardSensor, "left", "right", levelInfo.paddleSpeed());
    



    paddle.addTo(this);
  }
  
  private void initializeWalls()
  {
    Block leftWall = new Block(0, WALLS_TOP_OFFSET + WALLS_WIDTH, WALLS_WIDTH, gameHeight, Integer.MAX_VALUE, Color.GRAY, Color.GRAY);
    leftWall.addTo(this);
    
    Block rightWall = new Block(0, WALLS_TOP_OFFSET, gameWidth, WALLS_WIDTH, Integer.MAX_VALUE, Color.GRAY, Color.GRAY);
    rightWall.addTo(this);
    
    Block topWall = new Block(gameWidth - WALLS_WIDTH, WALLS_TOP_OFFSET + WALLS_WIDTH, WALLS_WIDTH, gameHeight, Integer.MAX_VALUE, Color.GRAY, Color.GRAY);
    topWall.addTo(this);
  }
  
  private void initializeDeathRegion()
  {
    Block deathRegion = new Block(0, gameHeight, gameWidth, WALLS_WIDTH, Integer.MAX_VALUE, Color.GRAY, Color.GRAY);
    deathRegion.addTo(this);
    
    BallRemover ballRemover = new BallRemover(this, ballsCounter);
    deathRegion.addHitListener(ballRemover);
  }
  
  private void initializeBlocks()
  {
    BlockRemover blockRemover = new BlockRemover(this, remainingBlocksCounter);
    ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
    
    for (Block block : levelInfo.blocks()) {
      block.addHitListener(blockRemover);
      block.addHitListener(scoreTrackingListener);
      block.addTo(this);
    }
  }
  

  private void initializeBalls()
  {
    for (Velocity ballStartVelocity : levelInfo.initialBallVelocities())
    {
      double ballX = paddle.getX() + levelInfo.paddleWidth() / 2;
      
      Ball ball = new Ball(new Point(ballX, paddle.getY() - 10.0D), 5.0D, environment);
      ball.setVelocity(ballStartVelocity);
      
      ball.addTo(this);
      
      ballsCounter.increase(1);
    }
  }
  



  public void playOneTurn()
  {
    done = false;
    
    paddle.setX(gameWidth / 2 - levelInfo.paddleWidth() / 2);
    
    initializeBalls();
    
    runner.play(new CountdownAnimation(2.0D, 3, sprites, levelInfo.getBackgroundSprite()));
    
    runner.play(this);
  }
  
  public boolean shouldStop()
  {
    return done;
  }
  

  public void doOneFrame(DrawSurface ds, double dt)
  {
    if (keyboardSensor.isPressed("p")) {
      runner.play(new KeyPressStoppableAnimation(new PauseScreen(), keyboardSensor, "space"));
    }
    



    if (levelInfo.getBackgroundSprite() != null) {
      levelInfo.getBackgroundSprite().drawOn(ds);
    }
    
    sprites.notifyAllTimePassed(dt);
    sprites.drawAllOn(ds);
    

    if (ballsCounter.getValue() == 0) {
      done = true;
    }
    

    if (remainingBlocksCounter.getValue() == 0) {
      scoreCounter.increase(100);
      done = true;
    }
  }
}
