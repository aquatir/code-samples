package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2352. Equal Row and Column Pairs — https://leetcode.com/problems/equal-row-and-column-pairs/description
 */
public class _2352_EqualRowAndColumnPairs {

    public int equalPairs(int[][] grid) {
        var n = grid.length; // the grid is n x n
        var rows = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            var row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(grid[i][j]);
                row.append(',');
            }
            var key = row.toString();
            rows.put(key, rows.getOrDefault(key, 0) + 1);
        }

        var res = 0;
        for (int i = 0; i < n; i++) {
            var row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(grid[j][i]);
                row.append(',');
            }
            var key = row.toString();
            if (rows.containsKey(key)) {
                res += rows.get(key);
            }
        }

        return res;
    }

    // Count only rows. Then add columns as soon as they are encountered
    // Alternative solution would be to put something other than a List<Integer> as a key to speed up the lookup process
    public int equalPairsListAsKey(int[][] grid) {
        var n = grid.length; // the grid is n x n
        var rows = new HashMap<List<Integer>, Integer>();

        for (int i = 0; i < n; i++) {
            var row = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                row.add(grid[i][j]);
            }
            rows.put(row, rows.getOrDefault(row, 0) + 1);
        }

        var res = 0;
        for (int i = 0; i < n; i++) {
            var row = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                row.add(grid[j][i]);
            }
            if (rows.containsKey(row)) {
                res += rows.get(row);
            }
        }

        return res;
    }

    // counting how many of each row and column we encountered
    // then count duplicates
    public int equalPairsPrecount(int[][] grid) {
        var n = grid.length; // the grid is n x n
        var cols = new HashMap<List<Integer>, Integer>();
        var rows = new HashMap<List<Integer>, Integer>();

        for (int i = 0; i < n; i++) {
            var row = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                row.add(grid[i][j]);
            }
            rows.put(row, rows.getOrDefault(row, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            var row = new ArrayList<Integer>(n);
            for (int j = 0; j < n; j++) {
                row.add(grid[j][i]);
            }
            cols.put(row, cols.getOrDefault(row, 0) + 1);
        }

        var res = 0;
        for (var entry: cols.entrySet()) {
            if (rows.containsKey(entry.getKey())) {
                res += entry.getValue() * rows.get(entry.getKey());
            }
        }

        return res;
    }


    // brute force by comparing all rows with all columns
    public int equalPairsBruteforce(int[][] grid) {
        var n = grid.length; // the grid is n x n

        // approach 1: n^3 brute-force
        // There are only 2n rows and cols. Some of them will be equal
        // what the brute-force will look like?
        // extract one row
        // compare is to all cols
        // extract next row
        // compare it so all cols

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
