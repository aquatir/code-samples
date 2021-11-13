package com.codesample.leetcode.medium;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 * <p>
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 */
public class DeleteAndEarn_740 {
    static class Solution {
        public int deleteAndEarn(int[] nums) {

            // first sol -> radix sort values -> then check how much will we delete.
            // second sol -> same but hashMap.
            // do the same with simple array;
            int n = 10001; // bacause num[i] <= 10^4.
            int[] maxEarnings = new int[n];
            for (int num : nums) {
                maxEarnings[num] += num;
            }

            int takePrev = 0;
            int skipPrev = 0;

            for (int i = 0; i < n; i++) {
                int takei = skipPrev + maxEarnings[i];
                int skipi = Math.max(takePrev, skipPrev);

                takePrev = takei;
                skipPrev = skipi;
            }

            return Math.max(takePrev, skipPrev);
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.deleteAndEarn(new int[]{3, 4, 2}));
        System.out.println(s.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }
}
