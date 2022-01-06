package codesample.leetcode.hard;

/**
 * 42. Trapping Rain Water — https://leetcode.com/problems/trapping-rain-water/
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it can trap after raining.
 */
public class _42_TrappingRainWater {
    static class Solution {

        // 4,2,0,3,2,5 n = 6. First n-i-1 = 6-1-1 = 4. Last: n - i - 1 = 6 - 5 - 1 = 0
        // correct result = 9.
        // given    maxToLeft      maxToRight   + water
        // 4        4               5           0
        // 2        4               5           2
        // 0        4               5           4
        // 3        4               5           1
        // 2        4               5           2
        // 5        5               5
        //                          total       9
        public int trap(int[] height) {
            int n = height.length;

            int[] maxToLeft = new int[n];
            int[] maxToRight = new int[n];

            maxToLeft[0] = height[0];
            maxToRight[n-1] = height[n-1];

            for (int i = 1; i < n ; i++) {
                maxToLeft[i] = Math.max(maxToLeft[i-1], height[i]);

                int oposite = n - i - 1;
                maxToRight[oposite] = Math.max(maxToRight[oposite + 1], height[oposite]);
            }

            int totalWater = 0;
            for (int i = 0; i < n; i++) {
                totalWater += Math.min(maxToLeft[i], maxToRight[i]) - height[i];
            }

            return totalWater;

        }
    }
}
