package queues;

import codesample.data_structures.queues.Queue;
import codesample.data_structures.queues.QueueOnArray;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericQueueTests {

    public static void testEnqueueDequeue1(Queue<Integer> queue) {
        queue = new QueueOnArray<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(3);
        assertThat(queue.dequeue()).isEqualTo(4);
        assertThat(queue.dequeue()).isEqualTo(5);

        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertThat(queue.dequeue()).isEqualTo(6);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(3);
        assertThat(queue.dequeue()).isEqualTo(4);
        assertThat(queue.dequeue()).isEqualTo(5);
    }

    public static void testEnqueueDequeue2(Queue<Integer> queue) {
        queue = new QueueOnArray<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(2);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(3);
        assertThat(queue.dequeue()).isEqualTo(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(3);
        queue.enqueue(1);
        queue.enqueue(2);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(2);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(3);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(3);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(3);
        queue.enqueue(3);
        assertThat(queue.dequeue()).isEqualTo(3);
    }

    public static void testError(Queue<Integer> emptyQueue) {
        assertThrows(IllegalArgumentException.class, emptyQueue::dequeue);
    }
}
