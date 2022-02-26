package com.thomasgassmann.anw.common;

import java.util.BitSet;
import java.util.Iterator;
import java.util.function.Function;

public class BitsetIterableWithCriteria implements Iterable<BitSet> {
    private BitsetIteratorWithCriteria _iterator;

    public BitsetIterableWithCriteria(int size, Function<BitSet, Boolean> fn) {
        _iterator = new BitsetIteratorWithCriteria(size, fn);
    }

    @Override
    public Iterator<BitSet> iterator() {
        return _iterator;
    }
}
