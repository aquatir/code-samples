package codesample.leetcode.medium;

/**
 * https://leetcode.com/problems/number-of-ways-to-split-array/description/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * nums contains a valid split at index i if the following are true:
 *
 * The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
 * There is at least one element to the right of i. That is, 0 <= i < n - 1.
 * Return the number of valid splits in nums.
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 */
public class _2270_NumberOfWaysToSplitArray {
    public int waysToSplitArray(int[] nums) {
        // build prefix sum
        // iterate over i and compare left and right parts according to condition
        // count number of successes
        // use long for prefixSum array to not overflow

        var n = nums.length;
        var prefixSum = new long[n];
        var answer = 0;
        prefixSum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        // not including last elements, because at least 1 elements must be according to condition
        for (int i = 0; i < n - 1; i++) {
            var leftSum = prefixSum[i];
            var rightSum = prefixSum[n - 1] - prefixSum[i];

            if (leftSum >= rightSum) {
                answer++;
            }
        }

        return answer;
    }

    public int waysToSplitArrayEfficient(int[] nums) {
        int ans = 0;
        long leftSection = 0;
        long total = 0;

        for (int num: nums) {
            total += num;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            leftSection += nums[i];
            long rightSection = total - leftSection;
            if (leftSection >= rightSection) {
                ans++;
            }
        }

        return ans;
    }
}
