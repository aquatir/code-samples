package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 62. Unique Paths — https://leetcode.com/problems/unique-paths/
 */
public class _62_UniquePaths {


    static class Solution {

        public int uniquePathsRecursive(int m, int n) {
            // m X n grid.
            String key = m + "," + n;
            if (m == 1 && n == 1) {
                return 1;
            }

            int topPaths = m - 1 == 0 ? 0:  uniquePaths(m - 1, n);
            int leftPaths = n - 1 == 0 ? 0 : uniquePaths(m, n - 1);
            return topPaths + leftPaths;
        }

        // HashMap would generally be slower here, but should theoretically take less space for huge m and n
        public int uniquePaths(int m, int n) {
            return uniquePaths(m, n, new int[m + 1][n + 1]);
        }

        int uniquePaths(int m, int n, int[][] memo) {

            if (memo[m][n] != 0) {
                return memo[m][n];
            }
            if (m == 1 && n == 1) {
                return 1;
            }
            if (m == 0 || n == 0) {
                return 0;
            }

            memo[m][n] = uniquePaths(m - 1, n, memo) + uniquePaths(m, n - 1, memo);
            return memo[m][n];
        }

    }
}
