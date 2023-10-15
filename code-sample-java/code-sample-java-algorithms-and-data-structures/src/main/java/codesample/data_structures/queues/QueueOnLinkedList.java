
package codesample.data_structures.queues;

import java.util.Iterator;

/**
 * Basic queue implementation using linked list
 */
class QueueOnLinkedList<T> implements Iterable {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private final T value;
        private Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    /**
     * Puts element into queue from tail
     *
     * @param value of element to put into queue
     */
    public void enqueue(T value) {
        Node inserted = new Node(value);

        if (this.isEmpty()) {
            head = tail = inserted;
        } else {
            Node oldTail = this.tail;
            oldTail.next = inserted;
            this.tail = inserted;
        }

        ++size;
    }

    /**
     * Gets element from queue
     *
     * @return element's value
     * @throws IllegalArgumentException when attempting to dequeue
     *                                  from empty queue
     */
    public T dequeue() {
        /* see if queue is empty */
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Attempting to dequeue from empty queue");
        }

        /* Of not empty proceed with exctracting value */
        T value = head.value;

        if (size == 1) {
            tail = null;
            head = null;
        } else {
            Node newHead = head.next;
            head.next = null;
            this.head = newHead;
        }
        --size;
        return value;
    }

    /**
     * Iterator implementation
     */
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    /**
     * Iterator implementation
     */
    private class QueueIterator implements Iterator<T> {

        Node current;

        QueueIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
        }
    }
}
