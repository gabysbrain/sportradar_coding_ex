package scoreboard;

public class InvalidMatchException extends Exception {
  public InvalidMatchException(String home, String away, String msg) {
    super("match between " + home + " and " + away + ". " + msg);
  }

  public InvalidMatchException(String home, String away, Throwable cause) {
    super("match between " + home + " and " + away + ".", cause);
  }
}
