package com.codesample.algo.datastructs.basic

import com.codesample.datastructs.basic.StackOnNonResizingArray
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackOnNonResizingArrayTests {
    @Test
    fun testStackInsert() {
        val stack = StackOnNonResizingArray<Int>(5)
            .also { it.push(1) }

        Assertions.assertEquals("StackOnNonResizingArray(maxSize=5,curSize=1,values=[1])", stack.toString())
    }

    @Test
    fun testStackInsertThree() {
        val stack = StackOnNonResizingArray<Int>(5)
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
            }

        Assertions.assertEquals("StackOnNonResizingArray(maxSize=5,curSize=3,values=[1,2,3])", stack.toString())
    }

    @Test
    fun testStackInsertAndPop() {
        val stack = StackOnNonResizingArray<Int>(5)
            .also {
                it.push(1)
            }

        val popped = stack.pop()

        Assertions.assertEquals(1, popped)
        Assertions.assertEquals("StackOnNonResizingArray(maxSize=5,curSize=0,values=[])", stack.toString())
    }

    @Test
    fun testStackInsertTwiceAndPopTwice() {
        val stack = StackOnNonResizingArray<Int>(5)
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        Assertions.assertEquals(2, popped1)
        Assertions.assertEquals(1, popped2)
        Assertions.assertEquals("StackOnNonResizingArray(maxSize=5,curSize=0,values=[])", stack.toString())
    }

    @Test
    fun testPopOnEmptyThrowException() {
        val stack = StackOnNonResizingArray<Int>(5)
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

    @Test
    fun testPushOverLimitThrowException() {
        val stack = StackOnNonResizingArray<Int>(2)
            .also {
                it.push(1)
                it.push(2)
            }

        assertThrows<RuntimeException> { stack.push(3) }
    }
}
