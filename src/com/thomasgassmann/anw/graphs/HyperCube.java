package com.thomasgassmann.anw.graphs;

public class HyperCube extends GraphAdjacencyList {
    private final int _dimensions;

    public HyperCube(int dimensions) {
        super((int)Math.pow(2, dimensions));
        _dimensions = dimensions;
        init();
    }

    private void init() {
        for (int i = 0; i < this.vertexCount(); i++) {
            for (int d = 0; d < _dimensions; d++) {
                // flip bit on d-th dimension, add edge
                var dBit = (i >> d & 0x1);
                int n = i;
                if (dBit == 0x1) {
                    n &= ~(1 << d);
                } else {
                    n |= 1 << d;
                }

                super.addUndirectedEdge(i, n);
            }
        }
    }
}
