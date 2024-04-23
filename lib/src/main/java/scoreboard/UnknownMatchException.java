package scoreboard;

public class UnknownMatchException extends Exception {
  public UnknownMatchException(String homeTeam, String awayTeam) {
    super("Can't find match between " + homeTeam + " and " + awayTeam);
  }
}
