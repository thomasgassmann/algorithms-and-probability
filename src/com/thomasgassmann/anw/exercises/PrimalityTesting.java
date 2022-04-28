package com.thomasgassmann.anw.exercises;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class PrimalityTesting {

    public static boolean prime(long n) {
        if (n % 2 == 0 || n <= 2) {
            return n == 2;
        }

        BigInteger NMINUSONE = BigInteger.valueOf(n - 1);
        BigInteger TWO = BigInteger.valueOf(2);
        long a = ThreadLocalRandom.current().nextLong(2, n);
        long d = n - 1;
        int k = 0;
        while (d % 2 == 0) {
            k++;
            d /= 2;
        }

        BigInteger x = BigInteger.valueOf(a).modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        if (x.equals(BigInteger.ONE) || x.equals(NMINUSONE)) {
            return true;
        }

        for (int i = 1; i < k; i++) {
            x = x.modPow(TWO, BigInteger.valueOf(n));
            if (x.equals(BigInteger.ONE)) {
                return false;
            }

            if (x.equals(NMINUSONE)) {
                return true;
            }
        }

        return false;
    }

}
