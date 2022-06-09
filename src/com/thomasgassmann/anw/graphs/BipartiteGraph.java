package com.thomasgassmann.anw.graphs;

import java.util.HashSet;

public interface BipartiteGraph extends Graph {
    HashSet<Integer> getA();
    HashSet<Integer> getB();
}
