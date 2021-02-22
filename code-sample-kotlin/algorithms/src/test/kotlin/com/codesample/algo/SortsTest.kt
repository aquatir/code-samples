package com.codesample.algo

import insertionSort
import mergeSort
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import selectionSort
import shellSort

class SortsTest {

    private fun testSortImpl(sortImpl: (Array<Int>) -> Unit) {

        assertAll(
            Executable { // 0 elements
                val array = arrayOf<Int>()
                sortImpl.invoke(array)
                assertArrayEquals(arrayOf<Int>(), array, "0 elements failed")
            },
            Executable { // 1 element
                val array = arrayOf(1)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1), array, "1 element failed")
            },
            Executable { // 3 elements
                val array = arrayOf(3, 1, 2)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3), array, "3 element failed")
            },
            Executable { // 6 elements + repeats
                val array = arrayOf(3, 1, 1, 2, 3, 2)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 1, 2, 2, 3, 3), array, "6 elements + repeats failed")
            },
            Executable { // sorted in different way
                val array = arrayOf(6, 5, 4, 3, 2, 1)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6), array, "sorted in different way failed")
            },
            Executable { // already sorted
                val array = arrayOf(1, 2, 3, 4, 5, 6)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6), array, "already sorted failed")
            },
            Executable { // 13 elements
                val array = arrayOf(1, 2, 3, 4, 5, 6, 8, 1, 3, 12, 32, 12, 0, 43)
                sortImpl.invoke(array)

                assertArrayEquals(arrayOf(0, 1, 1, 2, 3, 3, 4, 5, 6, 8, 12, 12, 32, 43), array, "13 elements failed")
            }
        )
    }

    @Test
    fun testSelectionSort() = testSortImpl(::selectionSort)

    @Test
    fun testInsertionSort() = testSortImpl(::insertionSort)

    @Test
    fun testShellSort() = testSortImpl(::shellSort)

    @Test
    fun testMergeSort() = testSortImpl(::mergeSort)
}
