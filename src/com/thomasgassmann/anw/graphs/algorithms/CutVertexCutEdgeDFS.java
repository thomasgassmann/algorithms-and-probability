package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.Graph;

public class CutVertexCutEdgeDFS {
    public static class Result {
        boolean[][] isCutEdge;
        boolean[] isCutVertex;
    }

    private static int _count = 0;
    private static boolean[][] _inT;

    public static Result getCutVerticesCutEdges(Graph g) {
        var res = new Result();
        res.isCutEdge = new boolean[g.vertexCount()][g.vertexCount()];
        res.isCutVertex = new boolean[g.vertexCount()];
        int[] low = new int[g.vertexCount()];
        int[] dfs = new int[g.vertexCount()];
        int[] deg = new int[g.vertexCount()];
        _count = 0;
        _inT = new boolean[g.vertexCount()][g.vertexCount()];
        dfsVisit(g, res, low, dfs, deg, 0);
        if (deg[0] >= 2) {
            res.isCutVertex[0] = true;
        }

        return res;
    }

    private static void dfsVisit(Graph g, Result r, int[] low, int[] dfs, int[] deg, int vertex) {
        dfs[vertex] = ++_count;
        low[vertex] = dfs[vertex];
        for (var item : g.edges(vertex)) {
            if (dfs[item] == 0) {
                // add {item, vertex} to tree
                _inT[item][vertex] = true;
                _inT[vertex][item] = true;

                dfsVisit(g, r, low, dfs, deg, item);
                deg[vertex]++;

                // if the smallest item we can reach from item
                // is greater than vertex, we have a cut vertex
                if (low[item] >= dfs[vertex] && vertex != 0) {
                    r.isCutVertex[vertex] = true;
                    // if it's strictly greater, we have a cut edge
                    if (low[item] > dfs[vertex]) {
                        r.isCutEdge[vertex][item] = true;
                        r.isCutEdge[item][vertex] = true;
                    }
                }

                // low can only have one non-tree edge
                // this is fine because item is not a tree edge here
                low[vertex] = Math.min(low[vertex], low[item]);
            } else if (!_inT[vertex][item] && !_inT[item][vertex]) {
                // only if it's not a tree edge
                // low can only have one non-tree edge, dfs[item] here
                low[vertex] = Math.min(low[vertex], dfs[item]);
            }
        }
    }
}
