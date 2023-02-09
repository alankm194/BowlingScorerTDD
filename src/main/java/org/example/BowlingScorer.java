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
                if (STRIKE.charAt(0) == scoreCharArray[0]) {
                    totalScore += CalculateStrikeScore(scoreIterator);
                }
            } else {
                if (SPARE == scoreCharArray[1]) {
                    totalScore += calculateSpareScore(scoreIterator, scoreCharArray);
                } else {
                    totalScore += calculateNonStrikeNonSpareScore(scoreCharArray);
                }
            }
        }
        return totalScore;
    }

    private int calculateSpareScore(ListIterator<String> iterator, char[] currentFrameCharArr) {
        var score = STRIKE_PAIR_SCORE;
        if (iterator.hasNext()) {
            var futureFrameFirstRoll = iterator.next().toCharArray()[0];
            score += addIndividualRollToScore(futureFrameFirstRoll);
            iterator.previous();
        }
        if(currentFrameCharArr.length == 3) {
            score += addIndividualRollToScore(currentFrameCharArr[2]);
        }
        return score;
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
                score += calculateFutureFrameScore(futureFrame1.toCharArray());
            }
            iterator.previous();
        }
        return score;
    }

    private int calculateFutureFrameScore(char[] scorePairArray) {
        if (scorePairArray[1] == SPARE) {
            return STRIKE_PAIR_SCORE;
        }
        return calculateNonStrikeNonSpareScore(scorePairArray);
    }


    private int calculateNonStrikeNonSpareScore(char[] scorePairArray) {
        int frameTotal = 0;
        for (char charScore : scorePairArray) {
            frameTotal += addIndividualRollToScore(charScore);
        }
        return frameTotal;
    }

    private int addIndividualRollToScore(char roll) {
        if(roll == STRIKE.charAt(0)) {
            return STRIKE_PAIR_SCORE;
        }
        if (Character.isDigit(roll)) {
            return Character.getNumericValue(roll);
        }
        return 0;
    }
}