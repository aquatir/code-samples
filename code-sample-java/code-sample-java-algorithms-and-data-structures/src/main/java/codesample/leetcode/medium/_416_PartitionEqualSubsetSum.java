package codesample.leetcode.medium;

/**
 * 416. Partition Equal Subset Sum — https://leetcode.com/problems/partition-equal-subset-sum/
 * <p>
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two
 * subsets such that the sum of elements in both subsets is equal.
 */
public class _416_PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        // [1, 5, 15, 5] => yes. [11] and [1,5,5]
        // [1,2,3,5] => no.

        // each number can be either in first subset of other subset
        // if sub is odd => we can't partition 100%
        // if it IS possible, the total sum of all elements divided by 2 =>
        //  sum of one subset
        // the algo: either take the first element to get the sum of skip it.

        var sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        } else {
            Boolean[][] dp = new Boolean[nums.length][sum / 2 + 1];
            return tryPartition(nums, sum / 2, 0, dp);
        }
    }


    // how do we brute force it?
    public boolean tryPartition(int[] nums, int targetSum, int currentIndex, Boolean[][] dp) {
        if (targetSum == 0) {
            return true;
        }

        if (nums.length == 0 || currentIndex >= nums.length) {
            return false;
        }

        // only calculate if first time, else return DP result
        if (dp[currentIndex][targetSum] == null) {

            // take current number, update DP
            if (nums[currentIndex] <= targetSum) {
                if (tryPartition(nums, targetSum - nums[currentIndex], currentIndex + 1, dp)) {
                    dp[currentIndex][targetSum] = true;
                    return true;
                }
            }

            // skip current number, update DP
            // we can only reach this branch if we checked "take number" and failed already
            dp[currentIndex][targetSum] = tryPartition(nums, targetSum, currentIndex + 1, dp);
        }

        return dp[currentIndex][targetSum];
    }
}
