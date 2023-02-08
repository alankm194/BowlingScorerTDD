package org.example;


public class BowlingScorer {

    private final static int MAX_FRAME_COUNT = 10;
    private final static int STRIKE_PAIR_SCORE = 10;
    private final static String STRIKE = "X";

    public int calculateScore(String input) {
        var scoreArray = input.split(" ");
        var totalScore = 0;
        for (int i = 0; i < MAX_FRAME_COUNT; i++) {
            var scoreCharArray = scoreArray[i].toCharArray();
            if (scoreCharArray.length == 1) {
                if (scoreCharArray[0] == STRIKE.charAt(0)) {
                    totalScore += CalculateStrikeScore(scoreArray[i + 1], scoreArray[i + 2]);
                }
            }
            else {
                if ('/' == scoreCharArray[1]) {
                    totalScore += STRIKE_PAIR_SCORE;
                    if ((i + 1) < scoreArray.length ) {
                        var futureFrame = scoreArray[i+1].toCharArray();
                        if (Character.isDigit(futureFrame[0])) {
                            totalScore += Character.getNumericValue(futureFrame[0]);
                        }
                    }
                } else {
                    totalScore += addNonSpareFrameToScore(scoreCharArray);
                }
            }
        }
        return totalScore;
    }

    private int CalculateStrikeScore(String futureFrame1, String futureFrame2) {
        var score = STRIKE_PAIR_SCORE;
        if (futureFrame1.equals(STRIKE)) {
            score += STRIKE_PAIR_SCORE;
            if (futureFrame2.equals(STRIKE)) {
                score += STRIKE_PAIR_SCORE;
            } else {
                if (Character.isDigit(futureFrame2.toCharArray()[0])) {
                    score += Character.getNumericValue(futureFrame2.toCharArray()[0]);
                }
            }
        } else {
            score += addNonSpareFrameToScore(futureFrame1.toCharArray());
        }
        return score;
    }

    private int addNonSpareFrameToScore(char[] scorePairArray) {
        int frameTotal = 0;
        for (char charScore : scorePairArray) {
            if (Character.isDigit(charScore)) {
                frameTotal += Character.getNumericValue(charScore);
            }
        }
        return frameTotal;
    }
}