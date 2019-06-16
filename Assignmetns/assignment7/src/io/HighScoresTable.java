package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The HighScoresTable class implements a collection of ScoreInfo entries.
 */
public class HighScoresTable {

  private int size;
  private List<ScoreInfo> scores;

  /**
   * Create an empty high-scores table with the specified size.
   *
   * @param size the table holds up to size top scores.
   */
  public HighScoresTable(int size) {
    this.size = size;
  }

  /**
   * Add a high-score.
   *
   * @param score the score to add.
   */
  public void add(ScoreInfo score) {
    scores.add(getRank(score.getScore()) - 1, score);
  }

  /**
   * @return table size.
   */
  public int size() {
    return size;
  }

  /**
   * The list is sorted such that the highest scores come first. BufferedReader
   * reader = new BufferedReader(new FileReader(f));
   *
   * @return the current high scores.
   */
  public List<ScoreInfo> getHighScores() {
    if (scores.size() > size()) {
      return scores.subList(0, size);
    }
    return scores;
  }

  /**
   * Rank 1 means the score will be highest on the list. Rank `size` means the
   * score will be lowest. Rank > `size` means the score is too low and will not
   * be added to the list.
   *
   * @param score the score to add.
   * @return the rank of the current score: where will it be on the list if added?
   */
  public int getRank(int score) {
    int i = 1;
    for (ScoreInfo s : getHighScores()) {
      if (score > s.getScore()) {
        break;
      }
      i++;
    }
    return i;
  }

  /**
   * Clears the table.
   */
  public void clear() {
  }

  /**
   * Load table data from file. Current table data is cleared.
   *
   * @param filename file to load.
   * @throws IOException if the file if not found.
   */
  public void load(File filename) throws IOException {
    scores = new ArrayList<ScoreInfo>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = reader.readLine()) != null) {
      String[] data = line.split(":");
      ScoreInfo info = new ScoreInfo(data[0], Integer.parseInt(data[1]));
      add(info);
    }
    reader.close();

  }

  /**
   *
   * @param filename Save table data to the specified file.
   * @throws IOException if the file if not found.
   */
  public void save(File filename) throws IOException {
    if (size <= 0) {
      return;
    }
    FileWriter writer = new FileWriter(filename);
    for (ScoreInfo score : getHighScores()) {
      String formattedInfo = String.format("%s:%d", score.getName(), score.getScore());
      writer.write(formattedInfo + "\n");
    }
    writer.close();
  }

  /**
   * Read a table from file and return it. If the file does not exist, or there is
   * a problem with reading it, an empty table is returned.
   *
   * @param filename file to read the table from.
   * @return the table read.
   */
  public static HighScoresTable loadFromFile(File filename) {
    HighScoresTable table = new HighScoresTable(0);
    try {
      table.load(filename);
      table.size = table.getHighScores().size();
      return table;
    } catch (Exception e) {
      System.out.println("Failed To Load \''" + filename + "\' \nError: \'" + e + "\'");
      return table;
    }
  }

  @Override
  public String toString() {
    String out = "";
    for (int i = 0; i < size(); i++) {
      try {
        ScoreInfo entry = scores.get(i);
        out += entry;

      } catch (Exception e) {
        out += "-";
      }
      out += "\n\n";
    }
    return out;
  }
}