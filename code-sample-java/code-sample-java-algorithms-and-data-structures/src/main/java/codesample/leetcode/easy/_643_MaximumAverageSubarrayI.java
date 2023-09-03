package codesample.leetcode.easy;

/**
 * 643. Maximum Average Subarray I — https://leetcode.com/problems/maximum-average-subarray-i/
 */
public class _643_MaximumAverageSubarrayI {

    // keep a window of K elements
    // when moving window, subtract leftmost and add rightmost elements, recalculate the max
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        var curSum = 0;

        for (int i = 0; i <= right; i++) {
            curSum += nums[i];
        }

        double max = (curSum * 1.00) / k;
        left++;
        right++;

        while (right != nums.length) {

            curSum -= nums[left - 1];
            curSum += nums[right];

            max = Math.max(max, (curSum * 1.00) / k);

            left++;
            right++;
        }

        return max;
    }
}
