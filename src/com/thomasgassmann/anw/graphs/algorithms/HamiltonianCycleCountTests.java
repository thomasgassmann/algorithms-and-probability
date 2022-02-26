package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.HyperCube;
import org.junit.jupiter.api.Test;

public class HamiltonianCycleCountTests {
    @Test
    public void checkNumberOfHamiltonianPaths() {
        var cube = new HyperCube(2);
        var number = HamiltonianCycleCount.countHamiltonianCycles(cube);
    }
}
