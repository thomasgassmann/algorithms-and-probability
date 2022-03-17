package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.GraphAdjacencyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreedyColoringTests {
    @Test
    public void checkColoring() {
        var g = new GraphAdjacencyList(4);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(1, 3);
        g.addUndirectedEdge(3, 2);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(2, 1);

        var c = GreedyColoring.find(g);
        Assertions.assertEquals(0, c.get(0));
        Assertions.assertEquals(1, c.get(1));
        Assertions.assertEquals(2, c.get(2));
        Assertions.assertEquals(0, c.get(3));
    }
}
