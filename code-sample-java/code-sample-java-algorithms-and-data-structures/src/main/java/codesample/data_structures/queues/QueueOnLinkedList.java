
package codesample.data_structures.queues;

import java.util.Iterator;

/**
 * Basic queue implementation using linked list
 */
public class QueueOnLinkedList<T> implements Iterable<T>, Queue<T> {

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
    @Override
    public void enqueue(T value) {
        Node inserted = new Node(value);

        if (this.isEmpty()) {
            head = inserted;
            tail = inserted;
        } else {
            var oldTail = this.tail;
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
    @Override
    public T dequeue() {
        /* see if queue is empty */
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Attempting to dequeue from empty queue");
        }

        /* If not empty proceed with extracting value */
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
