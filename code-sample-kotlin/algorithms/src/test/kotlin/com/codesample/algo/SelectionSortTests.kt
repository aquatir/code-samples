package com.codesample.algo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import selectionSort

class SelectionSortTests {

    @Test
    fun testSelectionSortEmpty() {
        val list = listOf<Int>()
        val sorted = selectionSort(list)

        assertEquals(listOf<Int>(), sorted)
    }

    @Test
    fun testSelectionSortOne() {
        val list = listOf(1)
        val sorted = selectionSort(list)

        assertEquals(listOf(1), sorted)
    }

    @Test
    fun testSelectionSortMultiple() {
        val list = listOf(3, 1, 2)
        val sorted = selectionSort(list)

        assertEquals(listOf(1, 2, 3), sorted)
    }

    @Test
    fun testSelectionSortMultipleWithRepeat() {
        val list = listOf(3, 1, 1, 2, 3, 2)
        val sorted = selectionSort(list)

        assertEquals(listOf(1, 1, 2, 2, 3, 3), sorted)
    }
}
