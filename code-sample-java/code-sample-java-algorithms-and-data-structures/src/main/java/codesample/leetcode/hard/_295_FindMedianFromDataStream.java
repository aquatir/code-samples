package codesample.leetcode.hard;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream — https://leetcode.com/problems/find-median-from-data-stream/
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 */
public class _295_FindMedianFromDataStream {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    class MedianFinder {

        // step 1: implement in the stupid way
        // step 2: implement in a smart way
        /**
         * take max heap and min heap;
         * divide elements in halfs
         * make elements appear in max heap first
         * make sure heaps are ~ in size, apart from a single element
         * when need num, result is either on top of max heap
         * or its (maxHeap.peek() + minheap.peek()) / 2
         */

        // first half => sorted by max
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));

        // second half => sorted by min
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (a - b));


        // if you draw it it will be
        // [maxHead][minHeap]


        public MedianFinder() {

        }

        public void addNum(int num) {

            // prioritize adding element to maxHeap if it's empty
            // if the element should go to lower part (maxHeap.peek() already >= num)
            // add to maxHeap
            // else add to minHeap
            if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // make sure that maxHeap is either larger by 1 or heaps are the same size
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            int n = maxHeap.size() + minHeap.size();

            if (n % 2 == 0) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return (double) maxHeap.peek();
            }
        }
    }


}
