package codesample.leetcode.easy;

/**
 * 1672. Richest Customer Wealth — https://leetcode.com/problems/richest-customer-wealth/description/
 */
public class _1672_RichestCustomerWealth {

    class Solution {
        public int maximumWealth(int[][] accounts) {
            int curMax = 0;

            for (int[] banks : accounts) {
                int curSum = 0;
                for (int money : banks) {
                    curSum += money;
                }
                curMax = Math.max(curMax, curSum);
            }

            return curMax;
        }
    }
}
