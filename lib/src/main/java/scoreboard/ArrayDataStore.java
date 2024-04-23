package scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayDataStore implements DataStore {
  public ArrayDataStore() {
    this.matches = new HashMap<String, Match>();
    this.playing = new HashSet<String>();
  }

  public boolean getIsPlaying(String teamName) {
    return this.playing.contains(teamName);
  }

  public void createMatch(Match match) {
    // This is just naive storage so don't worry about duplicates. The caller does
    String home = match.getHomeTeam();
    String away = match.getAwayTeam();
    this.matches.put(home, match);
    this.playing.add(home);
    this.playing.add(away);
  }

  public void removeMatch(String homeTeam, String awayTeam) throws UnknownMatchException {
    Match storedMatch = this.matches.get(homeTeam);
    if (storedMatch == null || storedMatch.getAwayTeam() != awayTeam) {
      throw new UnknownMatchException(homeTeam, awayTeam);
    }

    this.matches.remove(homeTeam);
    this.playing.remove(homeTeam);
    this.playing.remove(awayTeam);
  }

  public void updateMatch(Match match) throws UnknownMatchException {
    // Ensure a match with these teams is in progress
    Match storedMatch = this.matches.get(match.getHomeTeam());
    if (storedMatch == null || storedMatch.getAwayTeam() != match.getAwayTeam()) {
      throw new UnknownMatchException(match.getHomeTeam(), match.getAwayTeam());
    }

    this.matches.put(match.getHomeTeam(), match);
  }

  public Match getMatch(String homeTeam, String awayTeam) throws UnknownMatchException {
    Match m = this.matches.get(homeTeam);
    if (m == null || m.getAwayTeam() != awayTeam) {
      throw new UnknownMatchException(homeTeam, awayTeam);
    }
    return m;
  }

  public ArrayList<Match> getMatches() {
    return new ArrayList<Match>(this.matches.values());
  }

  // indexed by home team since there can be only one
  private HashMap<String, Match> matches;
  private HashSet<String> playing; // home and away teams
}
