package com.thomasgassmann.anw.common;

import java.util.BitSet;
import java.util.function.Function;

public class BitsetIteratorWithCriteria extends BitsetIterator {
    private Function<BitSet, Boolean> _fn;
    private BitSet _next;
    private boolean _hasNext;

    public BitsetIteratorWithCriteria(int bitsetSize, Function<BitSet, Boolean> fn) {
        super(bitsetSize);
        _fn = fn;
        findNext();
    }

    @Override
    public boolean hasNext() {
        return _next != null;
    }

    @Override
    public BitSet next() {
        var returnValue = _next;
        this.findNext();
        return returnValue;
    }

    private void findNext() {
        while (super.hasNext()) {
            _next = super.next();
            if (_fn.apply(_next)) {
                return;
            }
        }

        this._next = null;
        this._hasNext = false;
    }
}
