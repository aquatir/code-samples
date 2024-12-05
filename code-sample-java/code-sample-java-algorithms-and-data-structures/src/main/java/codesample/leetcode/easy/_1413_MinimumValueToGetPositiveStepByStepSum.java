package codesample.leetcode.easy;

/**
 * 1413. Minimum Value to Get Positive Step by Step Sum
 * https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/description/
 *
 */
public class _1413_MinimumValueToGetPositiveStepByStepSum {

    public int minStartValue(int[] nums) {
        var prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        var minimumPrefixSum = 101;

        for (int i = 0; i < prefixSum.length; i++) {
            minimumPrefixSum = Math.min(minimumPrefixSum, prefixSum[i]);
        }

        if (minimumPrefixSum > 0) {
            return 1;
        } else {
            return Math.abs(minimumPrefixSum) + 1;
        }
    }

    public int minStartValueNoExtraMemory(int[] nums) {
        var prefixSum = 0;
        var minimumPrefixSum = 101;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            minimumPrefixSum = Math.min(minimumPrefixSum, prefixSum);
        }

        if (minimumPrefixSum > 0) {
            return 1;
        } else {
            return Math.abs(minimumPrefixSum) + 1;
        }
    }
}
