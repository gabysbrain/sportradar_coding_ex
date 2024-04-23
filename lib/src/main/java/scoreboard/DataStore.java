package scoreboard;

import java.util.ArrayList;

public interface DataStore {
  public void createMatch(Match match);

  public void updateMatch(Match match) throws UnknownMatchException;

  public void removeMatch(String homeTeam, String awayTeam) throws UnknownMatchException;

  public boolean getIsPlaying(String teamName);

  public ArrayList<Match> getMatches();

  public Match getMatch(String homeTeam, String awayTeam) throws UnknownMatchException;
}
