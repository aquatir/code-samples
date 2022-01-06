package codesample.leetcode.medium;

/**
 * 64. Minimum Path Sum — https://leetcode.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the
 * sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class _64_MinimumPathSum {
    static class Solution {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[][] mins = new int[m][n];
            mins[0][0] = grid[0][0];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int topOrMax = i - 1 < 0 ? Integer.MAX_VALUE : mins[i-1][j];
                    int leftOrMax = j - 1 < 0 ? Integer.MAX_VALUE : mins[i][j-1];

                    mins[i][j] = grid[i][j] + Math.min(
                            topOrMax,
                            leftOrMax
                    );
                }
            }

            return mins[m - 1][n - 1];
        }
    }
}
