package com.thomasgassmann.anw;

import java.util.HashSet;

public class EulerTour {
    public static LinkedList<Integer> eulerTour(Graph graph) {
        for (int i = 0; i < graph.vertexCount(); i++) {
            if (graph.degree(i) % 2 == 1) {
                return null;
            }
        }

        // for a more detailed explanation see script for
        // algorithms and probability, page 49
        var visited = new HashSet<Edge>();
        var q = expand(graph, visited, 0);
        var current = q.head();
        while (current != null) {
            if (hasUntouchedEdge(graph, visited, current.value())) {
                var res = expand(graph, visited, current.value());
                q.replace(current, res);
            }

            current = current.next();
        }

        return q;
    }

    // finds a circle from v to v, which exists (and is found eventually) because all degrees are even
    // see proof (by contradiction) in lecture notes
    public static LinkedList<Integer> expand(Graph graph, HashSet<Edge> visited, int from) {
        int v = from;
        var q = new LinkedList<Integer>();
        q.add(v);
        while (hasUntouchedEdge(graph, visited, v)) {
            var vnext = getUntouchedEdge(graph, visited, v);
            q.add(vnext);
            visited.add(new Edge(v, vnext, graph.isDirected()));

            v = vnext;
        }

        return q;
    }

    // the pseudocode in the script removes seen edges from the graph, but we just mark them here
    public static boolean hasUntouchedEdge(Graph graph, HashSet<Edge> visited, int from) {
        for (int neighbor : graph.edges(from)) {
            if (!visited.contains(new Edge(from, neighbor, graph.isDirected()))) {
                return true;
            }
        }

        return false;
    }

    // the pseudocode in the script removes seen edges from the graph, but we just mark them here
    public static int getUntouchedEdge(Graph graph, HashSet<Edge> expanded, int from) {
        for (int neighbor : graph.edges(from)) {
            if (!expanded.contains(new Edge(from, neighbor, graph.isDirected()))) {
                return neighbor;
            }
        }

        return -1;
    }
}
