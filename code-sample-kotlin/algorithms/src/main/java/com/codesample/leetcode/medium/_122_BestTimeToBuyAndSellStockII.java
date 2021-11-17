package com.codesample.leetcode.medium;

/**
 * 122. Best Time to Buy and Sell Stock II — https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 */
public class _122_BestTimeToBuyAndSellStockII {
    static class Solution {

        // e.g. for [7,1,5,3,6,4]
        // *******
        // *
        // *****
        // ***
        // ******
        // ****

        // you gain profit each time you go up the slope. So e.g. from day 2 to day 3 you climb 4 start => profit += 4
        // then again from day 4 to day 5 => climb 3 => profit += 3 for a total of 7.

        // another situation: multiple growing days, e.g. [1, 4, 8]
        // *
        // ****
        // ********
        // if price grows two days in a row you could see for yourself that it doesn't matter when you buy on day 1 on day 2,
        // the profit would still be the same => 7. This is a further confirmation that "just calculate diff on climb" works.
        //

        public int maxProfit(int[] prices) {

            int totalProfit = 0;
            int prevPrice = prices[0];

            for (int i = 1; i < prices.length; i++) {
                int cur = prices[i];
                if (cur > prevPrice) {
                    totalProfit += cur - prevPrice;
                }
                prevPrice = cur;
            }

            return totalProfit;
        }
    }
}
