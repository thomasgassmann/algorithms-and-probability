package com.thomasgassmann.anw.exercises;

import org.junit.jupiter.api.Test;

public class RoseGardenTests {
    @Test
    public void check() {
        RoseGarden.Point[] roses = new RoseGarden.Point[] {
                new RoseGarden.Point(0, 0),
                new RoseGarden.Point(3, 3),
                new RoseGarden.Point(0, 3),
                new RoseGarden.Point(3, 0)
        };
        RoseGarden.Point[] weeds = new RoseGarden.Point[] {
                new RoseGarden.Point(1, 2),
                new RoseGarden.Point(-1, 1),
                new RoseGarden.Point(2, 3)
        };

        boolean[] res = RoseGarden.contains(roses, weeds);
    }
}
