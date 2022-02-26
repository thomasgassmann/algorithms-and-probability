package com.thomasgassmann.anw.graphs;

public interface Graph {
    int vertexCount();
    int degree(int v);
    boolean hasEdge(int u, int v);
    boolean isDirected();
    int[] edges(int u);
}
