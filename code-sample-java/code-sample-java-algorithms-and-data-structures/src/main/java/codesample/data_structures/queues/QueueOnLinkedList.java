
package codesample.data_structures.queues;

import java.util.Iterator;

/**
 * Basic queue implementation using linked list
 *
 * @author Pavel Bukhmatov (buhmatov@gmail.com; github.com/aquatir)
 */
public class QueueOnLinkedList<Item> implements Iterable {

    Node head;
    Node tail;
    int size;

    private class Node {
        private Item value;
        private Node next;

        public Node(Item value) {
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
    public void enqueue(Item value) {
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
    public Item dequeue() {
        /* see if queue is empty */
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Attempting to dequeue from empty queue");
        }

        /* Of not empty proceed with exctracting value */
        Item value = head.value;

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
     *
     * @return
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    /**
     * Iterator implementation
     */
    private class QueueIterator implements Iterator<Item> {

        Node current;

        public QueueIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
        }
    }
}
