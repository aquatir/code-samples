package codesample.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals — https://leetcode.com/problems/non-overlapping-intervals/description/
 */
public class _435_NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {

        // sort by end time

        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        int ans = 0;

        // this will be equal to END of interval eventually
        int endOfPreviousInterval = Integer.MIN_VALUE;

        for (int i = 0; i < intervals.length; i++) {

            // pick an interval
            int x = intervals[i][0];
            int y = intervals[i][1];

            // make a decision whatever we are deleting the next interval or keeping it
            // if the beginning of new interval is greater than (or eq) the end of previous => no overlap => no need to delete => simply move the end of previous
            // othersize, we have to delete an interval. Don't move endOfPrevious, but instead just increment the answer
            if (x >= endOfPreviousInterval) {
                // Don't remove interval, instead advance K
                endOfPreviousInterval = y;
            } else {
                // Don't advance interval, but remove it
                ans++;
            }
        }

        return ans;
    }
}
