package com.codesample.algo.datastructs.basic

import com.codesample.datastructs.basic.StackOnLinkedListFast
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackOnLinkedListFastTests {

    @Test
    fun testStackInsert() {
        val stack = StackOnLinkedListFast<Int>()
            .also { it.push(1) }

        Assertions.assertEquals("StackOnLinkedListFast(values=[1])", stack.toString())
    }

    @Test
    fun testStackInsertThree() {
        val stack = StackOnLinkedListFast<Int>()
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
            }

        Assertions.assertEquals("StackOnLinkedListFast(values=[3, 2, 1])", stack.toString())
    }

    @Test
    fun testStackInsertAndPop() {
        val stack = StackOnLinkedListFast<Int>()
            .also {
                it.push(1)
            }

        val popped = stack.pop()

        Assertions.assertEquals(1, popped)
        Assertions.assertEquals("StackOnLinkedListFast(values=[])", stack.toString())
    }

    @Test
    fun testStackInsertTwiceAndPopTwice() {
        val stack = StackOnLinkedListFast<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        Assertions.assertEquals(2, popped1)
        Assertions.assertEquals(1, popped2)
        Assertions.assertEquals("StackOnLinkedListFast(values=[])", stack.toString())
    }

    @Test
    fun testPopOnEmptyThrowException() {
        val stack = StackOnLinkedListFast<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        Assertions.assertEquals(2, popped1)
        Assertions.assertEquals(1, popped2)

        assertThrows<RuntimeException> { stack.pop() }
    }
}
