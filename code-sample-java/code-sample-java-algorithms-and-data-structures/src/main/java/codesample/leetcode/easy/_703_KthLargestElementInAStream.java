package codesample.leetcode.easy;

import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream — https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted
 * * order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element
 * * in the stream.
 */
public class _703_KthLargestElementInAStream {
    class KthLargest {

        // the lowest element is Kth largest. Peek will return it.
        private PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        private int maxSize;

        public KthLargest(int k, int[] nums) {
            this.maxSize = k;
            for (int number: nums) {
                this.add(number);
            }
        }

        public int add(int val) {
            // always add, than remove
            this.minQueue.offer(val);

            if (this.minQueue.size() > this.maxSize) {
                this.minQueue.poll();
            }

            return this.minQueue.peek();

        }
    }
}
