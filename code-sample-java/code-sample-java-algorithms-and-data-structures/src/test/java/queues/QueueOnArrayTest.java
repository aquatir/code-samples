package queues;

import codesample.data_structures.queues.QueueOnArray;
import codesample.data_structures.queues.QueueOnTwoStacks;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueOnArrayTest {

    private QueueOnArray<Integer> queue;

    @Test
    void test_enqueue_and_dequeue() {
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

    @Test
    void test_enqueue_deque_2() {
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


    /**
     * Test exception thrown when attempting to dequeue from empty queue
     */
    @Test
    void testDequeueEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            queue = new QueueOnArray<>(); /* queue is empty when just created */
            queue.dequeue();
        });

    }
}
