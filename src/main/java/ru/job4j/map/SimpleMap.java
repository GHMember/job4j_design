package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((double) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int getIndex(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity <<= 1;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> pair : oldTable) {
            if (pair != null) {
                int index = getIndex(pair.key);
                table[index] = new MapEntry<>(pair.key, pair.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        V rsl = null;
        if ((key != null && table[index] != null && table[index].key != null
                && key.hashCode() == table[index].key.hashCode())
                || (table[index] != null && Objects.equals(table[index].key, key))) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        boolean rsl = get(key) != null;
        if (rsl)  {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
