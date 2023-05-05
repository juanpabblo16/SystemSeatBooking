package struture;


import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.util.*;


public class MyMap<K, V>  {






    private Node<K, V>[] buckets;
    private int size;

    public MyMap() {
        buckets = new Node[29];
        size = 0;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Map.Entry<K, V>[] entries = new Map.Entry[0];
        for (Map.Entry<K, V> entry : entries) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = new Node<>(key,value);


        buckets[index] = node;
        size++;
    }
    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = buckets[index];

        return node.getValue();
    }




    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = buckets[index];

        return (node == null)? false: true;
    }

    public void remove(K key) {
        int index = getIndex(key);
        buckets[index] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public String toString(){
        String items = "";
        for (Node x: buckets){
            if (x != null){
                items += "key = " + x.getKey()+ " value = " + x.getValue()+"\n";
            }
        }
        return items;
    }

}
