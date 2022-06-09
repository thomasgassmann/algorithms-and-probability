package com.thomasgassmann.anw.graphs;

import java.util.HashSet;

public class BipartiteGraphAdjacencyList extends GraphAdjacencyList implements BipartiteGraph {
    public BipartiteGraphAdjacencyList(int vertexCount) {
        super(vertexCount);
    }

    private HashSet<Integer> _a = new HashSet<>();
    private HashSet<Integer> _b = new HashSet<>();

    @Override
    public void addDirectedEdge(int u, int v) {
        super.addDirectedEdge(u, v);
        if ((_b.contains(u) && _b.contains(v)) || (_a.contains(u) && _a.contains(v))) {
            throw new IllegalArgumentException();
        }

        _a.add(u);
        _b.add(v);
    }

    @Override
    public HashSet<Integer> getA() {
        return _a;
    }

    @Override
    public HashSet<Integer> getB() {
        return _b;
    }
}
