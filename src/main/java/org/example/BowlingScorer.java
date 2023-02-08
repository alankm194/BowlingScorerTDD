package org.example;


public class BowlingScorer {

    private final static int MAX_FRAME_COUNT = 10;
    private final static int STRIKE_PAIR_SCORE = 10;



    public int calculateScore(String input) {
        var scoreArray = input.split(" ");
        var totalScore = 0;
        for (int i = 0; i < MAX_FRAME_COUNT; i++) {
            var scoreCharArray = scoreArray[i].toCharArray();
            if (scoreCharArray.length == 1) {
                if (scoreCharArray[0] == 'X') {
                    totalScore += STRIKE_PAIR_SCORE;
                    if (scoreArray[i+1].equals("X")) {
                        totalScore += STRIKE_PAIR_SCORE;
                        if (scoreArray[i+2].equals("X")) {
                            totalScore += STRIKE_PAIR_SCORE;
                        } else {
                            if (Character.isDigit(scoreArray[i + 2].toCharArray()[0])) {
                                totalScore += Character.getNumericValue(scoreArray[i + 2].toCharArray()[0]);
                            }
                        }
                    } else {
                        totalScore += addNonSpareFrameToScore(scoreArray[i + 1].toCharArray());
                    }
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