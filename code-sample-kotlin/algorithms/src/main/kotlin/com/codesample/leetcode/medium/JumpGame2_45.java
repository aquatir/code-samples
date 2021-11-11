package com.codesample.leetcode.medium;

/**
 * 45. Jump Game II — https://leetcode.com/problems/jump-game-ii/
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 */
public class JumpGame2_45 {

    // Take forward approach from JumpGame (first one) -> instead of saving boolean save min number of steps.
    public int jump(int[] nums) {
        int n = nums.length;
        int[] stepsToReach = new int[n];

        // prepare min steps to reach. First line is non needed because java will set it to zero automatically.
        stepsToReach[0] = 0;
        for (int i = 1; i < n; i++) {
            stepsToReach[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= nums[i]; j++) {
                int indexAfterJump = i + j;
                if (indexAfterJump < n) {
                    // See if it is faster to reach target node from current in comparison to current way to this node.
                    stepsToReach[indexAfterJump] = Math.min(
                            stepsToReach[indexAfterJump],
                            stepsToReach[i] + 1
                    );
                }
            }
        }

        return stepsToReach[n - 1];
    }
}
