package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size >= container.length - 1) {
            extend();
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removedValue = container[index];
        Objects.checkIndex(index, size);
        modCount++;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        if (container.length - 1 == size) {
            container[container.length - 1] = null;
        }
        size--;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }

    private T[] extend() {
        return container = size == 0 ? Arrays.copyOf(container, container.length + 1)
                : Arrays.copyOf(container, container.length * 2);
    }

}