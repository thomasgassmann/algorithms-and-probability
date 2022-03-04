package com.thomasgassmann.anw.exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Password {
    public static boolean check(String[] values) {
        if (values.length <= 1) {
            return true;
        }

        // connected and euler path
        var edge = new HashMap<String, ArrayList<String>>(values.length);
        var vertices = new HashSet<String>();
        var indeg = new HashMap<String, Integer>();
        var outdeg = new HashMap<String, Integer>();
        for (String val : values) {
            var f = val.substring(0, 2);
            var l = val.substring(1, 3);
            if (!edge.containsKey(f)) {
                edge.put(f, new ArrayList<>());
            }

            if (!edge.containsKey(l)) {
                edge.put(l, new ArrayList<>());
            }

            edge.get(f).add(l);
            edge.get(l).add(f);

            vertices.add(f);
            vertices.add(l);

            outdeg.put(f, outdeg.getOrDefault(f, 0) + 1);
            indeg.put(l, indeg.getOrDefault(l, 0) + 1);
        }

        String src = values[0].substring(0, 2);
        for (String item : vertices) {
            if (indeg.containsKey(item) && indeg.get(item) == 0) {
                src = item;
            }
        }

        var q = new LinkedList<String>();
        q.add(src);
        HashSet<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            var u = q.poll();
            if (visited.contains(u)) {
                continue;
            }

            visited.add(u);
            for (var v : edge.get(u)) {
                if (!visited.contains(v)) {
                    q.add(v);
                }
            }
        }


        int outDegMinusInDeg = 0;
        int inDegMinusOutDeg = 0;
        int equalInDegOutDeg = 0;

        for (String v : vertices) {
            if (outdeg.getOrDefault(v, 0) - indeg.getOrDefault(v, 0) == 1) {
                outDegMinusInDeg++;
                continue;
            }

            if (indeg.getOrDefault(v, 0) - outdeg.getOrDefault(v, 0) == 1) {
                inDegMinusOutDeg++;
                continue;
            }

            if (indeg.getOrDefault(v, 0) == outdeg.getOrDefault(v, 0)) {
                equalInDegOutDeg++;
            }
        }

        return visited.size() == vertices.size() &&
                (outDegMinusInDeg + inDegMinusOutDeg + equalInDegOutDeg == vertices.size()) &&
                inDegMinusOutDeg <= 1 &&
                outDegMinusInDeg <= 1;
    }
}
