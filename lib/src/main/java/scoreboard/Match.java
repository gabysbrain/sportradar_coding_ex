package scoreboard;

import java.time.LocalDateTime;

public class Match implements Cloneable {
  public Match(String homeTeam, String awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = 0;
    this.awayScore = 0;

    this.createTime = LocalDateTime.now();
  }

  public String getHomeTeam() {
    return this.homeTeam;
  }

  public String getAwayTeam() {
    return this.awayTeam;
  }

  public int getHomeScore() {
    return this.homeScore;
  }

  public int getAwayScore() {
    return this.awayScore;
  }

  public LocalDateTime getCreateTime() {
    return this.createTime;
  }

  public void setHomeScore(int score) throws InvalidScoreException {
    if (score < 0) {
      throw new InvalidScoreException("negative home team score");
    }
    this.homeScore = score;
  }

  public void setAwayScore(int score) throws InvalidScoreException {
    if (score < 0) {
      throw new InvalidScoreException("negative away team score");
    }
    this.awayScore = score;
  }

  public void setScores(int homeScore, int awayScore) throws InvalidScoreException {
    this.setHomeScore(homeScore);
    this.setAwayScore(awayScore);
  }

  public boolean equals(Object other) {
    if (!(other instanceof Match)) {
      return false;
    }

    Match m = (Match) other;
    return this.homeTeam == m.homeTeam
        && this.awayTeam == m.awayTeam
        && this.homeScore == m.homeScore
        && this.awayScore == m.awayScore;
  }

  @Override
  public String toString() {
    return "Match: "
        + this.homeTeam
        + " v "
        + this.awayTeam
        + " ("
        + this.homeScore
        + ", "
        + this.awayScore
        + ")";
  }

  @Override
  public Object clone() {
    Match m = new Match(this.homeTeam, this.awayTeam);
    m.homeScore = this.homeScore;
    m.awayScore = this.awayScore;
    m.createTime = this.createTime;

    return m;
  }

  private String homeTeam;
  private String awayTeam;
  private int homeScore;
  private int awayScore;

  private LocalDateTime createTime;
}
