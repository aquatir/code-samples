package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class _986_IntervalListIntersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        int left = 0;
        int right = 0;

        List<int[]> res = new ArrayList<>();

        while (left != firstList.length && right != secondList.length) {
            int[] leftInterval = firstList[left];
            int[] rightInterval = secondList[right];

            if ((leftInterval[0] <= rightInterval[0] && rightInterval[0] <= leftInterval[1])
                || rightInterval[0] <= leftInterval[0] && leftInterval[0] <= rightInterval[1]) {

                res.add(
                    new int[]{
                        Math.max(leftInterval[0], rightInterval[0]),
                        Math.min(leftInterval[1], rightInterval[1])
                    }
                );
            }

            if (leftInterval[1] < rightInterval[1]) {
                left++;
            } else {
                right++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
