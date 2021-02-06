package com.codesample.datastructs.basic

import java.lang.RuntimeException

class StackOnNonResizingArray<T>(private val maxSize: Int) : Stack<T> {

    private val array: MutableList<T?> = MutableList(maxSize) { null }
    private var curSize: Int = 0

    override fun push(value: T) {
        if (curSize < maxSize) {
            array[curSize] = value
            curSize++
        } else {
            throw RuntimeException("Stack size exceeded")
        }
    }

    override fun pop(): T {
        return if (curSize == 0) throw RuntimeException("Stack is empty")
        else {
            val elem = array[curSize - 1]
            array[curSize - 1] = null
            curSize--
            elem!!
        }
    }

    override fun toString(): String {
        return "StackOnNonResizingArray(maxSize=$maxSize,curSize=$curSize,values=[${
            array.filterNotNull().joinToString(",")
        }])"
    }
}


/** Stack which uses an array and which will double in size when required and shrink by half when only 1/3 element
 * are available */
class StackOnResizingArray<T>() : Stack<T> {

    // initial size in 2
    private var array: MutableList<T?> = MutableList(2) { null }
    private var curSize: Int = 0
    private var curMaxSize: Int = 2


    override fun push(value: T) {
        if (curSize < curMaxSize) {
            array[curSize] = value
            curSize++
        } else {
            val toBeMaxSize = curMaxSize * 2
            val newArray = MutableList(toBeMaxSize) { index -> if (index < curMaxSize) array[index] else null }
            array = newArray
            curMaxSize = toBeMaxSize
            push(value)
        }
    }

    override fun pop(): T {
        return if (curSize == 0) throw RuntimeException("Stack is empty")
        else {
            val elem = array[curSize - 1]
            array[curSize - 1] = null
            curSize--

            if (curSize != 0 && curMaxSize / curSize >= 3) { // if current max is at least 3 times larger them curSize -> shrink array in half
                val toBeMaxSize = curMaxSize / 2
                val newArray = MutableList(toBeMaxSize) { index -> array[index] }
                array = newArray
                curMaxSize = toBeMaxSize
            }

            elem!!
        }
    }

    override fun toString(): String {
        return "StackOnResizingArray(curSize=$curSize,curMaxSize=${curMaxSize},values=[${
            array.filterNotNull().joinToString(",")
        }])"
    }

}
