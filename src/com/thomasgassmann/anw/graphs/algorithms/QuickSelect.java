package com.thomasgassmann.anw.graphs.algorithms;

import java.util.Random;

public class QuickSelect {
    private static Random _random = new Random();

    public static int quickSelect(int[] values, int k) {
        return quickSelect(values, 0, values.length - 1, k);
    }

    public static int quickSelect(int[] values, int l, int r, int k) {
        int p = partition(values, l, r);
        if (p == l + k - 1) {
            return values[p];
        } else if (p > l + k - 1) {
            return quickSelect(values, l, p - 1, k);
        } else {
            return quickSelect(values, p + 1, r, k - p + l - 1);
        }
    }

    private static int partition(int[] values, int l, int r) {
        int pivotIndex = _random.nextInt(l, r + 1);
        int pivot = values[pivotIndex];
        swap(values, pivotIndex, r);
        int i = l;
        int j = r - 1;
        while (i <= j) {
            while (i < r && values[i] <= pivot) {
                i++;
            }

            while (j > l && values[j] > pivot) {
                j--;
            }

            if (j > i)
                swap(values, i, j);
            else
                break;
        }

        swap(values, r, i);
        return i;
    }

    private static void swap(int[] values, int i, int j) {
        int tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
}
