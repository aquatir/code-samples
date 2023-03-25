package codesample.leetcode.medium;

/**
 * 209. Minimum Size Subarray Sum — https://leetcode.com/problems/minimum-size-subarray-sum/description/
 */
public class _209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        // O(n) — sliding window
        int left = 0;
        int curSum = 0;

        int minLength = nums.length + 1;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            while (curSum >= target) {
                minLength = Math.min(minLength, i - left + 1);
                curSum -= nums[left];
                left++;
            }
        }

        if (minLength == nums.length + 1) {
            return 0;
        } else {
            return minLength;
        }
    }

    public static void main(String[] args) {
        var s = new _209_MinimumSizeSubarraySum();

        System.out.println(s.minSubArrayLen(15, new int[] {1,2,3,4,5})); // expected: 15
        System.out.println(s.minSubArrayLen(4, new int[] {1,4,4})); //expected: 1
    }

}
