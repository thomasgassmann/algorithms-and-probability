package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.Graph;

import java.util.HashMap;

public class GreedyColoring {
    public static HashMap<Integer, Integer> find(Graph graph) {
        if (graph.vertexCount() == 0) {
            return null;
        }

        var res = new HashMap<Integer, Integer>();
        res.put(0, 0);

        for (int i = 1; i < graph.vertexCount(); i++) {
            int deg = graph.degree(i);
            boolean[] colors = new boolean[deg + 1];
            for (int v : graph.edges(i)) {
                if (res.containsKey(v)) {
                    colors[res.get(v)] = true;
                }
            }

            for (int j = 0; j < colors.length; j++) {
                if (!colors[j]) {
                    res.put(i, j);
                    break;
                }
            }
        }

        return res;
    }
}
