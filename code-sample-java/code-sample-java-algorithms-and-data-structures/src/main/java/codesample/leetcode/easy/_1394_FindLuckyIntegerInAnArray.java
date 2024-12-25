package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 1394. Find Lucky Integer in an Array — https://leetcode.com/problems/find-lucky-integer-in-an-array/description/
 */
public class _1394_FindLuckyIntegerInAnArray {
    public int findLucky(int[] arr) {
        var freq = new HashMap<Integer, Integer>();

        for (var num: arr) {
            freq.put(num, 1 + freq.getOrDefault(num, 0));
        }

        var ans = -1;

        for (var entry: freq.entrySet()) {
            if (entry.getKey() == entry.getValue()) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }
}
