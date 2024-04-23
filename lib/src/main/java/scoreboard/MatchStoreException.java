package scoreboard;

public class MatchStoreException extends Exception {
  public MatchStoreException(String homeTeam, String awayTeam, String msg) {
    super("Can't store match between " + homeTeam + " and " + awayTeam + ". " + msg);
  }
}
