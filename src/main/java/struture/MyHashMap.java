package struture;


import static java.util.Objects.hash;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private int size;
    private int capacity;
    private Entry<K, V>[] table;

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        this.table = (Entry<K, V>[]) new Entry[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public V put(K key, V value) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }
        Entry<K, V> newEntry = new Entry<>(key, value, table[index]);
        table[index] = newEntry;
        size++;
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> entry = table[index];
        Entry<K, V> prevEntry = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                V value = entry.value;
                if (prevEntry == null) {
                    table[index] = entry.next;
                } else {
                    prevEntry.next = entry.next;
                }
                size--;
                return value;
            }
            prevEntry = entry;
            entry = entry.next;
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[capacity];
        size = 0;
        for (Entry<K, V> oldEntry : oldTable) {
            while (oldEntry != null) {
                K key = oldEntry.key;
                V value = oldEntry.value;
                int index = hash(key);
                Entry<K, V> newEntry = new Entry<>(key, value, table[index]);
                table[index] = newEntry;
                oldEntry = oldEntry.next;
                size++;
            }
        }
    }

}
