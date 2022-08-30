package codesample.leetcode.medium;

/**
 * 260. Single Number III — https://leetcode.com/problems/single-number-iii/
 *
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 */
public class _260_SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        // all numbers appear twice, except two which appear once
        // we can xor all
        // we will than find the rightmost 1 => that the first different number
        //   we will than separately sum all numbers with the 1 at that position
        //   and all numbers without 1 at that position.
        // this will nulify all numbers and give us two correct numbers too

        int xors = 0;
        for (int i = 0; i < nums.length; i++) {
            xors = xors ^ nums[i];
        }

        // now find the lowest 1
        int bit = 1;
        while ((bit & xors) == 0) {
            bit = bit << 1;
        }

        int zeroAtBit = 0;
        int oneAtBit = 0;

        for (int num : nums) {
            if ((num & bit) == 0) { // the bit is zero
                zeroAtBit = zeroAtBit ^ num;
            } else { // the bit is one
                oneAtBit = oneAtBit ^ num;
            }
        }
        return new int[] { zeroAtBit, oneAtBit };
    }
}
