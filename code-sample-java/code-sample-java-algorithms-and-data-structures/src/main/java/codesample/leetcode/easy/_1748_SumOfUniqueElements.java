package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 1748. Sum of Unique Elements — https://leetcode.com/problems/sum-of-unique-elements/description/
 */
public class _1748_SumOfUniqueElements {
    public int sumOfUnique(int[] nums) {
        // num to count
        var freq = new HashMap<Integer, Integer>();
        for (var num: nums) {
            freq.put(num, 1 + freq.getOrDefault(num, 0));
        }
        var ans = 0;
        for (var entry: freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans += entry.getKey();
            }
        }

        return ans;
    }
}

