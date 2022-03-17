package com.thomasgassmann.anw.exercises;

import java.util.ArrayList;
import java.util.Iterator;

class Subset {
    private int _repr;
    private int[] _values;

    public Subset(int[] values, int repr) {
        _repr = repr;
        _values = values;
    }

    public ArrayList<Integer> get() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int c = _repr;
        int idx = 0;
        while (c != 0) {
            if ((c & 1) == 1) {
                res.add(_values[idx]);
            }

            c = c >> 1;
            idx++;
        }

        return res;
    }
}

class SubsetIterator implements Iterator<Subset> {
    private int[] _values;
    // we start at 1, because we don't consider empty subsets
    private int _current = 1;

    public SubsetIterator(int[] values) {
        _values = values;
    }

    @Override
    public Subset next() {
        Subset ret = new Subset(_values, _current);
        _current++;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return _current < (int) Math.pow(2, _values.length);
    }
}

class SubsetIterable implements Iterable<Subset> {
    private int[] _values;

    public SubsetIterable(int[] values) {
        _values = values;
    }

    @Override
    public Iterator<Subset> iterator() {
        return new SubsetIterator(_values);
    }
}

public class CountTheDivisors {
    public static long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    private static long lcm(ArrayList<Integer> values) {
        long lcm = 1;
        for (int value : values) {
            lcm *= (value / gcd(lcm, value));
        }

        return lcm;
    }

    private static long notDivisibleCount(int[] values) {
        long MAX = (long) Math.pow(10, 10);
        long total = MAX;
        // now deduct all divisors of values[i] for all i from total
        for (Subset s : new SubsetIterable(values)) {
            ArrayList<Integer> subset = s.get();

            int multiplier = subset.size() % 2 == 0 ? 1 : -1;

            long m = lcm(subset);

            long divisible = MAX / m;

            total += multiplier * divisible;
        }

        return total;
    }

}
