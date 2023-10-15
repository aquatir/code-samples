/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import codesample.data_structures.queues.Queue;
import codesample.data_structures.queues.QueueOnArray;
import codesample.data_structures.queues.QueueOnTwoStacks;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueOnTwoStacksTest {

    private final Queue<Integer> queue = new QueueOnTwoStacks<>();

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
