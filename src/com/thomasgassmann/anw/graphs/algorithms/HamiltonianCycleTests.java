package com.thomasgassmann.anw.graphs.algorithms;

import com.thomasgassmann.anw.graphs.HyperCube;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HamiltonianCycleTests {
    @Test
    public void checkHamiltonianCycle() {
        var cube = new HyperCube(4);
        var res = HamiltonianCycle.containsHamiltonianCycle(cube);
        Assertions.assertTrue(res);
    }
}
