package scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ArrayDataStoreTest {
  @Test
  void createMatch() throws MatchStoreException {
    // Setup
    ArrayDataStore store = new ArrayDataStore();

    // Execute
    store.createMatch(new Match("home", "away"));

    // Verify
    Match expected = new Match("home", "away");
    assertEquals(1, store.getMatches().size());
    assertEquals(expected, store.getMatches().get(0));
  }

  @Test
  void updateMatch() throws InvalidScoreException, MatchStoreException, UnknownMatchException {
    // Setup
    ArrayDataStore store = new ArrayDataStore();
    Match match = new Match("home", "away");
    store.createMatch(match);

    // Execute
    match.setScores(10, 2);
    store.updateMatch(match);

    // Verify
    ArrayList<Match> expected = new ArrayList<Match>();
    expected.add(match);
    assertIterableEquals(expected, store.getMatches(), "matches should be equal");
  }

  @Test
  void updateMatchDoesntUpdateStore() throws InvalidScoreException, MatchStoreException {
    // Setup
    ArrayDataStore store = new ArrayDataStore();
    Match match = new Match("home", "away");
    store.createMatch(match);

    // Execute
    match.setScores(10, 2);
    // deliberately don't update the store

    // Verify
    ArrayList<Match> expected = new ArrayList<Match>();
    expected.add(new Match("home", "away"));
    assertIterableEquals(expected, store.getMatches(), "stored match should not be updated");
  }

  @Test
  void updateUnknownMatch() throws InvalidScoreException, MatchStoreException {
    // Setup
    ArrayDataStore store = new ArrayDataStore();
    Match match1 = new Match("home", "away");
    Match match2 = new Match("home2", "away2");
    store.createMatch(match1);

    // Execute
    match2.setScores(10, 2);

    UnknownMatchException thrown =
        assertThrows(
            UnknownMatchException.class,
            () -> store.updateMatch(match2),
            "can't update a match that's not stored");
  }
}
