package codesample.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239. Sliding Window Maximum — https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * NOTE: NOT COMPLETE SOLUTION
 */
public class _239_SlidingWindowMaximum {

    // approach 1: with priority queue: should work, but fails on time limit exceeded, because remove on PriorityQueue is slow.
    // approach 2: with deque.
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 0) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        int resIndex = 0;

        // put indexes of max elements into the deque, because we need index for size
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // preserve deque size less than k.
            // remove elements which were added first (e.g. normal queue behaviour)
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // now, also remove element which were added last, e.g. in stack fashion.
            // we remove everything which is definitely less than nums[i]
            // after that the deque's leftmost element will be the max
            // this is also true on each iteration.
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // after while above the leftmost index is max, so we record int
            if (i >= k - 1) {
                result[resIndex++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var s = new _239_SlidingWindowMaximum();
        var res1 = s.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        for (int r: res1) {
            System.out.print(r + ", "); // expected [3,3,5,5,6,7]
        }
    }
}
