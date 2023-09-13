package codesample.leetcode.medium;

/**
 * 2352. Equal Row and Column Pairs — https://leetcode.com/problems/equal-row-and-column-pairs/description
 */
public class _2352_EqualRowAndColumnPairs {
    public int equalPairs(int[][] grid) {
        var n = grid.length; // the grid is n x n

        // approach 1: n^3 brute-force
        // There are only 2n rows and cols. Some of them will be equal
        // what the brute-force will look like?
        // extract one row
        // compare is to all cols
        // extract next row
        // compare it so all cols

        // approach 2?
        // need to compare cols efficiently (or not?)
        // rows are easy, just int[i] => array at row [i]
        //  Let's construct cols first -> could be done by transporting the matrix
        // will this be more efficient? Currently, we will break from comparison ASAP if the columns do not match

        int total = 0;
        for (int[] row : grid) {
            // compare with ALL cols
            // fix the row
            for (int i = 0; i < n; i++) {
                // actual element comparison
                var result = true;
                for (int j = 0; j < n; j++) {
                    if (grid[j][i] != row[j]) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    total++;
                }
            }
        }
        return total;
    }
}
