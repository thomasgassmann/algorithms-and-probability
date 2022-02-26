package com.thomasgassmann.anw.common;

import java.util.BitSet;
import java.util.Iterator;

public class BitsetIterable implements Iterable<BitSet> {
    private final int _size;

    public BitsetIterable(int size) {
        _size = size;
    }

    @Override
    public Iterator<BitSet> iterator() {
        return new BitsetIterator(_size);
    }
}
