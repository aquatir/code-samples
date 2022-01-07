package codesample.data_structures.queues;

public class FixedSizeCircularQueue {

    // front and rear == always valid
    private int front = 0;
    private int rear = 0;

    private int size = 0;
    private int[] elements;

    public FixedSizeCircularQueue(int k) {
        this.elements = new int[k];
    }

    public boolean enQueue(int value) {
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

    public boolean deQueue() {
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

    public int Front() {
        if (isEmpty()) return -1;
        else return elements[front];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        else return elements[rear];
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
        q.enQueue(1);
        q.enQueue(2);
        q.deQueue();
        q.enQueue(3);
        q.deQueue();
        q.enQueue(3);
        q.deQueue();
        q.enQueue(3);
        q.deQueue();
        System.out.println(q.Front()); // exp: 3
    }
}
