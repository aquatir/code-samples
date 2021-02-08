package com.codesample.algo.datastructs.basic

import QueueOnDoublyLinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException

class QueueOnDoublyLinkedListTests {

    @Test
    fun emptyQueue() {
        val queue = QueueOnDoublyLinkedList<Int>()

        assertEquals("QueueOnDoublyLinkedList(head=null,tail=null,values=[])", queue.toString())
    }

    @Test
    fun enqueueOneElement() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also { it.enqueue(1) }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=1,values=[1])", queue.toString())
    }

    @Test
    fun enqueueTwoElement() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also {
                it.enqueue(1)
                it.enqueue(2)
            }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=2,values=[1,2])", queue.toString())
    }

    @Test
    fun enqueueThreeElement() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also {
                it.enqueue(1)
                it.enqueue(2)
                it.enqueue(3)
            }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=3,values=[1,2,3])", queue.toString())
    }

    @Test
    fun enqueueOneElementAndDeque() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also { it.enqueue(1) }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=1,values=[1])", queue.toString())

        val elem1 = queue.dequeue()
        assertEquals(1, elem1)

        assertEquals("QueueOnDoublyLinkedList(head=null,tail=null,values=[])", queue.toString())
    }

    @Test
    fun enqueueTwoElementsAndDequeTwo() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also {
                it.enqueue(1)
                it.enqueue(2)
            }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=2,values=[1,2])", queue.toString())

        val elem1 = queue.dequeue()
        val elem2 = queue.dequeue()
        assertEquals(1, elem1)
        assertEquals(2, elem2)

        assertEquals("QueueOnDoublyLinkedList(head=null,tail=null,values=[])", queue.toString())
    }

    @Test
    fun enqueueThreeElementsAndDequeThree() {
        val queue = QueueOnDoublyLinkedList<Int>()
            .also {
                it.enqueue(1)
                it.enqueue(2)
                it.enqueue(3)
            }

        assertEquals("QueueOnDoublyLinkedList(head=1,tail=3,values=[1,2,3])", queue.toString())

        val elem1 = queue.dequeue()
        val elem2 = queue.dequeue()
        val elem3 = queue.dequeue()
        assertEquals(1, elem1)
        assertEquals(2, elem2)
        assertEquals(3, elem3)

        assertEquals("QueueOnDoublyLinkedList(head=null,tail=null,values=[])", queue.toString())
    }

    @Test
    fun dequeOnEmptyThrows() {
        val queue = QueueOnDoublyLinkedList<Int>()

        assertThrows<RuntimeException> { queue.dequeue() }
    }
}
