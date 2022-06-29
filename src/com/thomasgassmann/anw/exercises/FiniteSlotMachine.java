package com.thomasgassmann.anw.exercises;

public class FiniteSlotMachine {
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
