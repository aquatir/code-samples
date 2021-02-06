package com.codesample.algo

import com.codesample.datastructs.basic.StackOnLinkedListSlow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackOnLinkedListSlowTests {

    @Test
    fun testStackInsert() {
        val stack = StackOnLinkedListSlow<Int>()
            .also { it.push(1) }

        assertEquals("StackOnLinkedListSlow(values=[1])", stack.toString())
    }

    @Test
    fun testStackInsertThree() {
        val stack = StackOnLinkedListSlow<Int>()
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
            }

        assertEquals("StackOnLinkedListSlow(values=[1, 2, 3])", stack.toString())
    }

    @Test
    fun testStackInsertAndPop() {
        val stack = StackOnLinkedListSlow<Int>()
            .also {
                it.push(1)
            }

        val popped = stack.pop()

        assertEquals(1, popped)
        assertEquals("StackOnLinkedListSlow(values=[])", stack.toString())
    }

    @Test
    fun testStackInsertTwiceAndPopTwice() {
        val stack = StackOnLinkedListSlow<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        assertEquals(2, popped1)
        assertEquals(1, popped2)
        assertEquals("StackOnLinkedListSlow(values=[])", stack.toString())
    }

    @Test
    fun testPopOnEmptyThrowException() {
        val stack = StackOnLinkedListSlow<Int>()
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
