package com.codesample.leetcode.easy

/**
 * 1534. Count Good Triplets. https://leetcode.com/problems/count-good-triplets/
 *
 * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
 *
 * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 *
 * Where |x| denotes the absolute value of x.
 * Return the number of good triplets.
 *
 * Constraints:
 * 3 <= arr.length <= 100 // small array size. Can brute force
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */
fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {

    fun matched(i: Int, j: Int, k: Int): Boolean {
        return Math.abs(arr[i] - arr[j]) <= a
                && Math.abs(arr[j] - arr[k]) <= b
                && Math.abs(arr[i] - arr[k]) <= c
    }

    var matched = 0
    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            if (Math.abs(arr[i] - arr[j]) > a) {
                continue
            }
            for (k in j + 1 until arr.size) {
                if (matched(i, j, k)) {
                    matched++
                }
            }
        }
    }

    return matched
}

fun main() {
    println(countGoodTriplets(intArrayOf(3, 0, 1, 1, 9, 7), 7, 2, 3))
    println(countGoodTriplets(intArrayOf(1, 1, 2, 2, 3), 0, 0, 1))
}
