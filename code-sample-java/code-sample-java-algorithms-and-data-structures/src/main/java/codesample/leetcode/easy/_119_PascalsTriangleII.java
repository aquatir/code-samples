package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 119. Pascal's Triangle II — https://leetcode.com/problems/pascals-triangle-ii/
 * <p>
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 */
public class _119_PascalsTriangleII {
    static class Solution {

        // 0 1 2 3 <- index
        //
        // 1
        // 1 1
        // 1 2 1
        // 1 3 3 1
        // 1 4 6 4
        //
        // test cases: 0 (ok), 1 (ok), 3 (ok)
        // build triangle from left to right
        public List<Integer> getRow(int rowIndex) {
            List<Integer> prev = new ArrayList<>();

            prev.add(1);
            if (rowIndex == 0) {
                return prev;
            }

            for (int i = 1; i <= rowIndex; i++) {
                List<Integer> cur = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 | j == i) {
                        cur.add(1);
                    } else {
                        cur.add(prev.get(j - 1) + prev.get(j));
                    }
                }
                prev = cur;
            }

            return prev;
        }

        public List<Integer> getRowOneArray(int rowIndex) {

            int[] result = new int[rowIndex + 1];
            result[0] = 1;

            for (int i = 1; i < rowIndex + 1; i++) {
                for (int j = i; j >= 1; j--) {
                    result[j] += result[j - 1];
                }
            }
            return Arrays.stream(result).boxed().collect(Collectors.toList());
        }
    }
}
