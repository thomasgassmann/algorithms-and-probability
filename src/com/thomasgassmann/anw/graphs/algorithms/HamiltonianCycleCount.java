package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.common.BitsetIterableWithCriteria;
import com.thomasgassmann.anw.common.BitsetIteratorWithCriteria;
import com.thomasgassmann.anw.graphs.Graph;

import java.util.BitSet;
import java.util.function.Function;

public class HamiltonianCycleCount {
    public static int countHamiltonianCycles(Graph graph) {
        int start = 0;
        int z = countWalks(graph, start, new BitSet(graph.vertexCount()));
        for (BitSet subset : new BitsetIterableWithCriteria(graph.vertexCount(), new Function<BitSet, Boolean>() {
            @Override
            public Boolean apply(BitSet bitSet) {
                return !bitSet.get(start) && bitSet.cardinality() != 0;
            }
        })) {
            int multiplier = (int)Math.pow(-1, subset.cardinality());
            z += multiplier * countWalks(graph, start, subset);
        }

        return z / 2;
    }

    // we only consider the subgraph induced by removing the excluded
    // vertices. This could be done a bit more efficiently if we used
    // matrix multiplication with iterative multiplication.
    private static int countWalks(Graph graph, int vertex, BitSet exclude) {
        int[][] paths = new int[graph.vertexCount()][graph.vertexCount()];
        for (int u = 0; u < graph.vertexCount(); u++) {
            for (int v : graph.edges(u)) {
                if (!exclude.get(u) && !exclude.get(v)) {
                    paths[u][v] = 1;
                }
            }
        }

        var m = iterativeSquaring(paths, graph.vertexCount());
        return m[vertex][vertex];
    }

    private static int[][] iterativeSquaring(int[][] a, int n) {
        if (n == 1) {
            return a;
        }

        if (n == 0) {
            throw new IllegalArgumentException("Shouldn't happen");
        }

        var res = iterativeSquaring(a, n / 2);
        var mul = multiply(res, res);
        if (n % 2 == 0) {
            return mul;
        } else {
            return multiply(mul, a);
        }
    }

    // this is not Strassen! :)
    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for (int u = 0; u < a.length; u++) {
            for (int v = 0; v < b[0].length; v++) {
                int sum = 0;
                for (int k = 0; k < a[0].length; k++) {
                    sum += a[u][k] * b[k][v];
                }

                c[u][v] = sum;
            }
        }

        return c;
    }
}
