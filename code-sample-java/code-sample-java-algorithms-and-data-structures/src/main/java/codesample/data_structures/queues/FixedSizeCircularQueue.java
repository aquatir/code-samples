package codesample.data_structures.queues;

public class FixedSizeCircularQueue {

    // front and rear == always valid
    private int front = 0;
    private int rear = 0;

    private int size = 0;
    private final int[] elements;

    public FixedSizeCircularQueue(int k) {
        this.elements = new int[k];
    }

    public boolean enqueue(int value) {
        if (isFull()) {
            return false;
        }

        calculateNewRear();
        this.elements[rear] = value;
        size++;
        return true;
    }

    private void calculateNewRear() {
        if (isEmpty()) {
            rear = front = 0;
        } else if (rear + 1 == elements.length) {
            rear = 0;
        } else {
            rear = rear + 1;
        }
    }

    public boolean dequeue() {
        if (isEmpty()) {
            rear = front = 0;
            return false;
        }

        calculateNewFront();
        size--;
        return true;
    }

    private void calculateNewFront() {
        if (front + 1 == elements.length) {
            front = 0;
        } else {
            front = front + 1;
        }
    }

    public int front() {
        if (isEmpty()) {
            return -1;
        } else {
            return elements[front];
        }
    }

    public int rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return elements[rear];
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == elements.length;
    }

    public static void main(String[] args) {
        /*
["MyCircularQueue",
"enQueue", 1
"enQueue", 2
"deQueue", -
"enQueue", 3
"deQueue", -
"enQueue", 3
"deQueue", -
"enQueue", 3
"deQueue",  -
"Front"] -
[[2],[1],[2],[],[3],[],[3],[],[3],[],[]]
exp: [null,true,true,true,true,true,true,true,true,true,3]
my:  [null,true,true,true,true,true,true,true,true,true,0]
         */

        var q = new FixedSizeCircularQueue(2);
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.enqueue(3);
        q.dequeue();
        q.enqueue(3);
        q.dequeue();
        q.enqueue(3);
        q.dequeue();
        System.out.println(q.front()); // exp: 3
    }
}
