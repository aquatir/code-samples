package com.codesample.algo

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import selectionSort

class SelectionSortTests {

    @Test
    fun testSelectionSortEmpty() {
        val array = arrayOf<Int>()
        selectionSort(array)

        assertArrayEquals(arrayOf<Int>(), array)
    }

    @Test
    fun testSelectionSortOne() {
        val array = arrayOf(1)
        selectionSort(array)

        assertArrayEquals(arrayOf(1), array)
    }

    @Test
    fun testSelectionSortMultiple() {
        val array = arrayOf(3, 1, 2)
        selectionSort(array)

        assertArrayEquals(arrayOf(1, 2, 3), array)
    }

    @Test
    fun testSelectionSortMultipleWithRepeat() {
        val array = arrayOf(3, 1, 1, 2, 3, 2)
        selectionSort(array)

        assertArrayEquals(arrayOf(1, 1, 2, 2, 3, 3), array)
    }
}
