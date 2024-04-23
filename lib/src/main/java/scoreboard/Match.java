package scoreboard;

public class Match {
  public Match(String homeTeam, String awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = 0;
    this.awayScore = 0;
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

  private String homeTeam;
  private String awayTeam;
  private int homeScore;
  private int awayScore;
}
