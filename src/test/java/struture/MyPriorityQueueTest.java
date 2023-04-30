package struture;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MyPriorityQueueTest {

    private static final Comparator<Integer> MIN_HEAP = Comparator.naturalOrder();
    private static final Comparator<Integer> MAX_HEAP = Comparator.reverseOrder();

    @Test
    public void testAddAndPeek() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(MIN_HEAP);
        assertTrue(queue.isEmpty());

        queue.add(10);
        assertEquals(1, queue.size());
        assertEquals(Integer.valueOf(10), queue.peek());

        queue.add(5);
        assertEquals(2, queue.size());
        assertEquals(Integer.valueOf(5), queue.peek());

        queue.add(20);
        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(5), queue.peek());

        queue.add(2);
        assertEquals(4, queue.size());
        assertEquals(Integer.valueOf(2), queue.peek());
    }

    @Test
    public void testPoll() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(MAX_HEAP);
        assertTrue(queue.isEmpty());

        queue.add(10);
        queue.add(5);
        queue.add(20);
        queue.add(2);

        assertEquals(4, queue.size());
        assertEquals(Integer.valueOf(20), queue.poll());
        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(10), queue.poll());
        assertEquals(2, queue.size());
        assertEquals(Integer.valueOf(5), queue.poll());
        assertEquals(1, queue.size());
        assertEquals(Integer.valueOf(2), queue.poll());
        assertEquals(0, queue.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(MIN_HEAP);
        queue.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityZero() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(0, MIN_HEAP);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityNegative() {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(-5, MIN_HEAP);
    }
}