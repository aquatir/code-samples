package codesample.leetcode.easy;

/**
 * 485. Max Consecutive Ones — https://leetcode.com/problems/max-consecutive-ones/
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 */
public class _485_MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int curMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                curMax++;
            } else {
                curMax = 0;
            }
            max = Math.max(max, curMax);
        }

        return max;
    }

    public static void main(String[] args) {
        var s = new _485_MaxConsecutiveOnes();
        System.out.println(s.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1})); // exp 3
        System.out.println(s.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1})); // exp 2
    }
}
