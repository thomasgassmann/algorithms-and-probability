package com.thomasgassmann.anw;

public interface Graph {
    int vertexCount();
    int degree(int v);
    int[] edges(int u);
}
