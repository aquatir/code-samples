package codesample.leetcode.easy;

import java.util.PriorityQueue;

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
