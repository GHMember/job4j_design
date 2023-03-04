package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        for (int i = index; i > 0; i--) {
            node = node.next;
        }
        return node.item;
    }

    public T deleteFirst() {
        T deleted;
        if (head != null) {
            deleted = head.item;
            head = head.next;
        } else {
            throw new NoSuchElementException();
        }
        size--;
        modCount++;

        return deleted;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = node.item;
                node = node.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {

        ForwardLinked<Integer> list = new ForwardLinked<>();
        System.out.println(list.size);
        list.add(1);
        System.out.println(list.size);
        list.deleteFirst();
        System.out.println(list.size);
        list.deleteFirst();
        System.out.println(list.size);
    }
}
