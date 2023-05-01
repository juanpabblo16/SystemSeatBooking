package struture;

public class MyLinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element, first, null);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null, last);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E removedElement = first.element;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return removedElement;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E removedElement = last.element;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return removedElement;
    }

    public boolean remove(E element) {
        Node<E> current = first;
        while (current != null) {
            if (current.element.equals(element)) {
                if (current == first) {
                    removeFirst();
                } else if (current == last) {
                    removeLast();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.element;
    }

    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.element;
    }

    public boolean contains(E element) {
        Node<E> current = first;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
