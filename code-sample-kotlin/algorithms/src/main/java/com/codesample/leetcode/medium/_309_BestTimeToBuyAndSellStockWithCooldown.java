package com.codesample.leetcode.medium;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown — https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell
 * <p>
 * one share of the stock multiple times) with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 */
public class _309_BestTimeToBuyAndSellStockWithCooldown {
    static class Solution {

        // space-optimized dp
        public int maxProfit(int[] prices) {

            int n = prices.length;

            if (n == 1) return 0;
            if (n == 2) return Math.max(0, prices[1] - prices[0]);

            // we could be it 3 state
            // stateRest    -> we end up here if we did nothing OR sold previous step
            // stateBuy     -> we end up here if we were resting or BROUGHT or did nothing
            // stateSell    -> we end up here if we SOLD stock last time.

            // if we update those values with max each time, the best between stateRest and stateSell
            // should yield us result.

            // stateRest[i] = curStateRest
            // stateBuy[i] = curStateBuy
            // stateSell[i] = curStateSell

            // stateRest[i-1] = prevStateRest
            // stateBuy[i-1]  = prevStateBuy
            // stateSell[i-1] = prevStateSell

            int prevStateRest = 0;
            int prevStateBuy = -prices[0];
            int prevStateSell = Integer.MIN_VALUE;

            int curStateRest = 0, curStateBuy = 0, curStateSell = 0;


            for (int i = 1; i < n; i++) {

                // we get to rest by either resting again or selling
                curStateRest = Math.max(prevStateRest, prevStateSell);

                // we get to buy by either not buying again or buying after rest
                curStateBuy = Math.max(prevStateBuy, prevStateRest - prices[i]);

                // we end up at sell buy selling thus == profit
                curStateSell = prevStateBuy + prices[i];

                prevStateRest = curStateRest;
                prevStateBuy = curStateBuy;
                prevStateSell = curStateSell;
            }

            return Math.max(curStateRest, curStateSell);
        }


        public int maxProfitArrays(int[] prices) {

            int n = prices.length;

            if (n == 1) return 0;
            if (n == 2) return Math.max(0, prices[1] - prices[0]);

            // we could be it 3 state
            // stateRest    -> we end up here if we did nothing OR sold previous step
            // stateBuy     -> we end up here if we were resting or BROUGHT or did nothing
            // stateSell    -> we end up here if we SOLD stock last time.

            // if we update those values with max each time, the best between stateRest and stateSell
            // should yield us result.

            int[] stateRest = new int[n];
            int[] stateBuy = new int[n];
            int[] stateSell = new int[n];

            stateRest[0] = 0;                   // did nothing
            stateBuy[0] = -prices[0];           // brought stock
            stateSell[0] = Integer.MIN_VALUE;   // sell nothing.

            // stateRest[i] = curStateRest
            // stateBuy[i] = curStateBuy
            // stateSell[i] = curStateSell

            // stateRest[i-1] = prevStateRest
            // stateBuy[i-1]  = prevStateBuy
            // stateSell[i-1] = prevStateSell

            for (int i = 1; i < n; i++) {

                // we get to rest by either resting again or selling
                stateRest[i] = Math.max(stateRest[i - 1], stateSell[i - 1]);

                // we get to buy by either not buying again or buying after rest
                stateBuy[i] = Math.max(stateBuy[i - 1], stateRest[i - 1] - prices[i]);

                // we end up at sell buy selling thus == profit
                stateSell[i] = stateBuy[i - 1] + prices[i];
            }

            return Math.max(stateRest[n - 1], stateSell[n - 1]);
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        System.out.println(s.maxProfit(new int[]{1, 4, 1, 2, 6, 8})); // expected = 3 - 1 + 7 = 9
    }
}
