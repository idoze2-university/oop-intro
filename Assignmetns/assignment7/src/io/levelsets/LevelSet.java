package io.levelsets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import game.levels.LevelInformation;
import io.levelfactory.LevelSpecificationReader;

public class LevelSet {

  private String name;
  private String desc;
  private String pathToFile;

  public LevelSet(String name, String desc, String pathToFile) {
    this.name = name;
    this.desc = desc;
    this.pathToFile = pathToFile;
  }

  public List<LevelInformation> getLevels(){
    try {
      InputStreamReader is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(pathToFile));
      return LevelSpecificationReader.fromReader(is);
    } catch (Exception e) {
      System.out.println(String.format("Couldn't load Level Sets\nBecause: '%s'" ,e));
      System.exit(1);
    }
    return null;

  }

public String getName() {
	return name;
}

public String getDesc() {
	return desc;
}
}