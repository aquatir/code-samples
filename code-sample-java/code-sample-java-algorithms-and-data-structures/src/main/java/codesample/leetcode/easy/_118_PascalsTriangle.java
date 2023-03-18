package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class _118_PascalsTriangle {
    static class Solution {
//        public List<List<Integer>> generate(int numRows) {
//            List<List<Integer>> result = new ArrayList<>(numRows);
//
//            List<Integer> line = new ArrayList<>(1);
//            line.add(1);
//            result.add(line);
//
//            if (numRows == 1) {
//                return result;
//            }
//
//            for (int i = 2; i <= numRows; i++) {
//                line = new ArrayList<>(i);
//
//                for (int j = 0; j < i; j++) {
//                    if (j == 0 | j == i - 1) {
//                        line.add(1);
//                    } else {
//                        line.add(result.get(i-2).get(j - 1) + result.get(i-2).get(j));
//                    }
//                }
//
//                result.add(line);
//            }
//
//            return result;
//        }

        // simplier to follow solution
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();

            result.add(List.of(1));
            for (int i = 1; i < numRows; i++) {
                var prevRow = result.get(i - 1);
                int sizeOfSubResult = i + 1;
                var subResult = new ArrayList<Integer>(sizeOfSubResult);
                subResult.add(0, 1);
                for (int j = 1; j < sizeOfSubResult - 1; j++) {
                    subResult.add(prevRow.get(j - 1) + prevRow.get(j));
                }
                subResult.add(1);
                result.add(subResult);
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
