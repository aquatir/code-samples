package codesample.other.educative._04_merge_intervals;

import java.util.ArrayList;
import java.util.List;

public class _03_InsertInterval {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    ;

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // Given a list of non-overlapping intervals sorted by their start time,
        // insert a given interval at the correct position and merge all necessary
        // intervals to produce a list that has only mutually exclusive intervals.

        // 1. Find the insertion point. Possible scenarios. B is inserted interval.
        // |a|---|b|--|b|---|a|  <- Target fully inside the other. Don't insert anything anywhere
        // |b|---|a|--|a|---|b|  <- Target fully consumer interval a. Must continue to also include other points
        // |a|---|b|--|a|---|b|  <- a and b should be merged. Must continue to include others
        // |b|---|a|--|b|---|a|  <- a and b should merge. DO not continue after the merge.
        // |b|---|b|--|a|---|a|  <- no overlap add result to intervals
        // |a|---|a|--|b|---|b|  <- no overlap add result to intervals

        // We only do one of the last two if none of the above were triggered.
        // The algo:
        // if new point has no overlap that is a.end > b.start
        //   Simply add a
        // else if new point's start after b' start: a.start <= b.start && b.start <= a.end
        //   merge a and b. Don't add (yet)
        // else if INSIDE: a.start <= b.start & b.end <= a.end.
        //   Simply add a
        // else if b.start <= a.end && a.end <= b.end
        //   merge and b . Don't add (yet)
        // else if b.start > a.start && b.end > a.end
        //   Simply add a

        List<Interval> mergedIntervals = new ArrayList<>();

        // newInterval

        int i = 0;
        int size = intervals.size();

        // add those without overlap in the beginning
        while (i < size && intervals.get(i).end < newInterval.start) {
            mergedIntervals.add(intervals.get(i++));
        }

        // merge overlaps
        while (i < size && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }

        mergedIntervals.add(newInterval);

        // add remaining

        while (i != intervals.size()) {
            mergedIntervals.add(intervals.get(i++));
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : _03_InsertInterval.insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : _03_InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : _03_InsertInterval.insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}
