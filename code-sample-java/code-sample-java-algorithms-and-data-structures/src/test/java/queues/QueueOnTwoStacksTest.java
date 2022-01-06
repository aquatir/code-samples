/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import codesample.data_structures.queues.QueueOnTwoStacks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueOnTwoStacksTest {

    private QueueOnTwoStacks<Integer> queue;

    /**
     * Test of enqueue method, of class QueueOnTwoStacks.
     */
    @Test
    public void testEnqueueAndDequeue() {
        queue = new QueueOnTwoStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.dequeue(), new Integer(1));
        assertEquals(queue.dequeue(), new Integer(2));
    }

    /**
     * Test exception thrown when attempting to dequeue from empty queue
     */
    @Test
    public void testDequeueEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            queue = new QueueOnTwoStacks<>(); /* queue is empty when just created */
            queue.dequeue();
        });

    }

}
