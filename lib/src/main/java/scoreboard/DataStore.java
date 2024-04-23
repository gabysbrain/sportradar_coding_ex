package scoreboard;

import java.util.ArrayList;

public interface DataStore {
  public void createMatch(Match match);

  public void updateMatch(Match match) throws UnknownMatchException;

  public boolean getIsPlaying(String teamName);

  public ArrayList<Match> getMatches();
}
