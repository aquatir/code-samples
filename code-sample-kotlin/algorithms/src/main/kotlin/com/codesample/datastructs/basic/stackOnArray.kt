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
        return "StackOnNonResizingArray(maxSize=$maxSize,curSize=$curSize,values=[${array.filterNotNull().joinToString(",")}])"
    }
}


class StackOnResizingArray<out T>() {

}
