package com.thomasgassmann.anw;

public class Node<T> {
    private T _value;
    private Node<T> _prev;
    private Node<T> _next;

    public Node(T value) {
        _value = value;
    }

    public T value() {
        return _value;
    }

    public Node<T> next() {
        return _next;
    }

    public Node<T> prev() {
        return _prev;
    }

    public void prev(Node<T> prev) {
        _prev = prev;
    }

    public void next(Node<T> next) {
        _next = next;
    }
}
