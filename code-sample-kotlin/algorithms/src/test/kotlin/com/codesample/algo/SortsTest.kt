package com.codesample.algo

import insertionSort
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import selectionSort

class SortsTest {

    private fun testSortImpl(sortImpl: (Array<Int>) -> Unit) {
        assertAll(
            Executable { // 0 elements
                val array = arrayOf<Int>()
                sortImpl.invoke(array)
                assertArrayEquals(arrayOf<Int>(), array)
            },
            Executable { // 1 element
                val array = arrayOf(1)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1), array)
            },
            Executable { // 3 elements
                val array = arrayOf(3, 1, 2)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3), array)
            },
            Executable { // 6 elements + repeats
                val array = arrayOf(3, 1, 1, 2, 3, 2)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 1, 2, 2, 3, 3), array)
            },
            Executable { // sorted in different way
                val array = arrayOf(6, 5, 4, 3, 2, 1)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6), array)
            },
            Executable { // already sorted
                val array = arrayOf(1, 2, 3, 4, 5, 6)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6), array)
            }
        )
    }

    @Test
    fun testSelectionSort() = testSortImpl(::selectionSort)

    @Test
    fun testInsertionSort() = testSortImpl(::insertionSort)
}
