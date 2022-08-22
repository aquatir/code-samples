package codesample.leetcode.easy;

/**
 * 136. Single Number — https://leetcode.com/problems/single-number/
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
public class _136_SingleNumber {
    public int singleNumber(int[] nums) {
        int xor = nums[0];

        for (int i = 1; i < nums.length; i++) {
            xor = xor ^ nums[i];
        }

        return xor;
    }
}
