package codesample.leetcode.medium;

/**
 * 918. Maximum Sum Circular Subarray — https://leetcode.com/problems/maximum-sum-circular-subarray/
 * <p>
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of
 * nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i],
 * nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 */
public class _918_MaximumSumCircularSubarray {
    static class Solution {
        public int maxSubarraySumCircular(int[] nums) {

            int n = nums.length;

            int min = nums[0];
            int max = nums[0];
            int prevMin = nums[0];
            int prevMax = nums[0];

            int sum = nums[0];

            for (int i = 1; i < n; i++) {
                sum += nums[i];

                prevMin = Math.min(nums[i], prevMin + nums[i]);
                min = Math.min(min, prevMin);

                prevMax = Math.max(nums[i], prevMax + nums[i]);
                max = Math.max(max, prevMax);
            }

            return sum == min ? max : Math.max(max, sum - min);
        }
    }

    public static void main(String[] args) {
        final var sol = new Solution();
        System.out.println(sol.maxSubarraySumCircular(new int[]{5, -3, 5}));               // expected = 10
        System.out.println(sol.maxSubarraySumCircular(new int[]{8, -1, -3, 8, -6, -9, 2, 4})); // expected = 18
    }
}
