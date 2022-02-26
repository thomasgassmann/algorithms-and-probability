package com.thomasgassmann.anw;

public class Edge {
    private final int _from;
    private final int _to;
    private final boolean _isDirected;

    public Edge(int from, int to, boolean isDirected) {
        this._from = from;
        this._to = to;
        this._isDirected = isDirected;
    }

    public int from() {
        return _from;
    }

    public int to() {
        return _to;
    }

    @Override
    public int hashCode() {
        if (!_isDirected) {
            var smaller = Math.min(_from, _to);
            var larger = Math.max(_from, _to);
            return smaller << 16 + larger;
        }

        return _from << 16 + _to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var edge = (Edge) o;
        if (edge._isDirected != this._isDirected) {
            return false;
        }

        if (edge._isDirected) {
            return edge._from == _from && edge._to == _to;
        }

        return Math.max(edge._from, edge._to) == Math.max(_from, _to) &&
               Math.min(edge._from, edge._to) == Math.min(_from, _to);
    }
}
