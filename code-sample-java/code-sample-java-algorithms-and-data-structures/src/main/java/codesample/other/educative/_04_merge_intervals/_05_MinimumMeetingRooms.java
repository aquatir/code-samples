package codesample.other.educative._04_merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum number of rooms required to hold all the meetings.
 *
 * Meetings: [[1,4], [2,5], [7,9]]
 * Output: 2
 * Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. [7,9] can
 * occur in any of the two rooms later.
 *
 * Meetings: [[6,7], [2,4], [8,12]]
 * Output: 1
 * Explanation: None of the meetings overlap, therefore we only need one room to hold all meetings.
 */
class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

public class _05_MinimumMeetingRooms {
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        /**
         * 1. Sort by start
         * 2. Starting from the first meeting:
         *  3. Figure out how many meetings have ended already
         *  4. remove them
         *  5. add new meeting to the DS of non-ended
         * 6. to remove you want to know the end date, so min-heap will do the trick
         */

        int maxLength = 0;
        var mutableMeetings = new ArrayList<>(meetings);
        mutableMeetings.sort(Comparator.comparing(meeting -> meeting.start));
        var minHeap = new PriorityQueue<Meeting>((m1, m2) -> Integer.compare(m1.end, m2.end));

        for (Meeting m : mutableMeetings) {
            while (!minHeap.isEmpty() && minHeap.peek().end <= m.start) {
                // remove all meetings which has already ended
                minHeap.poll();
            }
            minHeap.add(m);
            maxLength = Math.max(maxLength, minHeap.size());
        }

        return maxLength;
    }

    public static void main(String[] args) {
        List<Meeting> input = List.of(new Meeting(4, 5), new Meeting(2, 3), new Meeting(2, 4), new Meeting(3, 5));
        int result = _05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = List.of(new Meeting(1, 4), new Meeting(2, 5), new Meeting(7, 9));
        result = _05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = List.of(new Meeting(6, 7), new Meeting(2, 4), new Meeting(8, 12));
        result = _05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = List.of(new Meeting(1, 4), new Meeting(2, 3), new Meeting(3, 6));
        result = _05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = List.of(new Meeting(4, 5), new Meeting(2, 3), new Meeting(2, 4), new Meeting(3, 5));
        result = _05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}
