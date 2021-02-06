package com.codesample.algo

import com.codesample.datastructs.basic.StackOnLinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackOnLinkedListTests {

    @Test
    fun testStackInsert() {
        val stack = StackOnLinkedList<Int>()
            .also { it.push(1) }

        assertEquals("StackOnLinkedList(values=[1])", stack.toString())
    }

    @Test
    fun testStackInsertThree() {
        val stack = StackOnLinkedList<Int>()
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
            }

        assertEquals("StackOnLinkedList(values=[1, 2, 3])", stack.toString())
    }

    @Test
    fun testStackInsertAndPop() {
        val stack = StackOnLinkedList<Int>()
            .also {
                it.push(1)
            }

        val popped = stack.pop()

        assertEquals(1, popped)
        assertEquals("StackOnLinkedList(values=[])", stack.toString())
    }

    @Test
    fun testStackInsertTwiceAndPopTwice() {
        val stack = StackOnLinkedList<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        assertEquals(2, popped1)
        assertEquals(1, popped2)
        assertEquals("StackOnLinkedList(values=[])", stack.toString())
    }

    @Test
    fun testPopOnEmptyThrowException() {
        val stack = StackOnLinkedList<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        assertEquals(2, popped1)
        assertEquals(1, popped2)

        assertThrows<RuntimeException> { stack.pop() }
    }
}
