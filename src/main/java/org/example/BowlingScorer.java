package org.example;


public class BowlingScorer {

    public int calculateScore(String input) {
        var scoreArray = input.split(" ");
        var totalScore = 0;
        for (String scorePair: scoreArray) {
            for (char individualScore: scorePair.toCharArray()) {
                if (Character.isDigit(individualScore)) {
                    totalScore += Character.getNumericValue(individualScore);
                }
            }
        }
        return totalScore;
    }
}

