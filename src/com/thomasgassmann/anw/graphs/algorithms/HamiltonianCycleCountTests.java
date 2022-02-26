package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.GraphAdjacencyMatrix;
import com.thomasgassmann.anw.graphs.HyperCube;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HamiltonianCycleCountTests {
    @Test
    public void checkNumberOfHamiltonianPaths() {
        var graph = new GraphAdjacencyMatrix(new boolean[][] {
                { false, true, true },
                { true, false, true },
                { true, true, false }
        });
        var number = HamiltonianCycleCount.countHamiltonianCycles(graph);
        Assertions.assertEquals(1, number);

        var cube = new HyperCube(3);
        var res = HamiltonianCycleCount.countHamiltonianCycles(cube);
        Assertions.assertEquals(6, res);
    }
}
