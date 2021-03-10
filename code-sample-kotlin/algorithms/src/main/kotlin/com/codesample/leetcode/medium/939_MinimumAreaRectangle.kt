package com.codesample.leetcode.medium

/**
 * 939. Minimum Area Rectangle https://leetcode.com/problems/minimum-area-rectangle/
 *
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides
 * parallel to the x and y axes. If there isn't any rectangle, return 0.
 *
 * Example 1:
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 * Note:
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 */
fun minAreaRect(points: Array<IntArray>): Int {
    val lookupSet = mutableSetOf<Pair<Int, Int>>() // save all points we seen in set
    var curMin = Integer.MAX_VALUE
    for ( (x1, y1) in points) {  // for each point let's try other point for rectangle in lookupSet
        for ( (x2, y2) in lookupSet) { // iterate over lookup set
            if (x1 == x2 && y1 == y2) { // do nothing if we found the same point in lookup set on this iteration
                continue
            }
            if (Pair(x1, y2) in lookupSet) { // for all other points -> try to see if we have a rectangle
                if (Pair(x2, y1) in lookupSet) {
                    curMin = Math.min(curMin, Math.abs(x2-x1) * Math.abs(y2-y1))
                }
            }
        }
        lookupSet.add(Pair(x1, y1)) // !!! don't forget to add element into lookup set
    }
    return if (curMin == Integer.MAX_VALUE) 0 else curMin
}

fun main() {
    println(minAreaRect(arrayOf(
        intArrayOf(1,1),
        intArrayOf(1,3),
        intArrayOf(3,1),
        intArrayOf(3,3),
        intArrayOf(4,1),
        intArrayOf(4,3),
    )))
}

