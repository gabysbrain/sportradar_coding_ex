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

  private String homeTeam;
  private String awayTeam;
  private int homeScore;
  private int awayScore;
}
