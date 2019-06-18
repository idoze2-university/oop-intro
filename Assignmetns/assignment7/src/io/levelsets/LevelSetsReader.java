package io.levelsets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LeveelSpecificationReader reads LevelInfo from formatted file.
 */
public class LevelSetsReader {
  /**
   * @param reader the file to read specifications from.
   * @return List of Levels parsed.
   * @throws IOException if reader cannot read.
   */
  public static List<LevelSet> fromReader(java.io.Reader reader) throws IOException {
    List<LevelSet> levels = new ArrayList<LevelSet>();
    BufferedReader br = new BufferedReader(reader);
    String line = "";
    while ((line = br.readLine()) != null) {

      String[] data = line.split(":");
      String name = data[0];
      String desc = data[1];
      String path = br.readLine();
      levels.add(new LevelSet(name, desc, path));
    }
    return levels;
  }

}