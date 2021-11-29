package com.codesample.leetcode.medium;

/**
 * 63. Unique Paths II — https://leetcode.com/problems/unique-paths-ii/
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 */
public class _63_UniquePathsII {

    static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            if (obstacleGrid[0][0] == 1) {
                return 0;
            }

            obstacleGrid[0][0] = 1;
            for (int i = 1; i < m; i++) {
                obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
            }
            for (int i = 1; i < n; i++) {
                obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    } else {
                        obstacleGrid[i][j] = 0;
                    }
                }
            }

            return obstacleGrid[m - 1][n - 1];
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));                // ex = 1
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));       // ex = 2
        System.out.println(s.uniquePathsWithObstacles(new int[][]{{0,0,0,0}, {0, 1, 0, 0}, {0,0,0,0}, {0,0,1,0}, {0,0,0,0}})); // ex = 7
    }
}
