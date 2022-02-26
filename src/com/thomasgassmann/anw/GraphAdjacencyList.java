package com.thomasgassmann.anw;

import java.util.ArrayList;

public class GraphAdjacencyList implements Graph {
    private ArrayList<Integer>[] _adjacency;
    private boolean _isDirected = false;

    public GraphAdjacencyList(int vertexCount) {
        _adjacency = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            _adjacency[i] = new ArrayList();
        }
    }

    public void addDirectedEdge(int u, int v) {
        _adjacency[u].add(v);
        _isDirected = true;
    }

    public void addUndirectedEdge(int u, int v) {
        addDirectedEdge(u, v);
        addDirectedEdge(v, u);
        _isDirected = false;
    }

    @Override
    public int vertexCount() {
        return _adjacency.length;
    }

    @Override
    public int degree(int v) {
        return _adjacency[v].size();
    }

    @Override
    public boolean isDirected() {
        return _isDirected;
    }

    @Override
    public int[] edges(int u) {
        return _adjacency[u].stream().mapToInt(p -> p).toArray();
    }
}
