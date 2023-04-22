package structure;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {

    private Node<E> head;
    private int size;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            item = element;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> newNode = new Node<>(element);
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        E removedItem = head.item;
        head = head.next;
        size--;
        return removedItem;
    }

    public E removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        E removedItem;
        if (head.next == null) {
            removedItem = head.item;
            head = null;
        } else {
            Node<E> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            removedItem = current.next.item;
            current.next = null;
        }
        size--;
        return removedItem;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        E removedItem;
        if (index == 0) {
            removedItem = removeFirst();
        } else if (index == size - 1) {
            removedItem = removeLast();
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedItem = current.next.item;
            current.next = current.next.next;
            size--;
        }
        return removedItem;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = element;
    }

    public void clear() {
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.item = null;
            current.next = null;
            current = next;
        }
        head = null;
        size = 0;
    }

}
