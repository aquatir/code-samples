package com.codesample.leetcode.easy;

/**
 * 1137. N-th Tribonacci Number — https://leetcode.com/problems/n-th-tribonacci-number/
 * <p>
 * The Tribonacci sequence Tn is defined as follows:
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 */
public class _1137_NthTribonacciNumber {
    public int tribonacci(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        if (n == 3) return 2;

        int prevPrevPrev = 1;
        int prevPrev = 1;
        int prev = 2;
        int cur = 0;

        for (int i = 4; i <= n; i++) {
            cur = prevPrevPrev + prevPrev + prev;
            prevPrevPrev = prevPrev;
            prevPrev = prev;
            prev = cur;
        }

        return cur;
    }

    public int tribonacciDp(int n) {
        int dp[] = {0, 1, 1};
        for (int i = 3; i <= n; ++i)
            dp[i % 3] = dp[0] + dp[1] + dp[2];
        return dp[n % 3];
    }
}
