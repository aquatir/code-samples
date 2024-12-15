package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2248. Intersection of Multiple Arrays — https://leetcode.com/problems/intersection-of-multiple-arrays/description/
 */
public class _2248_IntersectionOfMultipleArrays {
    public List<Integer> intersection(int[][] nums) {

        // num to freq
        var freq = new HashMap<Integer, Integer>();
        for (int[] arr: nums) {
            for (int num: arr) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }

        // Answer must be sorted, use PQ to do so
        var minQueue = new PriorityQueue<Integer>(
            (val1, val2) -> Integer.compare(val1, val2)
        );
        for (var entry: freq.entrySet()) {
            if (entry.getValue() == nums.length) {
                minQueue.add(entry.getKey());
            }
        }

        var ans = new ArrayList<Integer>();
        while (!minQueue.isEmpty()) {
            ans.add(minQueue.poll());
        }
        return ans;
    }
}
