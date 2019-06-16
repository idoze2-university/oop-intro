package io;

/**
 * The ScoreInfo class represents a single ScoreToName entry in the
 * HighScoresTable.
 */
public class ScoreInfo {
  private String name;
  private int score;

  /**
   * Default Constructor.
   *
   * @param name  Name of the player.
   * @param score Score to be recorded.
   */
  public ScoreInfo(String name, int score) {
    this.name = name;
    this.score = score;
  }

  /**
   * @return The name recorded with the score.
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return The score recorded for this entry.
   */
  public int getScore() {
    return this.score;
  }

  @Override
  public String toString() {
    return getName() + " --- " + getScore();
  }
}