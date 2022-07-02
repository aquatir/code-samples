package codesample.leetcode.medium;

/**
 * 518. Coin Change 2 https://leetcode.com/problems/coin-change-2/
 * <p>
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 */
public class _518_CoinChange2 {

    public int change(int amount, int[] coins) {
        Integer[][] cache = new Integer[amount + 1][coins.length + 1];
        return knapsack(amount, 0, coins, cache);
    }

    private int knapsack(int amount, int i, int[] coins, Integer[][] cache) {
        // got to the bottom
        if (amount == 0) {
            return 1;
        }
        // wrong amount or not more coins
        if (amount < 0 || i == coins.length) {
            return 0;
        }

        if(cache[amount][i] != null) return cache[amount][i];

        int count = 0;
        // try to add the same coin
        count += knapsack(amount - coins[i], i, coins, cache);
        // try to add bigger coi
        count += knapsack(amount, i + 1, coins, cache);

        cache[amount][i] = count;

        return count;
    }

    public static void main(String[] args) {
        var s = new _518_CoinChange2();
        System.out.println(s.change(5, new int[]{1, 2, 5})); // exp: 4
    }
}
