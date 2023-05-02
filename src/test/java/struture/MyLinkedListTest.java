package struture;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyLinkedListTest {

    @Test
    public void testAddFirst() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.size());
        assertEquals((Integer) 3, list.getFirst());
        assertEquals((Integer) 1, list.getLast());
    }

    @Test
    public void testAddLast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.size());
        assertEquals((Integer) 1, list.getFirst());
        assertEquals((Integer) 3, list.getLast());
    }

    @Test
    public void testRemoveFirst() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals((Integer) 3, list.removeFirst());
        assertEquals((Integer) 2, list.getFirst());
        assertEquals((Integer) 1, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveLast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals((Integer) 1, list.removeLast());
        assertEquals((Integer) 3, list.getFirst());
        assertEquals((Integer) 2, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    public void testRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertFalse(list.remove(4));
        assertTrue(list.remove(2));
        assertEquals((Integer) 3, list.getFirst());
        assertEquals((Integer) 1, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    public void testGetFirst() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertNull(list.getFirst());
        list.addFirst(1);
        assertEquals((Integer) 1, list.getFirst());
    }

    @Test
    public void testGetLast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertNull(list.getLast());
        list.addLast(1);
        assertEquals((Integer) 1, list.getLast());
    }

    @Test
    public void testContains() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertFalse(list.contains(4));
        assertTrue(list.contains(2));
    }
}
