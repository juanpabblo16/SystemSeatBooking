package struture;


public class MyMap<K, V> {

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size;

    public MyMap() {
        buckets = new Node[16];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        node = new Node<>(key, value);
        node.next = buckets[index];
        buckets[index] = node;
        size++;
    }
    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean remove(K key) {
        int index = getIndex(key);
        Node<K, V> prev = null;
        Node<K, V> node = buckets[index];
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
