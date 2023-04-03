package codesample.leetcode.easy;

import java.util.ArrayDeque;

/**
 * 346. Moving Average from Data Stream — https://leetcode.com/problems/moving-average-from-data-stream/description/
 */
public class _346_MovingAverageFromDataStream {

    class MovingAverage {

        private ArrayDeque<Integer> deck;
        private int runningSum;
        private int curSize;
        private int maxSize;

        public MovingAverage(int size) {
            deck = new ArrayDeque<>(size + 1);
            runningSum = 0;
            curSize = 0;
            maxSize = size;
        }

        public double next(int val) {
            deck.addLast(val);
            curSize++;
            runningSum += val;
            if (curSize > maxSize) {
                var removed = deck.removeFirst();
                runningSum -= removed;
                curSize--;
            }
            return (double) runningSum / curSize;
        }
    }

}
