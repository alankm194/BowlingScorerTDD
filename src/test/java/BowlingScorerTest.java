import org.alan.BowlingScorer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingScorerTest {

    private BowlingScorer scorer;
    @BeforeEach
    public void init() {
        this.scorer = new BowlingScorer();
    }

    @Test
    public void whenBowlerMissesAllShots_ThenReturn0() {
        String testCase = "-- -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(0, actualScore);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/gameWithAllHitsNoStrikesAndSpares.csv", numLinesToSkip = 1)
    public void whenBowlerScoresNoSpareAndNoStrikes_ThenReturnCorrectScore(String input, int expectedScore) {
        assertEquals(expectedScore, scorer.calculateScore(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "gameWithOnlyStrikes.csv", numLinesToSkip = 1)
    public void whenBowlerScoreStrikes_theReturnCorrectScore(String input, int expectedScore) {
        assertEquals(expectedScore, scorer.calculateScore(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "gameMixtureOfStrikesSparesAndNormalFrames.csv", numLinesToSkip = 1)
    public void GivenGameWithVariablesScores_theReturnCorrectScore(String input, int expectedScore) {
        assertEquals(expectedScore, scorer.calculateScore(input));
    }

    /*
    problem analysis
        - we wont be validating input
        - ten pin bowling has 10 frames
        - 2 rolls in a frame
        - a frame that has not knocked all pins is scored by adding all the knockeddown pins
        - a frame with a spare looks ahead to the next roll to calculate the score for that frame
        - a frame with a strike looks ahead with 2 rolls. This can be 2 frames
        - on the tenth frame, a spare gets you one extra roll and a strike gets 2
     */

    /*
    input
        - input is line of string
        - if split line into a list separated by delimited of " ", max elements in list is 12
        - max elements in list of no strike/spare game would be 10
        - if you score a spare in the 10th frame and the last bonus shot isnt a strike, the last element will have 3 characters 5/5
     */


}
