package com.codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class _118_Pascals_Triangle {
    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>(numRows);

            List<Integer> prev = new ArrayList<>(1);
            prev.add(1);
            result.add(prev);

            if (numRows == 1) {
                return result;
            }

            for (int i = 2; i <= numRows; i++) {
                ArrayList<Integer> line = new ArrayList<>(i);

                for (int j = 0; j < i; j++) {
                    if (j == 0 | j == i - 1) {
                        line.add(1);
                    } else {
                        line.add(prev.get(j - 1) + prev.get(j));
                    }
                }

                result.add(line);
                prev = line;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.generate(3)); // expected = [[1],[1,1],[1,2,1]]
        System.out.println(s.generate(5)); // expected = [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
    }
}
