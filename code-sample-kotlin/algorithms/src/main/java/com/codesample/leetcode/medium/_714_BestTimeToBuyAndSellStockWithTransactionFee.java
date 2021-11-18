package com.codesample.leetcode.medium;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee — https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee
 * representing a transaction fee.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the
 * transaction fee for each transaction.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class _714_BestTimeToBuyAndSellStockWithTransactionFee {
    static class Solution {

        // prices = [1,3,2,8,4,9], fee = 2 result = 8
        //
        // *
        // ***
        // **
        // ********  + 8 - 1 - 2 = 7
        // ****
        // ********** + 9 - 4 - 2  = 3
        // total = 8

        // [1,3,7,5,10,3]       possible proffit at
        // *
        // ***
        // *******
        // *****
        // **********
        // ***

        // buy, sell, not-buy, hold
        public int maxProfit(int[] prices, int fee) {

            int n = prices.length;
            int buyCur = 0, sellCur = 0, skipCur = 0, holdCur = 0;

            int buyPrev = -prices[0];
            int sellPrev = Integer.MIN_VALUE;
            int skipPrev = 0;
            int holdPrev = -prices[0];

            for (int i = 1; i < n; i++) {
                buyCur = Math.max(sellPrev, skipPrev) - prices[i];
                skipCur = Math.max(sellPrev, skipPrev);
                holdCur = Math.max(buyPrev, holdPrev);
                sellCur = Math.max(buyPrev, holdPrev) + prices[i] - fee;

                buyPrev = buyCur;
                skipPrev = skipCur;
                holdPrev = holdCur;
                sellPrev = sellCur;
            }

            return Math.max(
                    sellCur,
                    skipCur
            );
        }
    }
}
