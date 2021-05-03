package com.codesample.leetcode.medium

import java.util.*

/**
 * 621. Task Scheduler https://leetcode.com/problems/task-scheduler/
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 * Example 2:
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 *
 * Constraints:
 * 1 <= task.length <= 10^4
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */

class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        // time total can be dominated by same-element tasks
        // attempt 1:
        // 1. count frequency
        // 2. each time-period take the ele with highest frequency which can be executed

        // attempts 2:
        // each step launch 2 tasks:
        // - where next element in takes into scheduling
        // - where next element is NOT taken into scheduling

        // attempts 3:
        // 1. count frequency
        // 2. if there are 'n' elements with freq > 0 ->
        // 2.1. subtract min of their frequency from each.
        // 2.2. increase total time by (n * min freq)  // this denotes scheduling without empty slots
        // 3. if there are 'k < n' elements with freq > 0 ->
        // 3.1. subtract min of their frequency from each
        // 3.2. increase total time by (k * min freq) + time for WAITING?!


        // copy-paste solution: https://leetcode.com/problems/task-scheduler/discuss/1157511/java-O(n)-time-and-O(1)-space
        val count = IntArray(26)
        var maxFreq = 0
        var maxFreqElementCount = 0

        for (ch in tasks) {
            val index = ch - 'A'
            ++count[index]
            if (count[index] == maxFreq) {
                ++maxFreqElementCount
            } else if (count[index] > maxFreq) {
                maxFreq = count[index]
                maxFreqElementCount = 1
            }
        }

        // count the max free slots, taking into account extra max frequency elements
        val emptySlots = (maxFreq - 1) * (n - (maxFreqElementCount - 1))  // after filling in max frequency elements

        // case 1: rest of the non max frequency elements does not have elements to fill in the spaces we will have idle spots
        // case 2: we have enough or more than enough elements to fill in the slots, we just slot them in without needing any idle
        val idle = Math.max(0, emptySlots - (tasks.size - maxFreqElementCount * maxFreq))

        return tasks.size + idle

    }
}

fun main() {
    val s = Solution();
    println(s.leastInterval(charArrayOf('A', 'B', 'A', 'B'), 2))
}
