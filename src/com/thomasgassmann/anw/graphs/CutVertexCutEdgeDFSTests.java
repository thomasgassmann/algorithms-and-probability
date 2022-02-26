package com.thomasgassmann.anw.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CutVertexCutEdgeDFSTests {
    @Test
    public void check() {
        var g = new GraphAdjacencyList(12);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(1, 2);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(3, 4);
        g.addUndirectedEdge(4, 5);
        g.addUndirectedEdge(5, 6);
        g.addUndirectedEdge(6, 7);
        g.addUndirectedEdge(7, 5);
        g.addUndirectedEdge(3, 7);
        g.addUndirectedEdge(2, 8);
        g.addUndirectedEdge(8, 0);
        g.addUndirectedEdge(0, 9);
        g.addUndirectedEdge(9, 11);
        g.addUndirectedEdge(10, 9);
        g.addUndirectedEdge(10, 11);

        var res = CutVertexCutEdgeDFS.getCutVerticesCutEdges(g);

        int[] cutVertices = new int[] { 0, 2, 3, 9 };
        int[][] cutEdges = new int[][] { { 0, 9 }, { 9, 0 }, { 2, 3 }, { 3, 2 } };
        for (int i = 0; i < res.isCutVertex.length; i++) {
            final int local = i;
            Assertions.assertEquals(
                    res.isCutVertex[i],
                    Arrays.stream(cutVertices).anyMatch(p -> p == local));
        }

        for (int i = 0; i < res.isCutEdge.length; i++) {
            for (int j = 0; j < res.isCutEdge.length; j++) {
                final int a = i;
                final int b = j;
                Assertions.assertEquals(
                        Arrays.stream(cutEdges).anyMatch(p -> p[0] == a && p[1] == b),
                        res.isCutEdge[a][b]);
            }
        }
    }
}
