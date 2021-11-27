package com.codesample.leetcode.medium;

/**
 * 62. Unique Paths — https://leetcode.com/problems/unique-paths/
 */
public class _62_UniquePaths {


    static class Solution {

        public int uniquePathsRecursive(int m, int n) {
            // m X n grid.

            if (m == 1 && n == 1) {
                return 1;
            }

            int topPaths = m - 1 == 0 ? 0:  uniquePaths(m - 1, n);
            int leftPaths = n - 1 == 0 ? 0 : uniquePaths(m, n - 1);
            return topPaths + leftPaths;
        }

        public int uniquePaths(int m, int n) {
            return uniquePaths(m, n, new int[m+1][n+1]);
        }

        int uniquePaths(int m, int n, int[][] memo) {
            if (m == 1 && n == 1) {
                return 1;
            }

            int topPaths = 0;
            if (m - 1 == 0) {
                topPaths = 0;
            } else {
                if (memo[m - 1][n] != 0) {
                    topPaths = memo[m-1][n];
                } else {
                    memo[m-1][n] = uniquePaths(m - 1, n, memo);
                    topPaths = memo[m-1][n];
                }
            }


            int leftPaths = 0;
            if (n - 1 == 0) {
                leftPaths = 0;
            } else {
                if (memo[m][n-1] != 0) {
                    leftPaths = memo[m][n-1];
                } else {
                    memo[m][n-1] = uniquePaths(m, n-1, memo);
                    leftPaths = memo[m][n-1];
                }
            }

            memo[m][n] = topPaths + leftPaths;
            return memo[m][n];
        }

    }
}
