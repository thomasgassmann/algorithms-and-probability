package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.Edge;
import com.thomasgassmann.anw.graphs.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class GreedyMatching {
    public static ArrayList<Edge> find(Graph graph) {
        if (graph.isDirected()) {
            return null;
        }

        var res = new ArrayList<Edge>();
        var considered = new HashSet<Integer>();
        for (var edge : graph.edges()) {
            if (!considered.contains(edge.from()) &&
                !considered.contains(edge.to())) {
                res.add(edge);
                considered.add(edge.from());
                considered.add(edge.to());
            }
        }

        return res;
    }
}
