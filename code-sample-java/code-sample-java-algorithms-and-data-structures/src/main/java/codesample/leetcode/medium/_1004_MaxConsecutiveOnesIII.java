package codesample.leetcode.medium;

/**
 * 1004. Max Consecutive Ones III — https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip
 * at most k 0's.
 */
public class _1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int windowStart = 0;
        int max = 0;
        int maxOnesCount = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 1) {
                maxOnesCount++;
            }

            // maybe shrink
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (nums[windowStart] == 1) {
                    maxOnesCount--;
                }
                windowStart++;
            }

            // max
            max = Math.max(max, windowEnd - windowStart + 1);

        }

        return max;
    }

    public static void main(String[] args) {
        var s = new _1004_MaxConsecutiveOnesIII();
        System.out.println(s.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)); // exp: 6
        System.out.println(s.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3)); // exp: 10
    }
}
