package scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ScoreboardTest {
  @Test
  void startMatch() throws InvalidMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());

    // Execute
    sb.startMatch("home", "away");

    // Verify
    ArrayList expected = new ArrayList<Match>();
    expected.add(new Match("home", "away"));
    assertIterableEquals(expected, sb.getMatches());
  }

  @Test
  void createDuplicateMatchHome() throws InvalidMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());

    // Execute
    sb.startMatch("home", "away");

    // Verify
    InvalidMatchException thrown =
        assertThrows(
            InvalidMatchException.class,
            () -> sb.startMatch("home", "away2"),
            "same home team can't play 2 matches at the same time");
  }

  @Test
  void createDuplicateMatchAway() throws InvalidMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());

    // Execute
    sb.startMatch("home", "away");

    // Verify
    InvalidMatchException thrown =
        assertThrows(
            InvalidMatchException.class,
            () -> sb.startMatch("home2", "away"),
            "same away team can't play 2 matches at the same time");
  }

  @Test
  void finishMatch() throws InvalidMatchException, UnknownMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());
    sb.startMatch("home", "away");

    // Execute
    sb.finishMatch("home", "away");

    // Verify
    ArrayList expected = new ArrayList<Match>();
    assertIterableEquals(expected, sb.getMatches());
  }

  @Test
  void finishUnknownMatch() throws InvalidMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());
    sb.startMatch("home", "away");

    // Verify
    UnknownMatchException thrown =
        assertThrows(
            UnknownMatchException.class,
            () -> sb.finishMatch("home2", "away2"),
            "can't end unknown match");
  }

  @Test
  void updateScore() throws InvalidMatchException, InvalidScoreException, UnknownMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());
    sb.startMatch("home", "away");

    // Execute
    sb.updateScore("home", "away", 12, 2);

    // Verify
    ArrayList expected = new ArrayList<Match>();
    Match m = new Match("home", "away");
    m.setScores(12, 2);
    expected.add(m);
    assertIterableEquals(expected, sb.getMatches());
  }

  @Test
  void updateInvalidScore() throws InvalidMatchException {
    // Setup
    Scoreboard sb = new Scoreboard(new ArrayDataStore());
    sb.startMatch("home", "away");

    // Execute and verify
    InvalidScoreException thrown =
        assertThrows(
            InvalidScoreException.class,
            () -> sb.updateScore("home", "away", -1, -1),
            "can't set negative scores");
  }

  @Test
  void getMatchesSorted()
      throws InvalidMatchException, InvalidScoreException, UnknownMatchException {
    // Setup
    Object[][] finalScores = {
      {"Germany", "France", 12, 2},
      {"Spain", "Brazil", 11, 2},
      {"Uruguay", "Italy", 6, 6},
      {"Mexico", "Canada", 0, 5},
      {"Argentina", "Australia", 3, 1}
    };

    Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());

    ArrayList expected = new ArrayList<Match>();
    for (Object[] md : finalScores) {
      Match m = new Match((String) md[0], (String) md[1]);
      m.setScores((int) md[2], (int) md[3]);
      scoreboard.startMatch(m.getHomeTeam(), m.getAwayTeam());
      scoreboard.updateScore(m.getHomeTeam(), m.getAwayTeam(), m.getHomeScore(), m.getAwayScore());
      expected.add(m);
    }

    // Execute
    Iterable<Match> matches = scoreboard.getMatches();

    // Verify
    assertIterableEquals(
        expected,
        scoreboard.getMatches(),
        "getMatches should get all active matches in order of total score");
  }
}
