package codesample.leetcode.medium;

import java.util.Arrays;

/**
 * 498. Diagonal Traverse — https://leetcode.com/problems/diagonal-traverse/description/
 */
public class _498_DiagonalTraverse {

    static class Solution {
        public int[] findDiagonalOrder(int[][] matrix) {

            int n = matrix.length;
            // Check for empty matrices
            if (n == 0) {
                return new int[0];
            }

            int row = 0;
            int column = 0;

            boolean topRightDirection = true;

            // The final result array
            int[] result = new int[n * n];
            int r = 0;

            // The uber while loop which will help us iterate over all
            // the elements in the array.
            while (row < n && column < n) {

                // First and foremost, add the current element to
                // the result matrix.
                result[r++] = matrix[row][column];

                // Move along in the current diagonal depending upon
                // the current direction.[i, j] -> [i - 1, j + 1] if
                // going up and [i, j] -> [i + 1][j - 1] if going down.
                int newRow = row + (topRightDirection ? -1 : 1);
                int newColumns = column + (topRightDirection ? 1 : -1);

                // Checking if the next element in the diagonal is within the
                // bounds of the matrix or not. If it's not within the bounds,
                // we have to find the next head.
                if (newRow < 0 || newRow == n || newColumns < 0 || newColumns == n) {

                    if (topRightDirection) {

                        // For an upwards going diagonal having [i, j] as its tail
                        // If [i, j + 1] is within bounds, then it becomes
                        // the next head. Otherwise, the element directly below
                        // i.e. the element [i + 1, j] becomes the next head
                        row += (column == n - 1 ? 1 : 0);
                        column += (column < n - 1 ? 1 : 0);

                    } else {

                        // For a downwards going diagonal having [i, j] as its tail
                        // if [i + 1, j] is within bounds, then it becomes
                        // the next head. Otherwise, the element directly below
                        // i.e. the element [i, j + 1] becomes the next head
                        column += (row == n - 1 ? 1 : 0);
                        row += (row < n - 1 ? 1 : 0);
                    }

                    // Flip the direction
                    topRightDirection = !topRightDirection;

                } else {

                    row = newRow;
                    column = newColumns;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        /*
        Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
         */

        var matrix = new int[][] {new int[]{1,2,3}, new int[]{4,5,6}, new int[]{7,8,9}};

        var res = s.findDiagonalOrder(matrix);
        Arrays.stream(res).forEach(it -> System.out.print(it + ", "));
    }
}
