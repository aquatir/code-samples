package com.codesample.leetcode.medium;

public class _931_MinimumFallingPathSum {

    static class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;

            if (n == 1) {
                return matrix[0][0];
            }

            // on each step we replace the current value in matrix with lowest possible sum.

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    int cur = matrix[i][j];

                    // for first elements on new row -> only check current and next element
                    if (j == 0) {
                        matrix[i][j] = cur + Math.min(
                                matrix[i-1][j],
                                matrix[i-1][j+1]
                        );

                        // for last element on new row -> only check current and prev element
                    } else if (j == n-1) {
                        matrix[i][j] = cur + Math.min(
                                matrix[i-1][j],
                                matrix[i-1][j - 1]
                        );

                        // for elements in the middle -> check both left, right and current indexes.
                    } else {
                        matrix[i][j] = cur + Math.min(
                                matrix[i-1][j - 1],
                                Math.min(
                                        matrix[i-1][j],
                                        matrix[i-1][j + 1]
                                )
                        );
                    }
                }
            }

            // the lowest number in last row is our answer. We could theoretically track it in a loop above but nah.
            int min = matrix[n-1][0];
            for (int i = 0; i < n; i++) {
                if (matrix[n-1][i] < min) {
                    min = matrix[n-1][i];
                }
            }

            return min;
        }
    }
}
