package codesample.data_structures.queues;

/**
 * Basic queue implementation using arrays
 */
class QueueOnArray<T> {
    private int head;
    private int tail;
    private int size;
    private T[] array;

    public QueueOnArray() {
        array = (T[]) new Object[16];
        head = 0;
    }

    public QueueOnArray(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Initial stack size should be greater then 0");
        }
        array = (T[]) new Object[initialSize];
        head = 0;
    }

    /**
     * Return true or false depending on queue being empty
     *
     * @return true or false depending on queue being empty
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get next element index to be used as tail
     */
    private void nextTail() {
        if (tail + 1 == array.length) {
            if (head == 0) {
                tail = array.length;
                resize(array.length * 2);
            } else {
                tail = 0;
            }
        } else {
            if (tail + 1 == head) {
                tail = array.length;
                resize(array.length * 2);
            } else {
                tail += 1;
            }
        }
    }

    /**
     * Get next element index to be used as head
     */
    private void nextHead() {
        if (head + 1 == array.length) {
            if (tail == 0) {
                head = tail = 0;
            } else {
                head = 0;
            }
        } else {
            if (head + 1 > tail) {
                head = tail = 0;
            } else {
                head += 1;
            }
        }
    }

    /**
     * Increase the size of array
     *
     * @param newSize new size of array
     */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < size; ++i) {
            newArray[i] = array[head];
            nextHead();
        }
        this.array = newArray;
        head = 0;
    }

    /**
     * Put an element into queue
     *
     * @param element new element to put into queue
     */
    public void enqueue(T element) {
        if (this.isEmpty()) {
            head = tail = 0;
        } else {
            nextTail();
        }
        array[tail] = element;
        ++size;
    }

    /**
     * Get an element from queue
     *
     * @return element for queue's tail
     */
    public T dequeue() {
        /* see if queue is empty */
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Attempting to dequeue from empty queue");
        }

        /* If not empty proceed with exctracting value */
        T value = array[head];

        array[head] = null;
        nextHead();
        --size;
        return value;
    }
}
