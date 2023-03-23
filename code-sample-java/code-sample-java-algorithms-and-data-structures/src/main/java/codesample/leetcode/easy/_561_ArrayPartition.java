package codesample.leetcode.easy;

import java.util.Arrays;

public class _561_ArrayPartition {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        var sum = 0;

        for (int i = 0; i < nums.length; i = i + 2) {
            sum += nums[i];
        }

        return sum;
    }
}
