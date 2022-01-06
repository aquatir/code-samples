package codesample.leetcode.medium;

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

            int prevBuy = - prices[0];
            int prevSell = 0;

            int curBuy = 0, curSell = 0;

            for (int i = 1; i < n; i++) {
                curBuy = Math.max(prevBuy, prevSell - prices[i]);
                curSell = Math.max(prevSell, prevBuy + prices[i] - fee);

                prevBuy = curBuy;
                prevSell = curSell;
            }

            return Math.max(
                    curBuy,
                    curSell
            );
        }
    }
}
