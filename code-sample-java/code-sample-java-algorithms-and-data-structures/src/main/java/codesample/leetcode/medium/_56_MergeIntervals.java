package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        if (intervals.length < 2) {
            return intervals;
        }

        // Sort by starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int resultIndex = 0;
        int[] a = intervals[0];
        int[] b = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            b = intervals[i];

            // first.end >= second.start
            if (a[1] >= b[0]) {
                a = new int[]{a[0], Math.max(a[1], b[1])};
            } else {
                result.add(a);
                a = b;
            }
        }

        if (a[1] >= b[0]) {
            result.add(new int[]{a[0], Math.max(a[1], b[1])});
        }

        return result.toArray(new int[result.size()][]);
    }
}
