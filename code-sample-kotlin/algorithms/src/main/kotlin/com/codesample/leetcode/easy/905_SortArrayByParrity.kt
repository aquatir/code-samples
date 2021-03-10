package com.codesample.leetcode.easy

/**
 * 905. Sort Array By Parity  https://leetcode.com/problems/sort-array-by-parity/
 *
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all
 * the odd elements of A.
 * You may return any answer array that satisfies this condition.
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
fun sortArrayByParity(A: IntArray): IntArray {
    val first = ArrayList<Int>(A.size)
    val second = ArrayList<Int>(A.size)

    for (element in A) {
        if (element.rem(2) == 0) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return (first + second).toIntArray()
}
