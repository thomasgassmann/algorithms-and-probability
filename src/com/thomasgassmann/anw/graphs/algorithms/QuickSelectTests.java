package com.thomasgassmann.anw.graphs.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickSelectTests {
    @Test
    public void checkQuickSelect() {
        int[] values = new int[] { 10, 5, 1, 4, 7, 8, 2, 6, 9, 3 };
        for (int i = 1; i <= 10; i++) {
            Assertions.assertEquals(i, QuickSelect.quickSelect(values, i));
        }
    }
}
