package codesample.other.educative._04_merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class _01_MergeIntervals {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    ;

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new LinkedList<Interval>();

        // return instantly for 0 and 1 elements in interval.
        if (intervals.size() < 2) {
            return intervals;
        }

        // Sort interval by starting point
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // Look at first two intervals: A and B.
        Iterator<Interval> iterator = intervals.iterator();

        Interval first = iterator.next();
        Interval second = first;
        while (iterator.hasNext()) {
            second = iterator.next();

            // If A.endPoint >= B.startPoint
            //  merge intervals. A = newMergeInterval. B = nextInterval.
            if (first.end >= second.start) {
                first = new Interval(first.start, Math.max(first.end, second.end));
            } else {
                mergedIntervals.add(first);
                first = second;
            }
        }

        if (first.end >= second.start) {
            mergedIntervals.add(new Interval(first.start, Math.max(first.end, second.end)));
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : _01_MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] "); // Merged intervals: [1,5] [7,9]
        System.out.print("should be : [1,5] [7,9]");

        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : _01_MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] "); // Merged intervals: [2,4] [5,9]
        System.out.print("should be : [2,4] [5,9]");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : _01_MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] "); // Merged intervals: [1,6]
        System.out.print("should be : [1,6]");
        System.out.println();
    }
}
