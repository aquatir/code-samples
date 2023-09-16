package codesample.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 933. Number of Recent Calls — https://leetcode.com/problems/number-of-recent-calls/description/
 */
public class _933_NumberOfRecentCalls {
    class RecentCounter {


        // stores all pings
        private Deque<Integer> deque;

        public RecentCounter() {
            this.deque = new ArrayDeque<>();
        }

        public int ping(int t) {
            deque.addFirst(t);

            // remove elements from the back that are older than [i - 3000]
            while (!deque.isEmpty() && deque.peekLast() < (t - 3000)) {
                deque.removeLast();
            }
            return deque.size();
        }
    }
}
