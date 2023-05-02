package struture;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyMapTest {

    @Test
    public void testPutAndGet() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertEquals(1, map.get("one").intValue());
        assertEquals(2, map.get("two").intValue());
        assertEquals(3, map.get("three").intValue());
    }

    @Test
    public void testContainsKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("one", 1);
        map.put("two", 2);
        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
        assertFalse(map.containsKey("three"));
    }

    @Test
    public void testRemove() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        assertTrue(map.remove("two"));
        assertFalse(map.containsKey("two"));
        assertEquals(2, map.size());
    }

    @Test
    public void testSize() {
        MyMap<String, Integer> map = new MyMap<>();
        assertEquals(0, map.size());
        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.size());
        map.remove("one");
        assertEquals(1, map.size());
    }
}
