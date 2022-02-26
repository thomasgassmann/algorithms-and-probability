package com.thomasgassmann.anw.common;

import java.util.BitSet;
import java.util.function.Function;

public class BitsetIteratorWithCriteria extends BitsetIterator {
    private final Function<BitSet, Boolean> _pred;

    public BitsetIteratorWithCriteria(int bitsetSize, Function<BitSet, Boolean> predicate) {
        super(bitsetSize);
        _pred = predicate;
    }

    @Override
    public BitSet next() {
        BitSet res;
        while ((res = super.next()) != null) {
            if (_pred.apply(res)) {
                return res;
            }
        }

        return null;
    }
}
