package queues;

import codesample.data_structures.queues.Queue;
import codesample.data_structures.queues.QueueOnArray;
import org.junit.jupiter.api.Test;

class QueueOnArrayTest {

    private final Queue<Integer> queue = new QueueOnArray<>();

    @Test
    void test_enqueue_and_dequeue() {
        GenericQueueTests.testEnqueueDequeue1(queue);
    }

    @Test
    void test_enqueue_deque_2() {
        GenericQueueTests.testEnqueueDequeue2(queue);
    }


    /**
     * Test exception thrown when attempting to dequeue from empty queue
     */
    @Test
    void testDequeueEmpty() {
        GenericQueueTests.testError(queue);

    }
}
