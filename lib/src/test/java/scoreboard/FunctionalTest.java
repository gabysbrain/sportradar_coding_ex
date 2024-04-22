package scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FunctionalTest {
  @Test
  void startNewMatch() {
    // Setup
    Scoreboard scoreboard = new Scoreboard();

    // Execute
    scoreboard.startMatch("home team", "away team");

    // Verify
    // should only be one in-progress match
    ArrayList<Match> expected = new ArrayList<Match>();
    expected.add(new Match("home team", "away team", 0, 0));
    assertIterableEquals(
        expected, scoreboard.getMatches(), "startMatch should create a new 0-0 match");
  }

  @Test
  void updateMatchScore() {
    // Setup
    Scoreboard scoreboard = new Scoreboard();
    scoreboard.startMatch("home team", "away team");

    // Execute
    scoreboard.updateScore("home team", "away team", 12, 2);

    // Verify
    ArrayList<Match> expected = new ArrayList<Match>();
    expected.add(new Match("home team", "away team", 12, 2));
    assertIterableEquals(expected, scoreboard.getMatches(), "updateScore should set the new score");
  }

  @Test
  void finishMatch() {
    // Setup
    Scoreboard scoreboard = new Scoreboard();
    scoreboard.startMatch("home team", "away team");
    scoreboard.updateScore("home team", "away team", 12, 2);

    // Execute
    scoreboard.finishMatch("home team", "away team");

    // Verify
    ArrayList<Match> expected = new ArrayList(); // only 1 match
    assertIterableEquals(expected, scoreboard.getMatches(), "finishMatch should remove the match");
  }

  @Test
  void getMatches() {
    // Setup
    Scoreboard scoreboard = new Scoreboard();
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
    ArrayList<Match> expected = new ArrayList();
    expected.add(new Match("Uruguay", "Italy", 6, 6));
    expected.add(new Match("Spain", "Brazil", 10, 2));
    expected.add(new Match("Mexico", "Canada", 0, 5));
    expected.add(new Match("Argentina", "Australia", 3, 1));
    expected.add(new Match("Germany", "France", 10, 2));
    assertIterableEquals(
        expected,
        scoreboard.getMatches(),
        "getMatches should get all active matches in order of total score");
  }
}
