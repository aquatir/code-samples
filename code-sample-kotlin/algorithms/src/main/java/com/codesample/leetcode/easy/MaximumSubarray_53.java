package com.codesample.leetcode.easy;

/**
 * 53. Maximum Subarray — https://leetcode.com/problems/maximum-subarray/
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * A subarray is a contiguous part of an array.
 */
public class MaximumSubarray_53 {
    static class Solution {
        public int maxSubArray(int[] nums) {

            // on each array entry:
            // current max = either previous max + this element OR this element
            // at the end we just find the max value of those partial sums

            int n = nums.length;
            int[] sum = new int[n];
            sum[0] = nums[0];

            for (int i = 1; i < n; i++) {
                sum[i] = Math.max(
                        sum[i - 1] + nums[i],
                        nums[i]
                );
            }

            int maxResult = nums[0];
            for (int i = 0; i < n; i++) {
                if (sum[i] > maxResult) {
                    maxResult = sum[i];
                }
            }

            return maxResult;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));      // expected 6
        System.out.println(sol.maxSubArray(new int[]{1}));                                  // expected 1
        System.out.println(sol.maxSubArray(new int[]{5, 4, -1, 7, 8}));                     // expected 23
    }
}
