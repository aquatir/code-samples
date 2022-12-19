package codesample.leetcode.easy;

/**
 * 1480. Running Sum of 1d Array — https://leetcode.com/problems/running-sum-of-1d-array/description/
 * <p>
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * <p>
 * Return the running sum of nums.
 */
public class _1480_RunningSumOf1dArray {
    class Solution {
//        public int[] runningSum(int[] nums) {
//            var result = new int[nums.length];
//            var currentSum = 0;
//
//            for (int i = 0; i < nums.length; i++) {
//                var num = nums[i];
//                currentSum += num;
//                result[i] = currentSum;
//            }
//
//            return result;
//        }

        public int[] runningSum(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] = nums[i] + nums[i-1];
            }

            return nums;
        }
    }
}
