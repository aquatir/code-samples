package codesample.leetcode.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 436. Find Right Interval — https://leetcode.com/problems/find-right-interval/
 * <p>
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
 */
public class _436_FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] result = new int[n];

        // Approach 1: binary search
        // extract + sort by startIndex (n*log(n))
        // for each interval, binary search for endIndex <= startIndex (log(n))
        // if found => take it, if not => take one on the right if there is any.

        // Approach 2: brute force
        // for each value do the min or equals search in (n * n)

        // Approach 3: two max heaps: for starts and for ends
        // for all the intervals:
        //   pick an element from end maxheap.
        //   skip over elements from start maxheap, where their start >= that ends
        //   when can no longer skip => update with lowest index found + return the element back into maxheap

        PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(n, (i1, i2) -> Integer.compare(intervals[i2][0], intervals[i1][0]));
        PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(n, (i1, i2) -> Integer.compare(intervals[i2][1], intervals[i1][1]));
        for (int i = 0; i < n; i++) {
            maxStartHeap.offer(i);
            maxEndHeap.offer(i);
        }

        for (int i = 0; i < n; i++) {
            var maxEndIndex = maxEndHeap.poll();
            result[maxEndIndex] = -1;
            if (intervals[maxStartHeap.peek()][0] >= intervals[maxEndIndex][1]) {
                var topStart = maxStartHeap.poll();
                while (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()][0] >= intervals[maxEndIndex][1]) {
                    topStart = maxStartHeap.poll();
                }
                result[maxEndIndex] = topStart;
                maxStartHeap.add(topStart);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var s = new _436_FindRightInterval();
        var res1 = s.findRightInterval(new int[][]{new int[]{1, 2}, new int[]{5, 6}});
        for (var v : res1) {
            System.out.print(v + ", "); // expected [1,-1]
        }

        System.out.println();
        var res2 = s.findRightInterval(new int[][]{new int[]{3,4}, new int[]{2,3}, new int[]{1,2}});
        for (var v : res2) {
            System.out.print(v + ", "); // expected [-1, 0, 1]
        }
    }
}
