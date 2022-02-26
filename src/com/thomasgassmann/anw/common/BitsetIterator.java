package com.thomasgassmann.anw.common;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Iterator;

public class BitsetIterator implements Iterator<BitSet> {
    private final int _bitsetSize;
    private BigInteger current = new BigInteger("0");
    private BigInteger _max;
    private boolean _next = true;

    public BitsetIterator(int bitsetSize) {
        _bitsetSize = bitsetSize;
        _max = new BigInteger("2").pow(bitsetSize);
    }

    @Override
    public boolean hasNext() {
        return _next;
    }

    @Override
    public BitSet next() {
        var bitset = new BitSet(_bitsetSize);
        for (int i = 0; i < _bitsetSize; i++) {
            bitset.set(i, current.testBit(i));
        }

        current = current.add(new BigInteger("1"));
        if (current.compareTo(_max) >= 0) {
            _next = false;
        }

        return bitset;
    }
}
