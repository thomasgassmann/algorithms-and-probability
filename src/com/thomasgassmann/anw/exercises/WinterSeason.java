package com.thomasgassmann.anw.exercises;

public class WinterSeason {
    private static double compute(int n, int k, double[] p) {
        double[][] dp = new double[2][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                int dpRow = i % 2;
                int lastDpRow = (i - 1) % 2;
                dp[dpRow][j] = dp[lastDpRow][j] * (1 - p[i - 1]);
                if (j > 0) {
                    dp[dpRow][j] += dp[lastDpRow][j - 1] * p[i - 1];
                }
            }
        }

        double result = 0;
        for (int i = k; i <= n; i++) {
            result += dp[n % 2][i];
        }

        return result;
    }
}
