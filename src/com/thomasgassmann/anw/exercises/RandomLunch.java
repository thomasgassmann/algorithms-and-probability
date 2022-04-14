package com.thomasgassmann.anw.exercises;

public class RandomLunch {
    public static void testCase() {
        int q = 0;
        int[] d = new int[0];
        double r = 0;
        switch (q) {
            case 1:
                r = differentDishes(d);
                break;
            case 2:
                r = dishOfType1(d);
                break;
            case 3:
                r = friendGot2GivenIGot1(d);
                break;
        }
    }

    private static double differentDishes(int[] d) {
        int s = totalDeliciousness(d);
        double pr = d[0] / (double)s;
        return 1 - pr * pr;
    }

    private static double dishOfType1(int[] d) {
        int c = totalDeliciousness(d);
        double f = 0; // total probability when friend does not choose dish of type 1
        // ignore disk of type 1, this is handled separately
        for (int i = 1; i < d.length; i++) {
            f += (d[0] * d[i]) / (double)(c * (c - d[i]));
        }

        return Math.pow(d[0] / (double)c, 2) + f;
    }

    private static double friendGot2GivenIGot1(int[] d) {
        int c = totalDeliciousness(d);
        double e1 = dishOfType1(d);
        double n = (d[0] * d[1]) / (double)(c * (c - d[1]));
        return n / e1;
    }

    private static int totalDeliciousness(int[] d) {
        int s = 0;
        for (int i = 0; i < d.length; i++)
            s += d[i];
        return s;
    }
}
