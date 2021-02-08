package com.codesample.algo.datastructs.basic

import com.codesample.datastructs.basic.StackOnResizingArray
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StackOnResizingArrayTests {

    @Test
    fun testStackInsert() {
        val stack = StackOnResizingArray<Int>()
            .also { it.push(1) }

        Assertions.assertEquals("StackOnResizingArray(curSize=1,curMaxSize=2,values=[1])", stack.toString())
    }

    @Test
    fun testStackInsertThree() {
        val stack = StackOnResizingArray<Int>()
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
            }

        Assertions.assertEquals("StackOnResizingArray(curSize=3,curMaxSize=4,values=[1,2,3])", stack.toString())
    }

    @Test
    fun testStackInsertAndPop() {
        val stack = StackOnResizingArray<Int>()
            .also {
                it.push(1)
            }

        val popped = stack.pop()

        Assertions.assertEquals(1, popped)
        Assertions.assertEquals("StackOnResizingArray(curSize=0,curMaxSize=2,values=[])", stack.toString())
    }

    @Test
    fun testStackInsertTwiceAndPopTwice() {
        val stack = StackOnResizingArray<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        val popped1 = stack.pop()
        val popped2 = stack.pop()

        Assertions.assertEquals(2, popped1)
        Assertions.assertEquals(1, popped2)
        Assertions.assertEquals("StackOnResizingArray(curSize=0,curMaxSize=2,values=[])", stack.toString())
    }

    @Test
    fun testPopOnEmptyThrowException() {
        val stack = StackOnResizingArray<Int>()
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
    fun testPushOverLimitResizesAnArray() {
        val stack = StackOnResizingArray<Int>()
            .also {
                it.push(1)
                it.push(2)
            }

        Assertions.assertEquals("StackOnResizingArray(curSize=2,curMaxSize=2,values=[1,2])", stack.toString())

        stack.push(3)
        Assertions.assertEquals("StackOnResizingArray(curSize=3,curMaxSize=4,values=[1,2,3])", stack.toString())

    }

    @Test
    fun testPoppingTooMuchElementResizesAnArray() {
        val stack = StackOnResizingArray<Int>()
            .also {
                it.push(1)
                it.push(2)
                it.push(3)
                it.push(4)
                it.push(5)
            }

        Assertions.assertEquals("StackOnResizingArray(curSize=5,curMaxSize=8,values=[1,2,3,4,5])", stack.toString())

        stack.pop()
        stack.pop()
        stack.pop()
        Assertions.assertEquals("StackOnResizingArray(curSize=2,curMaxSize=4,values=[1,2])", stack.toString())

    }

}
