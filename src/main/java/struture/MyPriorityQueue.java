package struture;

import java.util.Comparator;
import java.util.Date;

public class MyPriorityQueue<E> {

    private static final long serialVersionUID = -7720805057305804111L;

    private final Comparator<E> comparator;
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 16;

    public MyPriorityQueue() {
        this(DEFAULT_CAPACITY, null);
    }
    public MyPriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyPriorityQueue(int capacity, Comparator<E> comparator) {
        this.comparator = comparator;
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.elements = (E[]) new Object[capacity];
    }

    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException("Null elements not allowed");
        }
        if (size >= elements.length) {
            resize();
        }
        elements[size++] = element;
        upHeap(size - 1);
        return true;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return elements[0];
    }

    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = elements[0];
        elements[0] = elements[--size];
        elements[size] = null;
        downHeap(0);
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void upHeap(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >>> 1;
            E parent = elements[parentIndex];
            if (comparator.compare(element, parent) >= 0) {
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void downHeap(int index) {
        E element = elements[index];
        int half = size >>> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < size && comparator.compare(child, elements[rightChildIndex]) > 0) {
                childIndex = rightChildIndex;
                child = elements[childIndex];
            }
            if (comparator.compare(element, child) <= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }


    public boolean remove(E element) {
        if (element == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                elements[i] = elements[--size];
                elements[size] = null;
                downHeap(i);
                return true;
            }
        }
        return false;
    }
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return elements[index];
    }

}

