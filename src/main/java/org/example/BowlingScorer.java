package org.example;


import java.util.Arrays;
import java.util.ListIterator;

public class BowlingScorer {

    private final static int MAX_FRAME_COUNT = 10;
    private final static int STRIKE_PAIR_SCORE = 10;
    private final static String STRIKE = "X";
    private final static char SPARE = '/';

    public int calculateScore(String input) {
        ListIterator<String> scoreIterator = Arrays.asList(input.split(" ")).listIterator();
        var totalScore = 0;

        while (scoreIterator.hasNext() && scoreIterator.nextIndex() < MAX_FRAME_COUNT) {
            var scoreCharArray = scoreIterator.next().toCharArray();
            if (scoreCharArray.length == 1) {
                if (scoreCharArray[0] == STRIKE.charAt(0)) {
                    totalScore += CalculateStrikeScore(scoreIterator);
                }
            } else {
                if (SPARE == scoreCharArray[1]) {
                    totalScore += STRIKE_PAIR_SCORE;
                    if (scoreIterator.hasNext()) {
                        var futureFrameFirstRoll = scoreIterator.next().toCharArray()[0];
                        totalScore += addIndividualRollToScore(futureFrameFirstRoll);
                        scoreIterator.previous();
                    }
                } else {
                    totalScore += addNonSpareFrameToScore(scoreCharArray);
                }
            }
        }
        return totalScore;
    }

    private int CalculateStrikeScore(ListIterator<String> iterator) {
        var score = STRIKE_PAIR_SCORE;
        if (iterator.hasNext()) {
            var futureFrame1 = iterator.next();
            if (futureFrame1.equals(STRIKE)) {
                score += STRIKE_PAIR_SCORE;
                if (iterator.hasNext()) {
                    var futureFrame2 = iterator.next();
                    if (futureFrame2.equals(STRIKE)) {
                        score += STRIKE_PAIR_SCORE;
                    } else {
                        score += addIndividualRollToScore(futureFrame2.toCharArray()[0]);
                    }
                    iterator.previous();
                }
            } else {
                score += addNonSpareFrameToScore(futureFrame1.toCharArray());
            }
            iterator.previous();
        }
        return score;
    }


    private int addNonSpareFrameToScore(char[] scorePairArray) {
        int frameTotal = 0;
        for (char charScore : scorePairArray) {
            frameTotal += addIndividualRollToScore(charScore);
        }
        return frameTotal;
    }

    private int addIndividualRollToScore(char roll) {
        if (Character.isDigit(roll)) {
            return Character.getNumericValue(roll);
        }
        return 0;
    }
}