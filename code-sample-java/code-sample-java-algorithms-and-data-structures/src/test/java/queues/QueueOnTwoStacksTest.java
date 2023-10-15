/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import codesample.data_structures.queues.QueueOnTwoStacks;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueOnTwoStacksTest {

    private QueueOnTwoStacks<Integer> queue;

    /**
     * Test of enqueue method, of class QueueOnTwoStacks.
     */
    @Test
    void test_enqueue_and_dequeue() {
        queue = new QueueOnTwoStacks<>();
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

    /**
     * Test exception thrown when attempting to dequeue from empty queue
     */
    @Test
    void testDequeueEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            queue = new QueueOnTwoStacks<>(); /* queue is empty when just created */
            queue.dequeue();
        });

    }

}
