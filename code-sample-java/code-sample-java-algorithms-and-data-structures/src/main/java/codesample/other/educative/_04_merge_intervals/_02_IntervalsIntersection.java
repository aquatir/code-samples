package codesample.other.educative._04_merge_intervals;

import java.util.ArrayList;
import java.util.List;

public class _02_IntervalsIntersection {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        List<Interval> intervalsIntersection = new ArrayList<Interval>();

        // first: Sort both (need to?) => already sorted

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex != arr1.length && rightIndex != arr2.length) {
            Interval left = arr1[leftIndex];
            Interval right = arr2[rightIndex];

            if ((left.start <= right.start && right.start <= left.end)
                || right.start <= left.start && left.start <= right.end) {
                intervalsIntersection.add(new Interval(Math.max(left.start, right.start), Math.min(left.end, right.end)));
            }

            if (left.end < right.end) {
                leftIndex++;
            } else {
                rightIndex++;
            }
        }


        // then: Pick first value on left and right paths:
        // find the intersecting part:
        // either b.start <= a.start <= b.end
        // a.start <= b.start <= a.end
        //  interlap interval would be:
        //    start = max(a.start, b.start)
        //    end.  = min(a.end, b.end)

        // then me move to the interval having lesser "end" value

        // TODO: Write your code here
        return intervalsIntersection.toArray(new Interval[intervalsIntersection.size()]);
    }

    public static void main(String[] args) {
        Interval[] input1 = new Interval[]{new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)};
        Interval[] input2 = new Interval[]{new Interval(2, 3), new Interval(5, 7)};
        Interval[] result = _02_IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[]{new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)};
        input2 = new Interval[]{new Interval(5, 10)};
        result = _02_IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }
}
