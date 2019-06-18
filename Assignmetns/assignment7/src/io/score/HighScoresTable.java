package io.score;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The HighScoresTable class implements a collection of ScoreInfo entries.
 */
public class HighScoresTable implements java.io.Serializable {

  private int size;
  private List<ScoreInfo> scores;

  /**
   * Create an empty high-scores table with the specified size.
   *
   * @param size the table holds up to size top scores.
   */
  public HighScoresTable(int size) {
    this.size = size;
    scores = new ArrayList<ScoreInfo>();
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
   * @return the File itself
   * @throws IOException if the file if not found.
   */
  public static HighScoresTable load(String filename) throws IOException {
    ObjectInputStream is = null;
    try {
      is = new ObjectInputStream(new FileInputStream(filename));
      return (HighScoresTable) is.readObject();
    } catch (Exception e) {
      return new HighScoresTable(5);
    } finally {
      if (is != null) {
        is.close();
      }
    }

  }

  /**
   *
   * @param filename Save table data to the specified file.
   * @throws IOException if the file if not found.
   */
  public void save(String filename) throws IOException {
    ObjectOutputStream os = null;
    try {
      os = new ObjectOutputStream(new FileOutputStream(filename));
      os.writeObject(this);
    } finally {
      if (os != null) {
        os.close();
      }
    }
  }
}