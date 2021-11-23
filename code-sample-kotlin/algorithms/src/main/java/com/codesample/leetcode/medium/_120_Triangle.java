package com.codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _120_Triangle {
    static class Solution {
        public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
            // [2],[3,4],[6,5,7],[4,1,8,3]
            // 2
            // 3 4
            // 6 5 7
            // 4 1 8 3

            // 2
            // 5  6
            // 11 10 13
            // 14 11 18 16

            int n = triangle.size();
            if (n == 1) {
                return triangle.get(0).get(0);
            }

            // 2
            // 3 4
            // 6 5 7
            // 4 1 8 3

            // 2
            // 5  6
            // 11 10 13
            // 14 11 18 16
            for (int i = 1; i < n; i++) {
                List<Integer> curRow = triangle.get(i);
                List<Integer> prevRow = triangle.get(i-1);
                for (int j = 0; j < triangle.get(i).size(); j++) {
                    curRow.set(j, curRow.get(j) + Math.min(
                            prevRow.get(Math.max(0, j - 1)),
                            prevRow.get(Math.min(prevRow.size() - 1, j))
                    ));
                }
            }

            List<Integer> lastRaw = triangle.get(n-1);
            int min = lastRaw.get(lastRaw.size() - 1);

            for (int i = 0; i < lastRaw.size(); i++) {
                if (lastRaw.get(i) < min) {
                    min = lastRaw.get(i);
                }
            }

            return min;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        // [2],[3,4],[6,5,7],[4,1,8,3]
        // [2],[3,4],[6,5,7],[4,1,8,3]
        // 2
        // 3 4
        // 6 5 7
        // 4 1 8 3

        // 2
        // 5  6
        // 11 10 13
        // 14 11 18 16

        var list = new ArrayList<>(List.of(new ArrayList<>(List.of(2)), new ArrayList<>(List.of(3,4)), new ArrayList<>(List.of(6,5,7)), new ArrayList<>(List.of(4,1,8,3))));
        System.out.println(s.minimumTotal(list)); // expected = 11
    }
}
