package codesample.leetcode.easy;

import java.util.Arrays;

/**
 * 1051. Height Checker — https://leetcode.com/problems/height-checker/description/
 */
public class _1051_HeightChecker {

    class Solution {
        public int heightChecker(int[] heights) {
            // how many moves to make heights sorter?

            var n = heights.length;
            var copy = new int[n];
            for (int i = 0; i < n; i++) {
                copy[i] = heights[i];
            }

            // n * log(n), but max length is 100, so doesn't matter
            Arrays.sort(copy);

            int counter = 0;
            for (int i = 0; i < n; i++) {
                if (heights[i] != copy[i]) {
                    counter++;
                }
            }
            return counter;
        }
    }


}
