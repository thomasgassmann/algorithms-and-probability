package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.common.BitsetIterableWithCriteria;
import com.thomasgassmann.anw.common.BitsetIteratorWithCriteria;
import com.thomasgassmann.anw.graphs.Graph;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HamiltonianCycle {
    public static boolean containsHamiltonianCycle(Graph graph) {
        var paths = new Paths();
        int start = 0;

        for (int i = start + 1; i < graph.vertexCount(); i++) {
            var bitset = new BitSet(graph.vertexCount());
            bitset.set(start);
            bitset.set(i);
            paths.set(bitset, i, graph.hasEdge(start, i));
        }

        // we have already done subsets of sizes 0 and 1 (1 and 2 elements respectively)
        for (int i = 3; i <= graph.vertexCount(); i++) {
            final int closure = i;
            for (BitSet subset : new BitsetIterableWithCriteria(graph.vertexCount(), new Function<BitSet, Boolean>() {
                @Override
                public Boolean apply(BitSet bitSet) {
                    return bitSet.get(start) && bitSet.cardinality() == closure;
                }
            })) {
                for (int x = start + 1; x < graph.vertexCount(); x++) {
                    for (int y : graph.edges(x)) {
                        BitSet n = BitSet.valueOf(subset.toLongArray());
                        n.set(x, false);
                        if (subset.get(y) && y != start && paths.has(n, y)) {
                            paths.set(subset, x, true);
                            break;
                        }
                    }
                }
            }
        }

        var full = new BitSet(graph.vertexCount());
        full.set(0, graph.vertexCount(), true);

        for (int n : graph.edges(start)) {
            if (paths.has(full, n)) {
                return true;
            }
        }

        return false;
    }

    private static class Paths {
        private Map<BitSet, Map<Integer, Boolean>> _map = new HashMap<>();

        // returns true iff. there exists a 1-x path in G which exactly contains all
        // items denoted by the subsetIdentifier
        public boolean has(BitSet subsetIdentifier, int x) {
            return _map.getOrDefault(subsetIdentifier, new HashMap<>()).getOrDefault(x, false);
        }

        public void set(BitSet subset, int x, boolean value) {
            if (!_map.containsKey(subset)) {
                _map.put(subset, new HashMap<>());
            }

            _map.get(subset).put(x, value);
        }
    }
}
