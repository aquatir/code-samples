package com.codesample.leetcode.easy

/** 1752. Check if Array Is Sorted and Rotated https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
 * Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number
 * of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

Note: An array A rotated by x positions results in an array B of the same length such that A[i] == B[(i+x) % A.length],
where % is the modulo operation. */
fun check(nums: IntArray): Boolean {

    // There may be no more then 1 wrong-ordered elements.
    // e.g. for 3, 4, 5, 1, 2   :  3->4 ok, 4->5 ok, 5 -> 1 first wrong, 1->2 ok, 2->3 ok. Rotation must exist
    // e.g. for 2, 1            :  2 -> 1 first wrong. 1 -> 2 ok. Rotation must exist
    // e.g. for 2, 1, 3, 4      :  2 -> 1 first wrong. 1 -> 3 ok. 3 -> 4 ok 4 -> 1. Second wrong. No rotation exist

    var rotations = 0;
    for (i in 1 until nums.size) {
        if (nums[i] < nums[i - 1]) {
            rotations++
            if (rotations >= 2) {
                return false
            }
        }
    }

    if (nums.first() < nums.last()) {
        rotations++
    }

    return rotations < 2
}

fun main() {
    println(check(intArrayOf(3, 4, 5, 1, 2)))   // expected == true
    println(check(intArrayOf(2, 1, 3, 4)))      // expected == false
    println(check(intArrayOf(1, 2, 3)))         // expected == true
    println(check(intArrayOf(1, 1, 1)))         // expected == true
    println(check(intArrayOf(2, 1)))            // expected == true
    println(check(intArrayOf(6, 10, 6)))        // expected == true
}
