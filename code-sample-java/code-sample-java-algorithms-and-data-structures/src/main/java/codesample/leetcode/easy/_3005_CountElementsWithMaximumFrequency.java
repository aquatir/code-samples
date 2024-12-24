package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 3005. Count Elements With Maximum Frequency — https://leetcode.com/problems/count-elements-with-maximum-frequency/description/
 */
public class _3005_CountElementsWithMaximumFrequency {
    public int maxFrequencyElements(int[] nums) {

        // number to frequency
        var freq = new HashMap<Integer, Integer>();
        for (var num: nums) {
            freq.put(num, 1 + freq.getOrDefault(num, 0));
        }

        var ans = 0;
        var curMaxFreq = 0;

        for (var value: freq.values()) {
            if (value > curMaxFreq) {
                curMaxFreq = value;
                ans = value;
            } else if (value == curMaxFreq) {
                ans += value;
            }
        }
        return ans;
    }
}
