package com.codesample.leetcode.medium;

/**
 * 516. Longest Palindromic Subsequence — https://leetcode.com/problems/longest-palindromic-subsequence/
 * <p>
 * Given a string s, find the longest palindromic subsequence's length in s.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 */
public class _516_LongestPalindromicSubsequence {
    static public class Solution {
        public int longestPalindromeSubseq(String s) {

            int n = s.length();
            int[][] dp = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[0][s.length() - 1];
        }
    }
}
