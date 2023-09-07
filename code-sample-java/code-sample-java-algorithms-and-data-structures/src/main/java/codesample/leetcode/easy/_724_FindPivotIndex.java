package codesample.leetcode.easy;

/**
 * 724. Find Pivot Index — https://leetcode.com/problems/find-pivot-index/description/
 */
public class _724_FindPivotIndex {
    class Solution {

        // approach 1: two arrays: pre-calculate and check later
//        public int pivotIndex(int[] nums) {
//            int n = nums.length;
//            int[] sumFromLeft = new int[n];
//            int[] sumFromRight = new int[n];
//
//            int runningSum = 0;
//
//            // sum from left + skip leftmost element
//            for (int i = 1; i < n; i++) {
//                runningSum += nums[i - 1];
//                sumFromLeft[i] = runningSum;
//            }
//
//            // sum from right + skip rightmost element
//            runningSum = 0;
//            for (int i = n - 2; i >= 0; i--) {
//                runningSum += nums[i + 1];
//                sumFromRight[i] = runningSum;
//            }
//
//            for (int i = 0; i < n; i++) {
//                if (sumFromLeft[i] == sumFromRight[i]) {
//                    return i;
//                }
//            }
//
//            return -1;
//        }

        // approach 2: only precalculate the total sum of elements.
        // then grow "left" and calculate "right" sum on flight
        public int pivotIndex(int[] nums) {
            int totalSum = 0;
            int leftSum = 0;

            for (int x : nums) {
                totalSum += x;
            }

            // leftSum start with 0. For each element, check if condition holds, then
            // For the pivotal elements, the sum of elements to the left and to the right
            // is the same
            // the sum to left is leftSum
            // the sum to right is all the rest of elements which will be totalSum - leftSum - num[i]
            for (int i = 0; i < nums.length; ++i) {
                if (leftSum == totalSum - leftSum - nums[i]) {
                    return i;
                }
                leftSum += nums[i];
            }
            return -1;
        }
    }
}
