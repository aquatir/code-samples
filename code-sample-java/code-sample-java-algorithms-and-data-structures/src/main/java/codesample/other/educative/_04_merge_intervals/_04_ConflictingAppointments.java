package codesample.other.educative._04_merge_intervals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

public class _04_ConflictingAppointments {
    /**
     * Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.
     *
     * Appointments: [[1,4], [2,5], [7,9]]
     * Output: false
     * Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
     *
     * Appointments: [[6,7], [2,4], [8,12]]
     * Output: true
     * Explanation: None of the appointments overlap, therefore a person can attend all of them.
     *
     * Appointments: [[4,5], [2,3], [3,6]]
     * Output: false
     * Explanation: Since [4,5] and [3,6] overlap, a person cannot attend both of these appointments.
     */
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        /**
         * 1. sort by start date
         * 2. get first and second appointment (sorted by start)
         * 3. The only case where no overlap is: first.end < second.start
         * 4. In any other case: return false
         */


        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));

        Interval first = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            Interval second = intervals[i];
            if (! (first.end < second.start)) {
                return false;
            } else {
                first = second;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        boolean result = _04_ConflictingAppointments.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
        result = _04_ConflictingAppointments.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        result = _04_ConflictingAppointments.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }
}
