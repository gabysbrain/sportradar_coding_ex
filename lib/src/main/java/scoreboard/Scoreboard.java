package scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class MatchComparator implements Comparator<Match> {
  @Override
  public int compare(Match m1, Match m2) {
    return (m2.getHomeScore() + m2.getAwayScore()) - (m1.getHomeScore() + m1.getAwayScore());
  }
}

public class Scoreboard {
  public Scoreboard(DataStore storage) {
    this.storage = storage;
  }

  public Iterable<Match> getMatches() {
    ArrayList<Match> matches = this.storage.getMatches();
    Collections.sort(matches, new MatchComparator());
    return matches;
  }

  public void startMatch(String homeTeam, String awayTeam) throws InvalidMatchException {
    if (storage.getIsPlaying(homeTeam)) {
      throw new InvalidMatchException(
          homeTeam, awayTeam, "home team is already playing another match");
    }
    if (storage.getIsPlaying(awayTeam)) {
      throw new InvalidMatchException(
          homeTeam, awayTeam, "away team is already playing another match");
    }

    this.storage.createMatch(new Match(homeTeam, awayTeam));
  }

  public void finishMatch(String homeTeam, String awayTeam) throws UnknownMatchException {
    this.storage.removeMatch(homeTeam, awayTeam);
  }

  public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore)
      throws UnknownMatchException, InvalidScoreException {
    Match m = storage.getMatch(homeTeam, awayTeam);
    m.setScores(homeScore, awayScore);
    storage.updateMatch(m);
  }

  private DataStore storage;
}
