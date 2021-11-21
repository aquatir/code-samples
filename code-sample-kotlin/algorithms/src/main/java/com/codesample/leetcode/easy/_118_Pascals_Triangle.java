package com.codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _118_Pascals_Triangle {
    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();

            List<Integer> prev = new ArrayList<>();
            prev.add(1);
            result.add(prev);

            if (numRows == 1) {
                return result;
            }

            for (int i = 2; i <= numRows; i++) {
                int[] line = new int[i];
                line[0] = 1;
                line[i - 1] = 1;

                for (int j = 1; j < i - 1; j++) {
                    line[j] = prev.get(j - 1) + prev.get(j);
                }

                List<Integer> asList = Arrays.stream(line).boxed().collect(Collectors.toList());
                result.add(asList);
                prev = asList;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        System.out.println(s.generate(3)); // expected = [[1],[1,1],[1,2,1]]
    }
}
