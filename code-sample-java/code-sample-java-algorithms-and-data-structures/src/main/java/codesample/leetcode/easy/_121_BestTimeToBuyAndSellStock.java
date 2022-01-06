package codesample.leetcode.easy;

/**
 * 121. Best Time to Buy and Sell Stock — https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future
 * to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class _121_BestTimeToBuyAndSellStock {
    static class Solution {
        public int maxProfit(int[] prices) {
            int n = prices.length;

            int bestBuyPrice = prices[0];
            int profit = 0;

            for (int i = 1; i < n; i++) {
                profit = Math.max(profit, prices[i] - bestBuyPrice);
                bestBuyPrice = Math.min(bestBuyPrice, prices[i]);
            }

            return Math.max(profit, 0);
        }
    }
}
