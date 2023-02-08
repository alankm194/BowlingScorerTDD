import org.example.BowlingScorer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingScorerTest {

    @Test
    public void whenBowlerMissesAllShots_ThenReturn0() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "-- -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(0, actualScore);
    }

    @Test
    public void whenBowlerScores1InFirstFrame_ThenReturn1() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "1- -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(1, actualScore);
    }

    @Test
    public void whenBowlerScores1InFirstRollAnd4InSecondRollInFirstFrame_ThenReturn5() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "14 -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(5, actualScore);
    }

    @Test
    public void whenBowlerScores4InFirstRollAnd1InSecondRollInFirstFrame_ThenReturn5() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "41 -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(5, actualScore);
    }

    @Test
    public void whenBowlerScoresAll1sInAllRolls_ThenReturn20() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "11 11 11 11 11 11 11 11 11 11";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(20, actualScore);
    }

    @Test
    public void whenBowlerScoresTotal60InAllFrames_ThenReturn60() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "12 34 45 53 22 14 63 52 13 22";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(60, actualScore);
    }

    @Test
    public void whenBowlerScorePairInFirstFrameAndMissesEveryShotAfter_ThenReturn10() {
        BowlingScorer scorer = new BowlingScorer();
        String testCase = "1/ -- -- -- -- -- -- -- -- --";
        int actualScore = scorer.calculateScore(testCase);
        assertEquals(10, actualScore);
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
