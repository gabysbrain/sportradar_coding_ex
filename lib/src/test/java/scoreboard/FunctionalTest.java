package scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FunctionalTest {
  @Test
  void startNewMatch() throws InvalidMatchException {
    // Setup
    Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());

    // Execute
    scoreboard.startMatch("home team", "away team");

    // Verify
    // should only be one in-progress match
    ArrayList<Match> expected = new ArrayList<Match>();
    expected.add(new Match("home team", "away team"));
    assertIterableEquals(
        expected, scoreboard.getMatches(), "startMatch should create a new 0-0 match");
  }

  @Test
  void updateMatchScore()
      throws InvalidMatchException, InvalidScoreException, UnknownMatchException {
    // Setup
    Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());
    scoreboard.startMatch("home team", "away team");

    // Execute
    scoreboard.updateScore("home team", "away team", 12, 2);

    // Verify
    ArrayList<Match> expected = new ArrayList<Match>();
    Match m = new Match("home team", "away team");
    m.setScores(12, 2);
    expected.add(m);
    assertIterableEquals(expected, scoreboard.getMatches(), "updateScore should set the new score");
  }

  @Test
  void finishMatch() throws InvalidMatchException, InvalidScoreException, UnknownMatchException {
    // Setup
    Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());
    scoreboard.startMatch("home team", "away team");
    scoreboard.updateScore("home team", "away team", 12, 2);

    // Execute
    scoreboard.finishMatch("home team", "away team");

    // Verify
    ArrayList<Match> expected = new ArrayList(); // only 1 match
    assertIterableEquals(expected, scoreboard.getMatches(), "finishMatch should remove the match");
  }

  @Test
  void getMatches() throws InvalidMatchException, InvalidScoreException, UnknownMatchException {
    // Setup
    Scoreboard scoreboard = new Scoreboard(new ArrayDataStore());
    Object[][] finalScores = {
      // This is ordered by total score and then starting time
      {"Uruguay", "Italy", 6, 6},
      {"Spain", "Brazil", 10, 2},
      {"Mexico", "Canada", 0, 5},
      {"Argentina", "Australia", 3, 1},
      {"Germany", "France", 2, 2}
    };
    ArrayList expected = new ArrayList<Match>();
    for (Object[] md : finalScores) {
      Match m = new Match((String) md[0], (String) md[1]);
      m.setScores((int) md[2], (int) md[3]);
      expected.add(m);
    }

    // Execute
    scoreboard.startMatch("Mexico", "Canada");
    scoreboard.updateScore("Mexico", "Canada", 0, 5);
    scoreboard.startMatch("Spain", "Brazil");
    scoreboard.updateScore("Spain", "Brazil", 10, 2);
    scoreboard.startMatch("Germany", "France");
    scoreboard.updateScore("Germany", "France", 2, 2);
    scoreboard.startMatch("Uruguay", "Italy");
    scoreboard.updateScore("Uruguay", "Italy", 6, 6);
    scoreboard.startMatch("Argentina", "Australia");
    scoreboard.updateScore("Argentina", "Australia", 3, 1);

    // Verify
    assertIterableEquals(
        expected,
        scoreboard.getMatches(),
        "getMatches should get all active matches in order of total score");
  }
}
