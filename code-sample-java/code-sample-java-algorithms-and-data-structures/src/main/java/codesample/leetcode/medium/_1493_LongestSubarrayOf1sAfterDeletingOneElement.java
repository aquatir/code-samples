package codesample.leetcode.medium;

/**
 * 1493. Longest Subarray of 1's After Deleting One Element — https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element
 * <p>
 * This is a simpler version of {@link _1004_MaxConsecutiveOnesIII} where number of skips is arbitrary
 */
public class _1493_LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        // approach 1: sliding window
        //   keep the [left, right] window in a way that there is not more than a single zero inside of it
        //   when encountering non-zero => skip
        //   when encountering zero => remove elements from left until the zero is removed

        var max = 0;
        var left = 0;

        var zeroFound = false;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            if (num == 0) {
                if (!zeroFound) {
                    zeroFound = true;
                } else {
                    // one zero is present, have to shring the list
                    while (left < right && nums[left] != 0) {
                        left++;
                    }
                    // at this point nums[left] == 0, but nums[right] is also a null, so skip left
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
        }

        return max - 1;
    }
}
