package com.codesample.leetcode.medium;

/**
 * 376. Wiggle Subsequence — https://leetcode.com/problems/wiggle-subsequence/
 * <p>
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 * <p>
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 */
public class _376_WiggleSubsequence {

    class Solution {

        // possible in O(n)...
        public int wiggleMaxLength(int[] nums) {

            if (nums.length == 0) return 0;

            // next number if either bigger or smaller than previous
            // count both possible ways.
            // ups[i] or downs[i] == max length so far.
            // if cur element is equal to previous => nothing is found.
            int[] ups = new int[nums.length];
            int[] downs = new int[nums.length];

            ups[0] = 1;
            downs[0] = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    ups[i] = downs[i - 1] + 1;
                    downs[i] = downs[i - 1];
                } else if (nums[i] < nums[i - 1]) {
                    downs[i] = ups[i - 1] + 1;
                    ups[i] = ups[i - 1];
                } else {
                    downs[i] = downs[i - 1];
                    ups[i] = ups[i - 1];
                }
            }

            return Math.max(
                    downs[nums.length - 1],
                    ups[nums.length - 1]
            );
        }
    }
}
