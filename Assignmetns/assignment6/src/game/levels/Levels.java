package game.levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import game.component.Block;
import game.component.CountingBlock;
import game.component.Sprite;
import geometry.Velocity;

/**
 * The levels class provides a static method for getting information about the
 * pre-defined levels.
 */
public class Levels {

  /**
   *
   * @param level The index of the required leve.
   * @return the information of the specified level.
   */
  public static LevelInformation getLevel(int level) {

    switch (level) {
    case 1:
      return getLevel1();
    case 2:
      return getLevel2();
    case 3:
      return getLevel3();
    case 4:
      return getLevel4();
    default:
      break;
    }
    return null;
  }

  /**
   *
   * @return Information for Level1.
   */
  private static LevelInformation getLevel1() {
    return new LevelInformation() {

      @Override
      public int paddleWidth() {
        return 100;
      }

      @Override
      public int paddleSpeed() {
        return 0;
      }

      @Override
      public int numberOfBlocksToRemove() {
        return 1;
      }

      @Override
      public int numberOfBalls() {
        return 1;
      }

      @Override
      public String levelName() {
        return "Direct Hit";
      }

      @Override
      public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<Velocity>();
        lst.add(new Velocity(0, -7));
        return lst;
      }

      @Override
      public Sprite getBackground() {
        Sprite background = new Sprite() {
          @Override
          public void drawOn(DrawSurface d) {
            d.setColor(Color.gray.darker().darker().darker());
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.blue);
            for (int i = 0; i < 3; i++) {
              int radius = 50 + i * 25;
              d.drawCircle(400, 110, radius);
            }
            d.drawLine(400, 110 - 130, 400, 110 - 25);
            d.drawLine(400, 110 + 25, 400, 110 + 130);
            d.drawLine(400 - 130, 110, 400 - 25, 110);
            d.drawLine(400 + 25, 110, 400 + 130, 110);
          }

          @Override
          public void timePassed() {
          }
        };
        return background;
      }

      @Override
      public List<Block> blocks() {
        List<Block> lst = new ArrayList<Block>();
        lst.add(new CountingBlock(400 - 15, 110 - 15, 30, 30, Color.RED, 1));
        return lst;
      }
    };
  }

  /**
   *
   * @return Information for Level2
   */
  private static LevelInformation getLevel2() {
    return new LevelInformation() {

      @Override
      public int paddleWidth() {
        return 700;
      }

      @Override
      public int paddleSpeed() {
        return 3;
      }

      @Override
      public int numberOfBlocksToRemove() {
        return 15;
      }

      @Override
      public int numberOfBalls() {
        return 10;
      }

      @Override
      public String levelName() {
        return "Wide Easy";
      }

      @Override
      public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<Velocity>();
        double zero = 90;
        double offset = 11;
        double speed = 7;
        for (int i = 1; i <= 5; i++) {
          lst.add(Velocity.fromAngleAndSpeed(zero + (i * offset), speed));
          lst.add(Velocity.fromAngleAndSpeed(zero - (i * offset), speed));
        }
        return lst;
      }

      @Override
      public Sprite getBackground() {
        Sprite background = new Sprite() {
          @Override
          public void drawOn(DrawSurface d) {
            int x = 120, y = 120;
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.decode("#efe7b0"));
            for (int i = 0; i < 66; i++) {
              d.drawLine(x, y, i * 12, 200);
            }
            d.setColor(Color.decode("#efe7b0"));
            d.fillCircle(x, y, 50);
            d.setColor(Color.decode("#ecd749"));
            d.fillCircle(x, y, 40);
            d.setColor(Color.decode("#ffe118"));
            d.fillCircle(x, y, 30);
          }

          @Override
          public void timePassed() {
          }
        };
        return background;
      }

      @Override
      public List<Block> blocks() {
        List<Block> lst = new ArrayList<Block>();
        int x = 18;
        double width = 51;
        Color[] colors = {
            // Color pattern for the blocks.
            Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN,
            Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN };
        for (Color c : colors) {
          lst.add(new CountingBlock(x, 200, width, 25, c, 1));
          x += width;
        }
        return lst;
      }
    };
  }

  /**
   *
   * @return Information for Level3
   */
  private static LevelInformation getLevel3() {
    return new LevelInformation() {

      @Override
      public int paddleWidth() {
        return 170;
      }

      @Override
      public int paddleSpeed() {
        return 8;
      }

      @Override
      public int numberOfBlocksToRemove() {
        return 40;
      }

      @Override
      public int numberOfBalls() {
        return 2;
      }

      @Override
      public String levelName() {
        return "Green 3";
      }

      @Override
      public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<Velocity>();
        double zero = 90;
        double offset = 15;
        double speed = 7;
        lst.add(Velocity.fromAngleAndSpeed(zero + (offset), speed));
        lst.add(Velocity.fromAngleAndSpeed(zero - (offset), speed));
        return lst;
      }

      @Override
      public Sprite getBackground() {
        Sprite background = new Sprite() {
          @Override
          public void drawOn(DrawSurface d) {
            d.setColor(Color.decode("#2a8215"));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.decode("#2e2a29"));
            d.fillRectangle(50, 400, 104, 200);
            d.setColor(Color.WHITE);
            int w = 10, h = 25;
            int xoff = 6, yoff = 9;
            for (int row = 0; row < 6; row++) {
              for (int col = 0; col < 6; col++) {
                d.fillRectangle(50 + xoff + (w + xoff) * col, 400 + yoff + (h + yoff) * row, w, h);
              }
            }
            d.setColor(Color.decode("#2e2a29"));
            d.fillRectangle(89, 340, 26, 60);
            d.fillRectangle(97, 210, 10, 130);
            d.setColor(Color.ORANGE);
            d.fillCircle(101, 210, 11);
            d.setColor(Color.WHITE);
            d.fillCircle(101, 210, 8);
            d.setColor(Color.RED);
            d.fillCircle(101, 210, 5);
          }

          @Override
          public void timePassed() {
          }
        };
        return background;
      }

      @Override
      public List<Block> blocks() {
        Color[] colors = {
            // Colors for the rows
            Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE };
        List<Block> lst = new ArrayList<Block>();
        int y = 150;
        double width = 46, height = 23;
        for (int row = 0; row < 5; row++) {
          for (int c = 1; c <= 6 + (4 - row); c++) {
            lst.add(new CountingBlock(800 - 15 - (c * width), y + row * height, width, height, colors[row], 1));
          }
        }

        return lst;
      }
    };
  }

  /**
   *
   * @return Information for Level4
   */
  private static LevelInformation getLevel4() {
    return new LevelInformation() {

      @Override
      public int paddleWidth() {
        return 170;
      }

      @Override
      public int paddleSpeed() {
        return 8;
      }

      @Override
      public int numberOfBlocksToRemove() {
        return 150;
      }

      @Override
      public int numberOfBalls() {
        return 3;
      }

      @Override
      public String levelName() {
        return "Final Four";
      }

      @Override
      public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<Velocity>();
        double zero = 90;
        double offset = 25;
        double speed = 7;
        for (int i = -1; i <= 1; i++) {
          lst.add(Velocity.fromAngleAndSpeed(zero + i * (offset), speed));
        }
        return lst;
      }

      @Override
      public Sprite getBackground() {
        Sprite background = new Sprite() {
          @Override
          public void drawOn(DrawSurface d) {
            d.setColor(Color.decode("#1787cf"));
            d.fillRectangle(0, 0, 800, 600);

            d.setColor(Color.decode("#cbcdcc"));
            for (int i = 0; i < 10; i++) {
              int x = 105 + 8 * i;
              d.drawLine(x, 350, x - 40, 601);
            }
            d.fillCircle(75 + 30, 350, 23);
            d.fillCircle(85 + 30, 370, 25);
            d.setColor(Color.decode("#bbbbbb"));
            d.fillCircle(105 + 30, 340, 26);
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(130 + 30, 354, 27);
            d.fillCircle(117 + 30, 368, 22);

            d.setColor(Color.decode("#cbcdcc"));
            for (int i = 0; i < 9; i++) {
              int x = 605 + 10 * i;
              d.drawLine(x, 480, x - 50, 601);
            }
            d.fillCircle(75 + 530, 350 + 130, 23);
            d.fillCircle(85 + 530, 370 + 130, 25);
            d.setColor(Color.decode("#bbbbbb"));
            d.fillCircle(105 + 530, 340 + 130, 26);
            d.setColor(Color.decode("#aaaaaa"));
            d.fillCircle(130 + 530, 354 + 130, 27);
            d.fillCircle(117 + 530, 368 + 130, 22);
          }

          @Override
          public void timePassed() {
          }
        };
        return background;
      }

      @Override
      public List<Block> blocks() {
        Color[] colors = {
            // Colors for the rows
            Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN };
        List<Block> lst = new ArrayList<Block>();
        double width = 51, height = 25.3;
        for (int row = 0; row < 7; row++) {
          for (int c = 0; c < 15; c++) {
            lst.add(new CountingBlock(17 + (c * width), 90 + row * height, width, height, colors[row], 1));
          }
        }

        return lst;
      }
    };
  }

}
