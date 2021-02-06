package com.codesample.datastructs.basic

import java.lang.StringBuilder



interface LinkedList<T> {

    /** Add element [value] to end of list */
    fun add(value: T): LinkedList<T>

    /** Remove first occurrence of element [value] in list*/
    fun removeFirst(value: T): LinkedList<T>

    /** Revert list */
    fun revert(): LinkedList<T>
}

class SinglyLinkedList<T>(
    var head: Node<T>? = null,
    var size: Int = 0
): LinkedList<T> {

    override fun add(value: T): LinkedList<T> {

        // First element become head
        if (head == null) {
            head = Node(value, null)
        } else { // traverse until find an element with no next. Add this next to element.
            var current = head!!
            while (current.next != null) {
                current = current.next!!
            }
            current.next = Node(value, null)
        }
        size++
        return this
    }

    override fun removeFirst(value: T): LinkedList<T> {
        return when {
            head == null -> this // no elements
            head != null && head!!.value == value -> { // element is found in head
                head = head!!.next
                size--
                this
            }
            else -> {
                var prev = head!!
                var current = head!!.next
                while (current != null) {
                    if (current.value == value) {
                        // found an element which should be deleted.
                        prev.next = current.next
                        size--
                        break
                    }
                    prev = current
                    current = current.next
                }
                this
            }
        }
    }

    override fun toString(): String {
        return when (head) {
            null -> "SinglyLinkedList(size = 0, values=[])"
            else -> {
                val strBuilder = StringBuilder()
                strBuilder.append("SinglyLinkedList(size = $size, values=[")
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

    override fun revert(): LinkedList<T> {
        return if (head == null) this
        else  {
            var prev: Node<T>? = null
            var current = head

            while (current?.next != null) {
                val next = current.next
                current.next = prev
                prev = current
                current = next
            }

            current!!.next = prev
            head = current
            this
        }
    }
}

fun main() {
    val list = SinglyLinkedList<Int>()
    list.add(3)
        .add(5)
        .also { println(it) } // 3,5
        .removeFirst(3)
        .removeFirst(5)
        .also { println(it) } // null
        .add(1)
        .add(2)
        .add(3)
        .also { println(it) } // 1, 2, 3
        .add(3)
        .add(2)
        .also { println(it) } // 1, 2, 3, 3, 2
        .removeFirst(3) // 1, 2, 3, 2
        .also { println(it) } // 1, 2, 3, 2
        .removeFirst(3) // 1, 2, 2
        .removeFirst(3) // 1, 2, 2
        .removeFirst(3) // 1, 2, 2
        .also { println(it) } // // 1, 2, 2
        .removeFirst(2) // 1, 2
        .also { println(it) } // // 1, 2
        .removeFirst(2) // 1
        .removeFirst(2) // 1
        .also { println(it) } // 1
        .removeFirst(1) // null
        .removeFirst(1) // null
        .also { println(it) } // null
        .add(2)
        .add(2)
        .also { println(it) } // 2, 2
        .removeFirst(2)
        .also { println(it) } // 2
        .removeFirst(2)
        .also { println(it) } // null

    println(list)
}
