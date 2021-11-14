package com.codesample.leetcode.easy;

import java.util.Arrays;

/**
 * 1005. Maximize Sum Of Array After K Negations — https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
 *
 * Given an integer array nums and an integer k, modify the array in the following way:
 *  choose an index i and replace nums[i] with -nums[i].
 * You should apply this process exactly k times. You may choose the same index i multiple times.
 * Return the largest possible sum of the array after modifying it in this way.
 *
 * 1 <= nums.length <= 10^4
 * -100 <= nums[i] <= 100
 * 1 <= k <= 10^4
 */
public class MaximizeSumOfArrayAfterKNegations_1005 {
    static class Solution {
        public int largestSumAfterKNegations(int[] nums, int k) {

            // frist sort numbers.
            // while we have k and negative number -> flip negative from lowest
            // when k is out -> sum the rest
            // when k is available, but no more negative numbers ->
            //  if k == odd -> subtract one positive number then sum the rest
            //  if k == even -> do nothing


            // could be updated to radix sort with slightly increased memory because -100 <= nums[i] <= 100
            Arrays.sort(nums);

            // negate numbers if possible
            int i = 0;
            while (nums[i] < 0 && i < nums.length) {
                if (k != 0) {
                    k--;
                    nums[i] = - nums[i];
                }
                i++;
            }

            // if we still have k after processing all negative values we
            // would only do something depending on k being odd or even.
            // if k is odd      => we simply sum everything, nothing more we can do
            // if k is even     => we have to find the smalled by modulo number and subtract it.
            // Notice that this number would have always been added to sum first, so we subtract it twice.

            int totalSum = 0;
            int minByModulo = nums[0];
            for (i = 0; i < nums.length; i++) {
                totalSum += nums[i];
                minByModulo = Math.min(minByModulo, Math.abs(nums[i]));
            }

            if (k % 2 == 0) {
                return totalSum;
            } else {
                return totalSum - minByModulo * 2;
            }
        }
    }

    public static void main(String[] args) {
        final var sol = new Solution();
        System.out.println(sol.largestSumAfterKNegations(new int[]{-8,3,-5,-3,-5,-2}, 6));
        // expected 22 -> final array: 8 (flip), 3, 5 (flip), 3 (flip), 5 (flip), -2 (flip twice)

    }
}
