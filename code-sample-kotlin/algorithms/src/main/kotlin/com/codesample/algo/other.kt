package com.codesample.algo

import swap
import kotlin.random.Random


fun <T> shuffle(array: Array<T>) {
    for (i in array.indices) {
        val rnd = Random.nextInt(i + 1)
        swap(array, i, rnd)
    }
}
