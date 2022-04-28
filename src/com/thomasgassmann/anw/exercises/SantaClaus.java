package com.thomasgassmann.anw.exercises;

public class SantaClaus {
    private static boolean allocationPossible(int[] d, int[] c) {
        int SOURCE = 0;
        int SINK = 1;
        int TOY_OFFSET = 2;
        int CHILD_OFFSET = TOY_OFFSET + c.length;
        Graph graph = new Graph(2 + d.length + c.length);
        int totalDemand = 0;
        for (int i = 0; i < d.length; i++) {
            // children demand
            graph.addEdge(CHILD_OFFSET + i, SINK, d[i]);
            totalDemand += d[i];
        }

        for (int i = 0; i < c.length; i++) {
            // toy supply
            graph.addEdge(SOURCE, TOY_OFFSET + i, c[i]);

            for (int j = 0; j < d.length; j++) {
                // toy i can be given to child j once
                graph.addEdge(TOY_OFFSET + i, CHILD_OFFSET + j, 1);
            }
        }

        int maxFlow = graph.computeMaximumFlow(SOURCE, SINK);
        return maxFlow == totalDemand;
    }
}
