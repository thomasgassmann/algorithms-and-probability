package com.thomasgassmann.anw.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EulerTourTests {
    @Test
    public void checkEulerTour() {
        var g = new GraphAdjacencyList(6);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(4, 5);
        g.addUndirectedEdge(3, 5);
        g.addUndirectedEdge(2, 4);
        g.addUndirectedEdge(2, 1);
        g.addUndirectedEdge(1, 3);
        g.addUndirectedEdge(3, 4);

        var res = EulerTour.eulerTour(g);
        // there are more possible results actually
        int[] expected = new int[] { 0, 2, 4, 3, 1, 2, 3, 5, 4, 1, 0 };

        int i = 0;
        var current = res.head();
        while (current != null) {
            Assertions.assertEquals(expected[i++], current.value());
            current = current.next();
        }
    }
}
