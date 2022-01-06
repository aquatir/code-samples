package codesample.leetcode.medium;

/**
 * 413. Arithmetic Slices — https://leetcode.com/problems/arithmetic-slices/
 *
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two
 * consecutive elements is the same.
 *
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * A subarray is a contiguous subsequence of the array.
 */
public class _413_Arithmetic_Slices {
    static class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;

            // given k: 0 <= k <= n
            // what state can we know at k?
            // - some number of subarrays which are already complete and are arithemtic
            // - some number of subarrays which are not yet arithemtic but could be (e.g. 1/2 elements)
            // - some number of subarrays which are arithemic but could also be increased in size
            // to create new arithmetic subarrays

            // we can theoretically look only at last 3 elements:
            // if 3 elements form arithemic sequence
            //  - its either the first one
            //  - or it's a continuation of a previous one
            //      - in this case the forths number will increase a total number of arrays by 2
            //      1 for forth and 1 for extra 3
            //      - the 5th number will increate a total by additional 3
            //      1 for 5, 1 for extra forht and 1 for extra 3.
            //      - so we get a growing sum

            int total = 0;
            int currentConsecutiveSum = 0;

            for (int i = 2; i < n; i++) {
                if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                    currentConsecutiveSum++;
                    total += currentConsecutiveSum;
                } else {
                    currentConsecutiveSum = 0;
                }
            }

            return total;
        }
    }
}
