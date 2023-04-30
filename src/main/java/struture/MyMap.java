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
}
