package codesample.leetcode;

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return s.substring(0, 1);
        }

        // if dp[i][j] == true -> String between i and j is a palindrom
        // if dp[i][j] == false -> not a palindrom
        // dp[i][j] is a palindrom if
        //     dp[i+1][j-1] is also a palindrom and S_i = S_j

        boolean[][] dp = new boolean[n][n];

        // j > i always.
        int pStartIndex = 0;
        int maxLen = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (j - i >= 2) {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    pStartIndex = i;
                }

            }
        }

        return s.substring(pStartIndex, pStartIndex + maxLen);
    }
}
