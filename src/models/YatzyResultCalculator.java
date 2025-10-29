package models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    private int[] yatzyResult = new int[5];

    /**
     *
     * @param dice
     */
    public YatzyResultCalculator(Die[] dice) {
        for (int i = 0; i < dice.length; i++) {
            yatzyResult[i] = dice[i].getEyes();
        }
        Arrays.sort(yatzyResult);
    }

    /**
     * Calculates the score for Yatzy uppersection
     *
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        int sum = 0;
        for (int value : yatzyResult) {
            if (value == eyes) {
                sum += eyes;
            }
        }
        return sum;
    }

    public int onePairScore() {
        for (int i = yatzyResult.length - 1; i > 0; i--) {
            if (yatzyResult[i] == yatzyResult[i - 1]) {
                return yatzyResult[i] * 2;
            }
        }
        return 0;


    }

    public int twoPairScore() {
        int pairSum = 0;
        int pairFound = 0;

        for (int i = yatzyResult.length - 1; i > 0; i--) {
            if (yatzyResult[i] == yatzyResult[i - 1]) {
                pairSum += yatzyResult[i] * 2;
                pairFound++;
                i--;
            }
            if (pairFound == 2) {
                return pairSum;
            }

        }
        return 0;
    }


    public int threeOfAKindScore() {
        for (int i = yatzyResult.length - 1; i >= 2; i--) {
            if (yatzyResult[i] == yatzyResult[i - 1] && yatzyResult[i] == yatzyResult[i - 2]) {
                return yatzyResult[i] * 3;
            }
        }
        return 0;
    }


    public int fourOfAKindScore() {
        for (int i = yatzyResult.length - 1; i >= 3; i--) {
            if (yatzyResult[i] == yatzyResult[i - 1] && yatzyResult[i] == yatzyResult[i - 2] && yatzyResult[i] == yatzyResult[i - 3]) {
                return yatzyResult[i] * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        int[] expected = {1, 2, 3, 4, 5};
        if (Arrays.equals(yatzyResult, expected)) {
            return 15;
        }
        return 0;
    }

    public int largeStraightScore() {
        int[] expected = {2, 3, 4, 5, 6};
        if (Arrays.equals(yatzyResult, expected)) {
            return 20;
        }
        return 0;
    }

    public int fullHouseScore() {
        int two = 0;
        int three = 0;

        int[] count = new int[7];
        for (int value : yatzyResult) {
            count[value]++;
        }
        for (int i = 0; i <= 6; i++) {
            if (count[i] == 3)
                three += i;
            else if (count[i] == 2)
                two += i;
        }
        if (three > 0 && two > 0)
            return three * 3 + two * 2;
        else
            return 0;
    }

    public int chanceScore() {
        int sum = 0;
        for (int slag : yatzyResult) {
            sum += slag;
        }
        return sum;
    }

    public int yatzyScore() {
        int forsteSlag = yatzyResult[0];
        for (int i = 0; i < yatzyResult.length; i++) {
            if (yatzyResult[i] != forsteSlag) {
                return 0;
            }
        }
            return 50;
    }
}
