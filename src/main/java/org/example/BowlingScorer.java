package org.example;


public class BowlingScorer {

    public int calculateScore(String input) {
        var scoreArray = input.split(" ");
        var totalScore = 0;
        for (int i = 0; i < 10; i++) {
            var scorePairArray = scoreArray[i].toCharArray();
            if (scorePairArray.length == 2) {
                if ('/' == scorePairArray[1]) {
                    totalScore += 10;
                    if ((i + 1) < scoreArray.length ) {
                        var futureFrame = scoreArray[i+1].toCharArray();
                        if (Character.isDigit(futureFrame[0])) {
                            totalScore += Character.getNumericValue(futureFrame[0]);
                        }
                    }
                } else {
                    totalScore += addNonSpareFrameToScore(scorePairArray);
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