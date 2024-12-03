package codesample.leetcode.medium;

/**
 *
 * https://leetcode.com/problems/subarray-product-less-than-k/description/
 *
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

 * Example 1:
 *
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 */
public class _713_SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if (k <= 1) {
            return 0;
        }

        int curProduct = 1;
        int ans = 0;
        int left = 0;

        for (int right=0; right < nums.length; right++) {
            // increase window
            curProduct *= nums[right];

            // make sure window is valid
            while (curProduct >= k) {
                curProduct /= nums[left];
                left++;
            }

            // update answer

            // number of subarrays ending on right
            // this works, because right always move during iteration
            ans += (right - left) + 1;
        }

        return ans;
    }

}
