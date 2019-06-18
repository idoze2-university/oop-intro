package io.levelsets;

import java.io.InputStreamReader;
import java.util.List;

import game.levels.LevelInformation;
import io.levelfactory.LevelSpecificationReader;

/**
 * The LevelSet provides informaiton for pre-set collection of levels.
 */
public class LevelSet {

  private String name;
  private String desc;
  private String pathToFile;

  /**
   *
   * @param name       Name of the set.
   * @param desc       Desc of the set.
   * @param pathToFile Path to set description file.
   */
  public LevelSet(String name, String desc, String pathToFile) {
    this.name = name;
    this.desc = desc;
    this.pathToFile = pathToFile;
  }

  /**
   * @return List of levels parsed from the set file.
   */
  public List<LevelInformation> getLevels() {
    try {
      InputStreamReader is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(pathToFile));
      return LevelSpecificationReader.fromReader(is);
    } catch (Exception e) {
      System.out.println(String.format("Couldn't load Level Sets\nBecause: '%s'", e));
      System.exit(1);
    }
    return null;

  }

  /**
   * @return the name of the set.
   */
  public String getName() {
    return name;
  }

  /**
   * @return the Description of the set.
   */
  public String getDesc() {
    return desc;
  }
}