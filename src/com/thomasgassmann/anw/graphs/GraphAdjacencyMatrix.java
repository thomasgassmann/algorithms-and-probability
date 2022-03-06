package com.thomasgassmann.anw.graphs;

import java.util.Iterator;

public class GraphAdjacencyMatrix implements Graph {
    private final boolean[][] _matrix;

    public GraphAdjacencyMatrix(boolean[][] matrix) {
        _matrix = matrix;
    }

    @Override
    public int vertexCount() {
        return _matrix.length;
    }

    @Override
    public int degree(int v) {
        int sum = 0;
        for (int i = 0; i < _matrix.length; i++) {
            if (_matrix[v][i]) {
                sum += 1;
            }
        }

        return sum;
    }

    @Override
    public boolean hasEdge(int u, int v) {
        return _matrix[u][v];
    }

    @Override
    public boolean isDirected() {
        return false; // TODO: check
    }

    @Override
    public int[] edges(int u) {
        int[] res = new int[degree(u)];
        int j = 0;
        for (int i = 0; i < _matrix.length; i++) {
            if (_matrix[u][i]) {
                res[j++] = i;
            }
        }

        return res;
    }

    @Override
    public Iterable<Edge> edges() {
        throw new RuntimeException("Not implemented yet");
    }
}
