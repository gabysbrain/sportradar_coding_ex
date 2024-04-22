package scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchTest {
  @Test
  void getHomeTeam() {
    Match match = new Match("at home", "travelled here");

    assertEquals("at home", match.getHomeTeam(), "home team should return the correct home team");
  }

  @Test
  void getAwayTeam() {
    Match match = new Match("at home", "travelled here");

    assertEquals(
        "travelled here", match.getAwayTeam(), "away team should return the correct away team");
  }

  @Test
  void initialHomeScore() {
    Match match = new Match("at home", "travelled here");

    assertEquals(0, match.getHomeScore(), "initial home score should be 0");
  }

  @Test
  void initialAwayScore() {
    Match match = new Match("at home", "travelled here");

    assertEquals(0, match.getAwayScore(), "initial away score should be 0");
  }

  @Test
  void setHomeScore() throws InvalidScoreException {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    match.setHomeScore(17);

    // Verify
    assertEquals(17, match.getHomeScore(), "home score should set properly");
  }

  @Test
  void setAwayScore() throws InvalidScoreException {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    match.setAwayScore(21);

    // Verify
    assertEquals(21, match.getAwayScore(), "away score should set properly");
  }

  @Test
  void setHomeScoreZero() throws InvalidScoreException {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    match.setHomeScore(0);

    // Verify
    assertEquals(0, match.getAwayScore(), "home score should set properly");
  }

  @Test
  void setAwayScoreZero() throws InvalidScoreException {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    match.setAwayScore(0);

    // Verify
    assertEquals(0, match.getAwayScore(), "away score should set properly");
  }

  @Test
  void setHomeScoreNegative() {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    InvalidScoreException thrown =
        assertThrows(
            InvalidScoreException.class,
            () -> match.setHomeScore(-1),
            "negative score should throw InvalidScoreException");

    // Verify
    assertTrue(
        thrown.getMessage().contains("negative home team score"),
        "exception message does not contain 'negative home team score'");
  }

  @Test
  void setAwayScoreNegative() {
    // Setup
    Match match = new Match("at home", "travelled here");

    // Execute
    InvalidScoreException thrown =
        assertThrows(
            InvalidScoreException.class,
            () -> match.setAwayScore(-1),
            "negative score should throw InvalidScoreException");

    // Verify
    assertTrue(
        thrown.getMessage().contains("negative away team score"),
        "exception message does not contain 'negative away team score'");
  }
}
