package codesample.leetcode.medium;

/**
 * 487. Max Consecutive Ones II — https://leetcode.com/problems/max-consecutive-ones-ii/description/
 */
public class _487_MaxConsecutiveOnesII {

    // this approach works, but takes O(n) space (n*2 in reality)
    // you can do the same with sliding window which is O(1) and still be a O(n) time
    class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            // build two arrays: ones-to-the-right of i and ones-to-the-left-of-i
            // for each i try to see what happens if you flip :thinking:

            int n = nums.length;
            int[] onesToLeft = new int[n];
            int[] onesToRight = new int[n];

            // a bit of a hack with only 2 numbers because I'm probably doing something wrong with 2 arrays...
            if (n == 2) {
                int sum = nums[0] + nums[1];
                if (sum == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }

            int onesOnLeftSide = nums[0];
            for (int i = 1; i < n; i++) {
                onesToLeft[i] = onesOnLeftSide;
                if (nums[i] == 1) {
                    onesOnLeftSide++;
                } else {
                    onesOnLeftSide = 0;
                }
            }

            int onesOnRightSide = nums[n-1];
            for (int i = n-2; i > 0; i--) {
                onesToRight[i] = onesOnRightSide;
                if (nums[i] == 1) {
                    onesOnRightSide++;
                } else {
                    onesOnRightSide = 0;
                }
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                int possibleMax = onesToLeft[i] + onesToRight[i] + 1;
                max = Math.max(max, possibleMax);
            }

            return max;
        }
    }
}
