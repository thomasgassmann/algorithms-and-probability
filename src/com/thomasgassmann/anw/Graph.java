package com.thomasgassmann.anw;

public interface Graph {
    int vertexCount();
    int degree(int v);
    boolean isDirected();
    int[] edges(int u);
}
