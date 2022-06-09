package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.BipartiteGraph;
import com.thomasgassmann.anw.graphs.Edge;
import com.thomasgassmann.anw.graphs.Graph;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

public class AugmentingPath {
    public static class Layers {
        private HashMap<Integer, HashSet<Integer>> _layers = new HashMap<>();

        public void add(int vertex, int layer) {
            if (!_layers.containsKey(layer)) {
                _layers.put(layer, new HashSet<Integer>());
            }

            _layers.get(layer).add(vertex);
        }

        public HashSet<Integer> getVerticesInLayer(int layer) {
            return _layers.get(layer);
        }

        public boolean isEmpty(int layer) {
            return _layers.get(layer) == null || _layers.get(layer).isEmpty();
        }
    }

    public static ArrayList<Edge> getAugmentingPathInBipartiteGraph(
            BipartiteGraph graph,
            HashSet<Edge> matching) {
        var layers = new Layers();
        HashSet<Integer> coveredA = new HashSet<>();
        HashSet<Integer> coveredB = new HashSet<>();
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Edge> nonMatching = new HashSet<>();
        for (var edge : matching) {
            if (graph.getA().contains(edge.from())) {
                coveredA.add(edge.from());
                coveredB.add(edge.to());
            } else {
                coveredA.add(edge.to());
                coveredB.add(edge.from());
            }
        }

        for (var edge : graph.edges()) {
            if (!matching.contains(edge)) {
                nonMatching.add(edge);
            }
        }

        for (var a : graph.getA()) {
            if (!coveredA.contains(a)) {
                layers.add(a, 0);
                visited.add(a);
            }
        }

        if (layers.isEmpty(0)) {
            return null;
        }

        for (int i = 1; i <= graph.vertexCount(); i++) {
            HashSet<Integer> prev = layers.getVerticesInLayer(i - 1);
            if (i % 2 == 1) {
                // take non-matching edges

            } else {
                // take matching edges
                
            }
        }
    }
}
