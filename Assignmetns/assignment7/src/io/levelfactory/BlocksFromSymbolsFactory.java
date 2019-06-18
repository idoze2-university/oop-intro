package io.levelfactory;

import java.util.HashMap;

import biuoop.DrawSurface;
import java.awt.Color;

import game.component.CountingBlock;
import game.component.Sprite;

/**
 * Provides a factory that creates blocks from parsing their data.
 */
public class BlocksFromSymbolsFactory {
  private HashMap<String, HashMap<String, String>> blockToDef;
  private HashMap<String, HashMap<String, String>> spaceToDef;
  private HashMap<String, String> defaultVals;

  /**
   * Default constructor.
   */
  public BlocksFromSymbolsFactory() {
    blockToDef = new HashMap<String, HashMap<String, String>>();
    spaceToDef = new HashMap<String, HashMap<String, String>>();
    defaultVals = new HashMap<String, String>();

  }

  /**
   * Adds the input to the correct HashMap.
   *
   * @param data dataline that describes the block/spacer.
   */
  public void addSymbol(String data) {
    if (data.startsWith("bdef")) {
      String[] fields = data.replace("bdef ", "").split(" ");
      HashMap<String, String> information = new HashMap<String, String>();
      information.putAll(defaultVals);
      String symb = "z";
      for (String field : fields) {
        String[] info = field.split(":");
        if (info[0].equals("symbol")) {
          symb = info[1];
        } else {
          information.put(info[0], info[1]);
        }
      }
      blockToDef.put(symb, information);
    } else if (data.startsWith("default")) {
      String[] fields = data.replace("default ", "").split(" ");
      for (String field : fields) {
        String[] info = field.split(":");
        defaultVals.put(info[0], info[1]);
      }
    } else {
      String[] fields = data.replace("sdef ", "").split(" ");
      HashMap<String, String> information = new HashMap<String, String>();
      String symb = "z";
      for (String field : fields) {
        String[] info = field.split(":");
        if (info[0].equals("symbol")) {
          symb = info[1];
        } else {
          information.put(info[0], info[1]);
        }
      }
      information.putAll(defaultVals);
      spaceToDef.put(symb, information);
    }
  }

  /**
   * @param s Symbol to check.
   * @return true if 's' is a valid space symbol.
   */
  public boolean isSpaceSymbol(String s) {
    return spaceToDef.containsKey(s);
  }

  /**
   * @param s Symbol to check.
   * @return true if 's' is a valid block symbol.
   */
  public boolean isBlockSymbol(String s) {
    return blockToDef.containsKey(s);
  }

  /**
   * @param s    symbol that represents the block.
   * @param xpos X position of the block.
   * @param ypos Y position of the block.
   * @return generated block.
   */
  public CountingBlock getBlock(String s, int xpos, int ypos) {
    HashMap<String, String> data = blockToDef.get(s);
    int hp = Integer.parseInt(data.get("hit_points"));
    int width = Integer.parseInt(data.get("width"));
    int height = Integer.parseInt(data.get("height"));
    Sprite[] bgs = new Sprite[hp];
    for (int i = 0; i < hp; i++) {
      try {
        bgs[i] = SpecificationParser.parseBackGround(data.get("fill-" + (i + 1)), xpos, ypos, width, height);
      } catch (Exception e) {
        bgs[i] = SpecificationParser.parseBackGround(data.get("fill"), xpos, ypos, width, height);
      }
    }
    CountingBlock countingBlock = new CountingBlock(xpos, ypos, width, height, Color.red, hp) {
      @Override
      public void drawOn(DrawSurface d) {
        super.drawOn(d);
        int hp = this.getHitPoints();
        bgs[hp - 1].drawOn(d);
        if (data.containsKey("stroke")) {
          d.setColor(SpecificationParser.parseColor(data.get("stroke")));
          d.drawRectangle(xpos, ypos, width, height);
        }

      }

    };
    return countingBlock;
  }

  /**
   * @param s symbol to be tested.
   * @return the width in pixels associated with the given spacer-symbol
   */
  public int getSpaceWidth(String s) {
    return Integer.valueOf(spaceToDef.get(s).get("width"));
  }

  /**
   * @param s symbol to be tested.
   * @return the width in pixels associated with the given block-symbol.
   */
  public int getBlockWidth(String s) {
    return Integer.valueOf(blockToDef.get(s).get("width"));
  }
}