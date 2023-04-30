package struture;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void testPutAndGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
        assertEquals(Integer.valueOf(3), map.get("three"));
    }

    @Test
    public void testPutDuplicate() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("one", 2);

        assertEquals(Integer.valueOf(2), map.get("one"));
    }

    @Test
    public void testRemove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(Integer.valueOf(2), map.remove("two"));
        assertNull(map.get("two"));
        assertEquals(2, map.size());
    }

    @Test
    public void testContainsKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertTrue(map.containsKey("one"));
        assertFalse(map.containsKey("four"));
    }

    @Test
    public void testContainsValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(4));
    }
}
