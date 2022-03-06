package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.GraphAdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreedyMatchingTests {
    @Test
    public void checkGreedy() {
        var graph = new GraphAdjacencyList(4);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(2, 3);

        var res = GreedyMatching.find(graph);
        Assertions.assertEquals(2, res.size());
        Assertions.assertEquals(0, res.get(0).from());
        Assertions.assertEquals(1, res.get(0).to());
        Assertions.assertEquals(2, res.get(1).from());
        Assertions.assertEquals(3, res.get(1).to());
    }
}
