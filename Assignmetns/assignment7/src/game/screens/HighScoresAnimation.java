package game.screens;

import java.util.List;

import biuoop.DrawSurface;
import game.animation.Animation;
import io.score.HighScoresTable;
import io.score.ScoreInfo;

public class HighScoresAnimation implements Animation {

  HighScoresTable scores;

  /**
   * Default constructor.
   *
   * @param scores the table to be printed.
   */
  public HighScoresAnimation(HighScoresTable scores) {
    this.scores = scores;
  }

  @Override
  public void doOneFrame(DrawSurface d) {
    List<ScoreInfo> scoreinfos = scores.getHighScores();
    for (int i = 0; i < scoreinfos.size(); i++) {
      d.drawText(30,40*(i + 3),scoreinfos.get(i).toString(),20);
    }
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}