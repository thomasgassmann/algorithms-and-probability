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
            z += (int)Math.pow(-1, subset.cardinality()) * countWalks(graph, start, subset);
        }

        return z / 2;
    }

    // basically Floyd-Warshall with scalar product to count walks
    // from vertex back to vertex using some excluded vertices
    // we only consider the subgraph induced by removing the excluded
    // vertices. This could be done a bit more efficiently if we used
    // matrix multiplication with iterative multiplication.
    private static int countWalks(Graph graph, int vertex, BitSet exclude) {
        int[][] m = new int[graph.vertexCount()][graph.vertexCount()];
        for (int u = 0; u < graph.vertexCount(); u++) {
            for (int v : graph.edges(u)) {
                if (exclude.get(u) || exclude.get(v)) {
                    continue;
                }

                m[u][v] = 1;
            }
        }

        for (int k = 0; k < graph.vertexCount(); k++) {
            for (int u = 0; u < graph.vertexCount(); u++) {
                for (int v = 0; v < graph.vertexCount(); v++) {
                    if (exclude.get(u) || exclude.get(v) || exclude.get(k)) {
                        continue;
                    }

                    m[u][v] = m[u][v] + m[u][k] * m[k][v];
                }
            }
        }

        return m[vertex][vertex];
    }
}
