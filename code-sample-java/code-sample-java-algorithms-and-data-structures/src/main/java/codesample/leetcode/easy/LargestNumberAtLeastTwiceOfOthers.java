package codesample.leetcode.easy;

/**
 * Largest Number At Least Twice of Others — https://leetcode.com/submissions/detail/892837812/?from=explore&item_id=1147
 */
public class LargestNumberAtLeastTwiceOfOthers {
    class Solution {
        public int dominantIndex(int[] nums) {
            int curMaxIndex = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= nums[curMaxIndex]) {
                    curMaxIndex = i;
                }
            }

            var maxValue = nums[curMaxIndex];
            for (int i = 0; i < nums.length; i++) {
                if (i == curMaxIndex) {
                    continue;
                } else {
                    if (maxValue < nums[i] * 2) {
                        return -1;
                    }
                }
            }

            return curMaxIndex;
        }
    }
}
