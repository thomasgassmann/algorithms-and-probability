package com.thomasgassmann.anw.exercises;

class Result {
    double expectedValue;
    double variance;
}

public class RandomTriangles {
    private static Result numberOfTriangles(long n, double p) {
        Result res = new Result();
        res.expectedValue = expectedValue(n, p);
        res.variance = variance(n, p);
        return res;
    }

    private static double variance(long n, double p) {
        double ex = expectedValue(n, p);
        return ex
                + 12 * ncr(n, 4) * Math.pow(p, 5)
                + 30 * ncr(n, 5) * Math.pow(p, 6)
                + 20 * ncr(n, 6) * Math.pow(p, 6)
                - Math.pow(ex, 2);
    }

    private static double expectedValue(long n, double p) {
        return ncr(n, 3) * Math.pow(p, 3);
    }

    private static long ncr(long n, long k) {
        long numerator = 1;
        for (long i = n; i > n - k; i--) {
            numerator *= i;
        }

        return numerator / fact(k);
    }

    private static long fact(long k) {
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res *= i;
        }

        return res;
    }
}
