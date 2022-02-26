package com.thomasgassmann.anw.common;

public class LinkedList<T> {
    private Node<T> _head;
    private Node<T> _tail;

    public Node<T> head() {
        return _head;
    }

    public Node<T> tail() {
        return _tail;
    }

    public void add(T value) {
        var n = new Node<T>(value);
        if (_tail == null) {
            _head = n;
            _tail = n;
            return;
        }

        _tail.next(n);
        n.prev(_tail);
        _tail = n;
    }

    public void replace(Node<T> toReplace, LinkedList<T> list) {
        if (_tail == null || list._tail == null) {
            throw new IllegalArgumentException("Either one of the lists is empty");
        }

        var prev = toReplace.prev();
        var next = toReplace.next();
        list._head.prev(prev);
        list._tail.next(next);
        if (next != null) {
            next.prev(list._tail);
        }

        if (prev != null) {
            prev.next(list._head);
        }
    }
}
