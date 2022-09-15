package codesample.leetcode.easy;

import java.util.PriorityQueue;

public class _703_KthLargestElementInAStream {
    class KthLargest {

        // the lowest element is Kth largest. Peek will return it.
        private final PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        private final int maxSize;

        public KthLargest(int k, int[] nums) {
            this.maxSize = k;
            for (int number : nums) {
                this.add(number);
            }
        }

        public int add(int val) {
            // if maxSize => maybe add
            if (this.minQueue.size() == this.maxSize) {
                if (minQueue.peek() >= val) {
                    // don't add element which is smaller than the smallest already
                } else {
                    // else, remove the smallest and add another one.
                    minQueue.poll();
                    minQueue.offer(val);
                }
            } else {
                // if not max size => always add
                this.minQueue.add(val);
            }

            return this.minQueue.peek();
        }
    }
}
