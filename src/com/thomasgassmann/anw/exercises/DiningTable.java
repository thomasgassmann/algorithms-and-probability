package com.thomasgassmann.anw.exercises;

import java.util.ArrayList;
import java.util.LinkedList;

public class DiningTable {
    public static int[] match(ArrayList<Integer>[] adjacency, int r) {
        boolean[] color = new boolean[adjacency.length];
        color[r] = false;
        if (!twoColorable(adjacency, color, r)) {
            return null;
        }

        int count = 0;
        for (int i = 0; i < adjacency.length; i++) {
            if (color[i] == color[r]) {
                count++;
            }
        }

        int[] res = new int[count];
        int j = 0;
        for (int i = 0; i < adjacency.length; i++) {
            if (color[i] == color[r]) {
                res[j++] = i;
            }
        }

        return res;
    }

    private static boolean twoColorable(
            ArrayList<Integer>[] adjacency,
            boolean[] color,
            int startVertex) {
        boolean[] visited = new boolean[adjacency.length];
        visited[startVertex] = true;
        var q = new LinkedList<Integer>();
        q.add(startVertex);
        while (!q.isEmpty()) {
            var u = q.poll();
            for (var v : adjacency[u]) {
                if (visited[v] && color[v] == color[u]) {
                    return false;
                }

                if (!visited[v]) {
                    q.add(v);
                    visited[v] = true;
                    color[v] = !color[u];
                }
            }
        }

        return true;
    }

}
