package codesample.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239. Sliding Window Maximum — https://leetcode.com/problems/sliding-window-maximum/
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * NOTE: NOT COMPLETE SOLUTION
 */
public class _239_SlidingWindowMaximum {

    // should work, but fails on time limit exceeded, because remove on PriorityQueue is slow.
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1];


        if (nums.length == 1) {
            result[0] = nums[0];
            return result;
        }

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < k; i++) {
            maxQueue.offer(nums[i]);
        }
        result[0] = maxQueue.peek();

        for (int i = k; i < nums.length; i++) {
            maxQueue.remove(nums[i - k]);
            maxQueue.offer(nums[i]);
            result[i - k + 1] = maxQueue.peek();
        }

        return result;
    }
}
