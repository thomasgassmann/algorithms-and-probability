package com.thomasgassmann.anw.exercises;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RoseGarden {

    public static class Point {
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;

        public boolean isEqual(Point b) {
            return this.x == b.x && this.y == b.y;
        }
    }

    public static boolean[] contains(Point[] roses, Point[] weeds) {
        boolean[] res = new boolean[weeds.length];
        ArrayList<Point> wrap = jarvis(roses);

        for (int i = 0; i < weeds.length; i++) {
            if (weedInWrap(wrap, weeds[i])) {
                res[i] = true;
            }
        }

        return res;
    }

    private static boolean weedInWrap(ArrayList<Point> wrap, Point weed) {
        for (int i = 0; i < wrap.size(); i++) {
            Point current = wrap.get(i);
            Point next = wrap.get((i + 1) % wrap.size());
            if (toTheRight(weed, current, next)) {
                return false;
            }
        }

        return true;
    }

    private static ArrayList<Point> jarvis(Point[] points) {
        int h = 0;
        Point current = smallest(points);
        ArrayList<Point> res = new ArrayList<Point>();
        do {
            res.add(current);
            current = findNext(current, points);
            h++;
        } while (!current.isEqual(res.get(0)));
        return res;
    }

    private static Point findNext(Point current, Point[] points) {
        Point p = getRandom(points);
        while (p.isEqual(current)) {
            p = getRandom(points);
        }

        Point res = p;
        for (int i = 0; i < points.length; i++) {
            if (points[i].isEqual(p) || points[i].isEqual(current)) {
                continue;
            }

            if (toTheRight(points[i], current, res)) {
                res = points[i];
            }
        }

        return res;
    }

    private static boolean toTheRight(Point p, Point q, Point r) {
        return (q.x - p.x) * (r.y - p.y) < (q.y - p.y) * (r.x - p.x);
    }

    private static Point getRandom(Point[] points) {
        return points[ThreadLocalRandom.current().nextInt(0, points.length)];
    }

    private static Point smallest(Point[] points) {
        Point res = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < res.x || (points[i].x == res.x && points[i].y < res.y)) {
                res = points[i];
            }
        }

        return res;
    }
}
