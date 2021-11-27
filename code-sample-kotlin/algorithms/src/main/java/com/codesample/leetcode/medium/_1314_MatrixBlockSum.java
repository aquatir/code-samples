package com.codesample.leetcode.medium;

/**
 * 1314. Matrix Block Sum — https://leetcode.com/problems/matrix-block-sum/
 *
 * Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:
 * i - k <= r <= i + k,
 * j - k <= c <= j + k, and
 * (r, c) is a valid position in the matrix.
 */
public class _1314_MatrixBlockSum {

    // TODO: do same solution myself...
    static class Solution {

        // [[1,2,3],[4,5,6],[7,8,9]]
        // 1    2   3
        // 4    5   6
        // 7    8   9
        // k = 1
        // 12   21  16
        // 27   45  33
        // 24   39  28
        // answer[i][j] == sum of elements mat[r][c] for
        // i - k <= r <= i + k
        // j - k <= c <= j + k
        // (r,c) is a valid position in matrix.
        // e.g. answer[0][0] == sum of elements for
        // -1 <= r <= 1
        // -1 <= c <= 1
        // so.. its a square?
        // what if dp[i][j] will tell us the sum of all elements from
        // dp[0][0] up until dp[i][j] ?
        // so dp[1][1] = mat[0][0] + mat[1][0] + mat[0][1] + mat[1][1]
        // what is dp[1][0] ??
        // dp[1][0] = mat[0][0] + mat[1][0] right?
        // dp[0][1] = mat[0][0] + mat[0][1]

        // dp[1][1] = mat[1][1] + dp[1][0] + dp[0][1] - dp[0][0]
        // dp[2][2] = mat[2][2] + dp[1][2] + dp[2][1] - dp[1][1] ???
        // 1    1   1
        // 1    1   1
        // 1    1   1

        // dp[1][1] = 4
        // dp[1][2] = 6
        // dp[2][1] = 6
        // dp[2][2] ~ 9 = 1 + 6 + 6 - 4 = 9
        // Seems correct

        // question 1: how to we build this thing?
        // question 2: how does it help us solve the problem?

        // e.g. what is answer[1][1] with k = 1?
        // with square matrix above it's dp[2][2] exactly. But it's not always the case right?
        // what is answer[0][0] ? it's dp[1][1]...
        // what is asnwer[0][2] ? it's dp[1][2] - dp[1][0]... how did I find this out?


        public int[][] matrixBlockSum(int[][] mat, int K) {
            int m = mat.length, n = mat[0].length;
            int[][] sum = new int[m + 1][n + 1];

            for (int r = 1; r <= m; r++) {
                for (int c = 1; c <= n; c++) {
                    sum[r][c] = mat[r - 1][c - 1] + sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1];
                }
            }

            int[][] ans = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int r1 = Math.max(0, r - K), c1 = Math.max(0, c - K);
                    int r2 = Math.min(m - 1, r + K), c2 = Math.min(n - 1, c + K);
                    r1++; c1++; r2++; c2++; // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
                    ans[r][c] = sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1];
                }
            }
            return ans;
        }
    }
}
