package com.thomasgassmann.anw.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GraphAdjacencyList implements Graph {
    protected ArrayList<Integer>[] _adjacency;
    protected boolean _isDirected = false;

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
    public boolean hasEdge(int u, int v) {
        return _adjacency[u].stream().anyMatch(p -> p.equals(v));
    }

    @Override
    public boolean isDirected() {
        return _isDirected;
    }

    @Override
    public int[] edges(int u) {
        return _adjacency[u].stream().mapToInt(p -> p).toArray();
    }

    @Override
    public Iterable<Edge> edges() {
        return new Iterable<Edge>() {
            @Override
            public Iterator<Edge> iterator() {
                return new Iterator<Edge>() {
                    private int _current = 0;
                    private int _currentEdge = 0;

                    @Override
                    public boolean hasNext() {
                        return _current < _adjacency.length &&
                               _currentEdge < _adjacency[_current].size();
                    }

                    @Override
                    public Edge next() {
                        int from = _current;
                        int to = _adjacency[from].get(_currentEdge);
                        if (_currentEdge < _adjacency[_current].size() - 1) {
                            _currentEdge++;
                        } else {
                            _current++;
                            _currentEdge = 0;
                        }

                        return new Edge(from, to, isDirected());
                    }
                };
            }
        };
    }
}
