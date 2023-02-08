package org.example;


public class BowlingScorer {

    public int calculateScore(String input) {
        var scoreArray = input.split(" ");
        var totalScore = 0;
        for (String scorePair: scoreArray) {
            var pairCharArray = scorePair.toCharArray();
            if (pairCharArray.length == 2 ) {
                if ('/' == pairCharArray[1]) {
                    totalScore += 10;
                } else {
                    for (char charScore: pairCharArray) {
                        if (Character.isDigit(charScore)) {
                            totalScore += Character.getNumericValue(charScore);
                        }
                    }
                }
            }
        }
        return totalScore;
    }
}

