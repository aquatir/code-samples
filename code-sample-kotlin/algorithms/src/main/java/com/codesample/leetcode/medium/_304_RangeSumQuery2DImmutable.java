package com.codesample.leetcode.medium;

/**
 * 304. Range Sum Query 2D - Immutable — https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class _304_RangeSumQuery2DImmutable {
    class NumMatrix {

        private int[][] dp;

        public NumMatrix(int[][] matrix) {

            if (matrix.length == 0 || matrix[0].length == 0) return;

            int m = matrix.length;
            int n = matrix[0].length;
            this.dp = new int[m + 1][n + 1];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1]
                    - dp[row1][col2 + 1]
                    - dp[row2 + 1][col1]
                    + dp[row1][col1];
        }
    }

    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
}
