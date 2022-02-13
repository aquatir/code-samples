package codesample.leetcode.medium;

import java.util.Arrays;

/**
 * 16. 3Sum Closest — https://leetcode.com/problems/3sum-closest/
 * <p>
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 */
public class _16_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {

        // we sort the array to make finding differents easier

        Arrays.sort(nums); // need to make fiding number easier n * log(n) with qsort
        // we fix one value

        // internal array would have: value, left, right, target
        // when sum value+left+right.
        // if exactly target => return result
        // if > than target sum: move right pointer. Calculate absolute difference.
        // if < than target sum: move left pointers. Calculate absolute difference.
        // if at any point absolute difference becomes larger -> there is no point to continue iteratig
        // (^ how true if that?)
        // at the end of one cycle return absolute difference. If it is zero -> early exist,
        // if not, try again with different fixed number

        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int diffWithTarget = target - nums[i] - nums[left] - nums[right];
                if (diffWithTarget == 0) {
                    return target;
                }

                if (Math.abs(diffWithTarget) < Math.abs(smallestDifference)) {
                    smallestDifference = diffWithTarget;
                }

                if (diffWithTarget > 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return target - smallestDifference;
    }
}
