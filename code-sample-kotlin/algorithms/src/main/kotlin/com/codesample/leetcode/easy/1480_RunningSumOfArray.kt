package com.codesample.leetcode.easy

/** 1480. Running Sum of 1d Array https://leetcode.com/problems/running-sum-of-1d-array/
 *
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * Return the running sum of nums.
 * */
fun runningSum(nums: IntArray): IntArray {
    for (i in 1 until nums.size) {
        nums[i] = nums[i] + nums[i - 1]
    }
    return nums
}


fun main() {
    println(runningSum(intArrayOf(1, 2, 3, 4)).map { it })
    println(runningSum(intArrayOf(1, 1, 1, 1, 1)).map { it })
    println(runningSum(intArrayOf(3, 1, 2, 10, 1)).map { it })
}
