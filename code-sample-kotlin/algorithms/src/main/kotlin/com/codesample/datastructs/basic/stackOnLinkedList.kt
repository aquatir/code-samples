package com.codesample.datastructs.basic

import java.lang.RuntimeException
import java.lang.StringBuilder


interface Stack<T> {
    fun push(value: T)
    fun pop(): T
}

/** The slow implementation when head is static and element are added after the head */
class StackOnLinkedListSlow<T> : Stack<T> {

    private var head: Node<T>? = null

    override fun push(value: T) {
        if (head == null) {
            head = Node(value, null)
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current!!.next = Node(value, null)
        }
    }

    override fun pop(): T {

        return when {
            head == null -> throw RuntimeException("Stack is empty")
            head != null && head!!.next == null -> {
                val value = head!!.value
                head = null
                value
            }
            else -> {
                var prev: Node<T>? = null
                var current = head
                while (current?.next != null) {
                    val next = current.next
                    prev = current
                    current = next
                }

                val value = current!!.value
                prev!!.next = null

                value
            }
        }
    }

    override fun toString(): String {
        return when (head) {
            null -> "StackOnLinkedListSlow(values=[])"
            else -> {
                val strBuilder = StringBuilder()
                strBuilder.append("StackOnLinkedListSlow(values=[")
                var current = head
                while (current?.next != null) {
                    strBuilder.append("${current.value}, ")
                    current = current.next
                }
                strBuilder.append("${current!!.value}])")
                strBuilder.toString()
            }
        }
    }
}

/** The fast implementation where head is dynamic and is used as a next value to give */
class StackOnLinkedListFast<T> : Stack<T> {

    private var head: Node<T>? = null

    override fun push(value: T) {
        if (head == null) {
            head = Node(value, null)
        } else {
            val newHead = Node(value, head)
            head = newHead
        }
    }

    override fun pop(): T {

        return when {
            head == null -> throw RuntimeException("Stack is empty")
            head != null && head!!.next == null -> {
                val value = head!!.value
                head = null
                value
            }
            else -> {
                val newHead = head!!.next
                val oldValue = head!!.value

                head = newHead
                oldValue
            }
        }
    }

    override fun toString(): String {
        return when (head) {
            null -> "StackOnLinkedListFast(values=[])"
            else -> {
                val strBuilder = StringBuilder()
                strBuilder.append("StackOnLinkedListFast(values=[")
                var current = head
                while (current?.next != null) {
                    strBuilder.append("${current.value}, ")
                    current = current.next
                }
                strBuilder.append("${current!!.value}])")
                strBuilder.toString()
            }
        }
    }
}
