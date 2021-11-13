package com.codesample.leetcode.medium;

/**
 * 55. Jump Game — https://leetcode.com/problems/jump-game/
 *
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the
 * array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 */
public class JumpGame_55 {

    // DP from backward. Assume we can reach -> go backward and remember if we truly can.
    public boolean canJump(int[] nums) {
        boolean[] canBeInPoint = new boolean[nums.length];
        int n = nums.length;
        canBeInPoint[n - 1] = true;

        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j <= nums[i]; j++) {
                if (canBeInPoint[i + j]) {
                    canBeInPoint[i] = true;
                    break; // enough to end up at single point once.
                }
            }
        }

        /*

        for(int i=n-2;i>=0;i--)
        {
            for(int j=0;j<=nums[i] && i+j<n;j++)
            {
                if(jump[i+j]==true)
                {
                    jump[i]=true;
                    break;
                }
            }
        }

        return jump[0];
         */

        return canBeInPoint[0];
    }

    /**
     * Set max reachable node as max = 0 first.
     * Then on each iteration i check:
     * if i (current position) > max then we can traverse any more -> no solution
     * if less or equal -> we may be able to move further, so set max of (nums[i] + i) and max.
     */
    public boolean canJumpGreedy(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            } else {
                max = Math.max(nums[i] + i, max);
            }
        }
        return true;
    }

    /**
     * when we end up at some point, it doesn't matter how we ended up there -> we would
     * search over all the possible solutions anyway.
     * we only need to end up at certain point once.
     */
    public boolean canJumpForwardMemoization(int[] nums) {
        boolean[] wasInPoint = new boolean[nums.length];
        findSolutionFrom(0, nums, wasInPoint);

        return wasInPoint[nums.length - 1];

    }

    private void findSolutionFrom(int fromIndex, int[] nums, boolean[] wasInPoint) {
        int maxIndex = nums.length;
        int curPointValue = nums[fromIndex];

        for (int i = 0; i <= curPointValue; i++) {
            int afterJumpIndex = fromIndex + i;
            if (afterJumpIndex < maxIndex && !wasInPoint[afterJumpIndex]) {
                wasInPoint[afterJumpIndex] = true;
                findSolutionFrom(afterJumpIndex, nums, wasInPoint);
            }
        }
    }

    public static void main(String[] args) {

    }
}
