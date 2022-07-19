package codesample.leetcode.easy;

/**
 * 645. Set Mismatch — https://leetcode.com/problems/set-mismatch/
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some
 * error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number
 * and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 *
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 *
 * Example 2:
 * Input: nums = [1,1]
 * Output: [1,2]
 */
public class _645_SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int element = nums[i];
            int expectedElementIndex = element - 1;
            if (element != nums[expectedElementIndex]) {
                int tmp = nums[expectedElementIndex];
                nums[expectedElementIndex] = element;
                nums[i] = tmp;
            }
            else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            int element = nums[j];
            if (element - 1 != j) {
                return new int[] {element, j + 1};
            }
        }


        return new int[] { -1, -1};
    }
}
