package com.codesample.leetcode.medium

/** 907. Sum of Subarray Minimums Given an array of integers arr, find the sum of min(b), where b ranges over every
 * (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * */
fun sumSubarrayMins1(arr: IntArray): Int {

    // brute force solution -> split into arrays, calculate each subarray minimum -> sum minimums
    val modulo = 1_000_000_000L + 7L
    var sum = 0L

    fun arraysOfElements(arr: IntArray, numberOfElements: Int): List<IntArray> {
        var left = 0
        var right = numberOfElements - 1

        val listOfArrays = mutableListOf<IntArray>()

        while (right < arr.size) {
            val anotherArray = mutableListOf<Int>()
            for (i in left..right) {
                anotherArray.add(arr[i])
            }
            listOfArrays.add(anotherArray.toIntArray())
            left++
            right++
        }
        return listOfArrays
    }

    for (numOfElementsInArray in 1..arr.size) {
        val arrays = arraysOfElements(arr, numOfElementsInArray)
        sum += arrays.map { it.min() ?: 0 }.sum()
    }

    return (sum % modulo).toInt()
}

fun sumSubarrayMins2(arr: IntArray): Int {

    // slightly smarted solution -> do not create extra arrays, just return their indexes
    // -> calculate each subarray minimum -> sum minimums
    val modulo = 1_000_000_000L + 7L
    var sum = 0L

    fun arraysOfElements(arr: IntArray, numberOfElements: Int): List<Pair<Int, Int>> {
        var left = 0
        var right = numberOfElements - 1

        val listOfArrays = mutableListOf<Pair<Int, Int>>()

        while (right < arr.size) {
            listOfArrays.add(Pair(left, right))
            left++
            right++
        }
        return listOfArrays
    }

    fun minOfElements(arr: IntArray, left: Int, right: Int): Int {
        var min = Int.MAX_VALUE
        for (i in left..right) {
            if (min > arr[i]) {
                min = arr[i]
            }
        }
        return min
    }

    for (numOfElementsInArray in 1..arr.size) {
        val arrays = arraysOfElements(arr, numOfElementsInArray)
        sum += arrays.map { minOfElements(arr, it.first, it.second) }.sum()
    }

    return (sum % modulo).toInt()
}

fun sumSubarrayMins3(arr: IntArray): Int {

    // slightly smarted solution -> do not return indexes, just calculate sum of mins
    // -> sum minimums
    val modulo = 1_000_000_000L + 7L
    var sum = 0L

    fun minOfElements(arr: IntArray, left: Int, right: Int): Int {
        var min = Int.MAX_VALUE
        for (i in left..right) {
            if (min > arr[i]) {
                min = arr[i]
            }
        }
        return min
    }

    fun arraysOfElements(arr: IntArray, numberOfElements: Int): Int {
        var left = 0
        var right = numberOfElements - 1

        var sumOfMinSubArrays = 0

        while (right < arr.size) {
            val minOfSum = minOfElements(arr, left, right)
            sumOfMinSubArrays += minOfSum
            left++
            right++
        }
        return sumOfMinSubArrays
    }


    for (numOfElementsInArray in 1..arr.size) {
        sum += arraysOfElements(arr, numOfElementsInArray)
    }

    return (sum % modulo).toInt()
}

//fun sumSubarrayMins4(arr: IntArray): Int {
//
//    // let's try remembering what where the minimums between elements
//    val modulo = 1_000_000_000L + 7L
//    var sum = 0L
//
//    fun minOfElements(arr: IntArray, left: Int, right: Int): Int {
//        var min = Int.MAX_VALUE
//        for (i in left..right) {
//            if (min > arr[i]) {
//                min = arr[i]
//            }
//        }
//        return min
//    }
//
//    fun arraysOfElements(arr: IntArray, numberOfElements: Int): Int {
//        var left = 0
//        var right = numberOfElements - 1
//
//        var sumOfMinSubArrays = 0
//
//        while (right < arr.size) {
//            val minOfSum = minOfElements(arr, left, right)
//            sumOfMinSubArrays += minOfSum
//            left++
//            right++
//        }
//        return sumOfMinSubArrays
//    }
//
//
//    for (numOfElementsInArray in 1..arr.size) {
//        sum += arraysOfElements(arr, numOfElementsInArray)
//    }
//
//    return (sum % modulo).toInt()
//}

fun main() {
    println(sumSubarrayMins3(intArrayOf(3, 1, 2, 4)))           // expected: 17
    println(sumSubarrayMins3(intArrayOf(11, 81, 94, 43, 3)))    // expected: 444
}
