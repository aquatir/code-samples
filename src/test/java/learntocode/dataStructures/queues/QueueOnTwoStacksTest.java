/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learntocode.dataStructures.queues;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author pavel
 */
public class QueueOnTwoStacksTest {

    QueueOnTwoStacks<Integer> queue;

    /**
     * Test of enqueue method, of class QueueOnTwoStacks.
     */
    @Test
    public void testEnqueueAndDequeue() {
        queue = new QueueOnTwoStacks<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(queue.dequeue(), new Integer(1));
        assertEquals(queue.dequeue(), new Integer(2));
    }

    /**
     * Test exception thrown when attempting to dequeue from empty queue
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDequeueEmpty() {
        queue = new QueueOnTwoStacks<Integer>(); /* queue is empty when just created */
        queue.dequeue();
    }

}
