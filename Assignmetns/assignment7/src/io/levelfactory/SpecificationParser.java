package io.levelfactory;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import game.component.Sprite;
import geometry.Velocity;

/**
 * Tools for parsing formatted Strings for Ass7.
 */
public class SpecificationParser {

  /**
   * Gets data from "foo:data" format.
   *
   * @param data line of data.
   * @return parsed String data.
   */
  public static String getDataValue(String data) {
    return data.substring(data.indexOf(":") + 1).trim();
  }

  /**
   * Splits Data from string specifier to List of velocities.
   *
   * @param data line of data.
   * @return List of velocities.
   */
  public static List<Velocity> parseVelocities(String data) {
    String[] entries = data.substring(data.indexOf(":") + 1).split(" ");
    List<Velocity> ret = new ArrayList<Velocity>();
    for (String entry : entries) {
      String[] velocityData = entry.split(",");
      int speed = Integer.parseInt(velocityData[1]);
      while(speed>100){
        speed/=10;
      }
      Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(velocityData[0]) + 90,speed );
      ret.add(v);
    }
    return ret;
  }

  /**
   * Parses background sprite from data line and arguments.
   *
   * @param data line of data.
   * @return parsed Sprite from format.
   */
  public static Sprite parseBackGround(String data) {
    return parseBackGround(data, 0, 0, 800, 600);
  }

  /**
   * Parses background sprite from data line and arguments.
   *
   * @param data   line of data.
   * @param x      xposition.
   * @param y      yposition.
   * @param width  width argument.
   * @param height height argument.
   * @return parsed Sprite from format.
   */
  public static Sprite parseBackGround(String data, int x, int y, int width, int height) {
    data = data.substring(data.indexOf(":") + 1);
    if (data.startsWith("color")) {
      data = data.substring(data.indexOf("(") + 1, data.lastIndexOf(")"));
      final Color c = parseColor(data);
      return new Sprite() {
        @Override
        public void timePassed() {
          // nothing.
        }

        @Override
        public void drawOn(DrawSurface d) {
          d.setColor(c);
          d.fillRectangle(x, y, width, height);
        }
      };
    } else {
      data = data.substring(data.indexOf("(") + 1, data.lastIndexOf(")"));
      final String imagepath = data;
      return new Sprite() {

        @Override
        public void timePassed() {
          // Do Nothing.
        }

        @Override
        public void drawOn(DrawSurface d) {
          Image img = null;
          try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(imagepath));
            d.drawImage(x, y, img);
          } catch (IOException e) {
            d.setColor(Color.black);
            d.fillRectangle(x, y, width, height);
          }
        }

      };
    }
  }

  /**
   * Parses color from color(args) format.
   *
   * @param data line of data.
   * @return parsed Color.
   */
  public static Color parseColor(String data) {
    Color c = null;
    if (data.startsWith("RGB")) {
      data = data.substring(data.indexOf("(") + 1, data.lastIndexOf(")"));
      String[] rgb = data.split(",");
      float[] hsb = Color.RGBtoHSB(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]), null);
      c = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    } else {
      if (data.indexOf("(") != -1) {
        data = data.substring(data.indexOf("(") + 1, data.lastIndexOf(")"));
      }
      switch (data) {
      case "black":
        c = Color.black;
        break;
      case "blue":
        c = Color.blue;
        break;
      case "cyan":
        c = Color.cyan;
        break;
      case "gray":
        c = Color.gray;
        break;
      case "lightGray":
        c = Color.lightGray;
        break;
      case "green":
        c = Color.green;
        break;
      case "orange":
        c = Color.orange;
        break;
      case "pink":
        c = Color.pink;
        break;
      case "red":
        c = Color.red;
        break;
      case "white":
        c = Color.white;
        break;
      case "yellow":
        c = Color.yellow;
        break;
      default:
        c = Color.black;
        break;
      }
    }
    return c;
  }
}