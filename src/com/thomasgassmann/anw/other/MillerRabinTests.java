package com.thomasgassmann.anw.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class MillerRabinTests {
    @Test
    public void checkMillerRabin() {
        Assertions.assertEquals(false, MillerRabin.prime(1));
        Assertions.assertEquals(true, MillerRabin.prime(2));
        Assertions.assertEquals(true, MillerRabin.prime(17));
        Assertions.assertEquals(false, MillerRabin.prime(25));
        Assertions.assertEquals(false, MillerRabin.prime(2932021007403L));
        Assertions.assertEquals(true, MillerRabin.prime(3613));
        Assertions.assertEquals(true, MillerRabin.prime(2932031007403L));
    }
}
