package codesample.leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin —https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */
public class _973_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (point1, point2) -> Double.compare(
                len(points[point2]),
                len(points[point1])
            )
        );

        for (int i = 0; i < k; i++) {
            maxHeap.offer(i);
        }

        for (int i = k; i < points.length; i++) {
            if (len(points[maxHeap.peek()]) > len(points[i])) {
                maxHeap.poll();
                maxHeap.offer(i);
            } else {
                // skip
            }
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            int index = maxHeap.poll();
            result[i] = points[index];
        }

        return result;
    }

    public double len(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }
}
