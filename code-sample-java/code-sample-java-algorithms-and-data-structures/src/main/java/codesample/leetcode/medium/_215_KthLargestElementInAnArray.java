package codesample.leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array — https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity.
 */
public class _215_KthLargestElementInAnArray {

    // approach 1: straightforward MAX priority queue approach
    // n * log(n) due to priority queue insert being log(n) and there are n elements.
//    public int findKthLargest(int[] nums, int k) {
//        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Comparator.reverseOrder());
//
//        for (int number: nums) {
//            maxHeap.offer(number);
//        }
//
//        for (int i = 0; i < (k-1); i++) {
//            maxHeap.poll();
//        }
//
//        return maxHeap.poll();
//    }

    // approach 2: min queue with the size of K
    // we store k the largest elements in this queue, making it n * log(k) which is just tiny bit faster
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int number: nums) {
            if (minHeap.size() == k) {
                // if we are at max => only push larger elements;
                if (minHeap.peek() < number) {
                    minHeap.poll();
                    minHeap.offer(number);
                } else {
                    // do nothing
                }
            } else {
                // push first k elements
                minHeap.offer(number);
            }

        }

        // at this point the smallest of elements is the Kth largest
        return minHeap.poll();
    }

    // approach 3: quick select...? Should be linear but so hard to implement
}
