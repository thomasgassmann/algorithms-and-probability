package com.thomasgassmann.anw.exercises;

public class FiniteSlotMachine {

    private static double alt(int m, int[] cards) {
        int n = cards.length;
        // dp[i][j]: probability of choosing card j as i-th card
        double[][] dp = new double[n][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = cards[j] / m;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = j - 1; k >= 0; k--) {
                    dp[i][j] += dp[i - 1][k] * cards[j] / (m - i);
                }
            }
        }

        double res = 0;
        for (int j = 0; j < n; j++) {
            if (cards[j] >= 2) {
                for (int i = 0; i < Math.min(m - 1, n); i++) {
                    res += dp[i][j] * (cards[j] - 1) / (m - i - 1);
                }
            }
        }

        return res;
    }

    private static double[][] probseqlen(int[] cards, int m) {
        // probs[i][k]: prob of getting a strictly increasing
        // sequence of length k using all cards j < i
        double[][] probs = new double[cards.length][cards.length + 1];
        for (int i = 0; i < cards.length; i++) {
            probs[i][0] = 1;
        }

        for (int i = 0; i < cards.length; i++) {
            for (int k = 1; k <= i; k++) {
                double available = (double)(m - (k - 1));
                for (int j = 0; j < i; j++) {
                    double c = cards[j] / available;
                    probs[i][k] += probs[j][k - 1] * c;
                }
            }
        }

        return probs;
    }

    private static double win(int[] cards, int m) {
        double total = 0.0;
        double[][] probs = probseqlen(cards, m);
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] >= 2) {
                // restrict to m - 2 because we always need 2 cards to be left to choose
                for (int k = 0; k <= Math.min(i, m - 2); k++) {
                    var cardsLeft = m - k;
                    total += probs[i][k]
                            * (cards[i] / (double)cardsLeft)
                            * ((cards[i] - 1) / (double)(cardsLeft - 1));
                }
            }
        }

        return total;
    }
}
