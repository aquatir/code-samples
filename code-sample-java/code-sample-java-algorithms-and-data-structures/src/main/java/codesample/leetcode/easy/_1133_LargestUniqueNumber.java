package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 1133. Largest Unique Number — https://leetcode.com/problems/largest-unique-number/description/
 */
public class _1133_LargestUniqueNumber {

    public int largestUniqueNumberFreqMap(int[] nums) {
        var freq = new HashMap<Integer, Integer>();
        for (var num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        var max = -1;
        for (var entry: freq.entrySet()) {
            if (entry.getValue() > 1) {
                continue;
            } else {
                max = Math.max(max, entry.getKey());
            }
        }

        return max;
    }

    public int largestUniqueTwoSets(int[] nums) {
        var seen = new HashSet<Integer>();
        var possible = new HashSet<Integer>();
        for (var num: nums) {
            if (seen.contains(num)) {
                possible.remove(num);
            } else {
                seen.add(num);
                possible.add(num);
            }
        }

        var max = -1;
        for (var num: possible) {
            max = Math.max(max, num);
        }

        return max;
    }
    public int largestUniqueNumberPqAndSet(int[] nums) {
        var set = new HashSet<Integer>();
        var pq = new PriorityQueue<Integer>();
        for (var num: nums) {
            if (set.contains(num)) {
                pq.remove(num);
                continue;
            }
            pq.add(num);
            set.add(num);
        }

        if (pq.isEmpty()) {
            return -1;
        } else {
            return pq.poll();
        }
    }
}
