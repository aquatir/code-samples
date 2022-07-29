package codesample.leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 480. Sliding Window Median — https://leetcode.com/problems/sliding-window-median/
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the
 * very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 */
public class _480_SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {

        // approach one: just use an array.
        // to find median have to sort n * log(n) first
        // adding element to preserve sort is still n * log(n)
        // [maxHeap][minHeap]

        // [1, 2, 3, 4]
        // k = 2 => [1,2], [2,3], [3,4] = 3 nums = 4 - 2 + 1

        /**
         for inside of k: build minHeap + maxHeap such as all elements are in
         [maxHeap][minHeap] => this allows to get mediam quick O(1).
         building those heaps is also quick O(log(n)) to add new element

         before moving to the right:
         remove the LOWEST of maxHeap (we know it, because we move from it)
         put min of minHeap to maxHeap
         add new element to minHeap
         re-sort heaps if nessessary

         always have maxHeap either the same size as minHeap or 1 above, but not more.
         resort if nessessary.
         */

        int numbersInReturn = nums.length - k + 1; // ???
        double[] result = new double[numbersInReturn];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // todo: can make those two cycle into a single one
        for (int i = 0; i < k; i++) {
            int element = nums[i];

            if (maxHeap.isEmpty() || maxHeap.peek() >= element) {
                maxHeap.add(element);
            } else {
                minHeap.add(element);
            }

            // max heap always either the same as min OR 1 element larget
            rebalanceHeaps(maxHeap, minHeap);
        }

        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            result[0] = ( (double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        } else {
            result[0] = (double) maxHeap.peek();
        }

        int nextMedianIndex = 1;
        for (int i = k; i < nums.length; i++) {
            // first => remove previous elements
            // it can be in either of the heaps, so search + remove
            // elements are in [maxHeap][minHeap], so we remove from maxHeap here
            int elementToRemove = nums[i - k];
            if (maxHeap.contains(elementToRemove) && minHeap.contains(elementToRemove)) {
                maxHeap.remove(elementToRemove);
            } else if (maxHeap.contains(elementToRemove)) {
                maxHeap.remove(elementToRemove);
            } else {
                minHeap.remove(elementToRemove);
            }
            // next => balance the heaps
            rebalanceHeaps(maxHeap, minHeap);
            // next => add extra element
            int element = nums[i];
            if (maxHeap.isEmpty() || maxHeap.peek() >= element) {
                maxHeap.add(element);
            } else {
                minHeap.add(element);
            }
            // next => rebalance again
            rebalanceHeaps(maxHeap, minHeap);
            // finally, calculate mean

            if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
                result[nextMedianIndex] = ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
            } else {
                result[nextMedianIndex] = (double) maxHeap.peek();
            }
            nextMedianIndex++;
        }

        return result;
    }

    public void rebalanceHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        var s = new _480_SlidingWindowMedian();

//        var result = s.medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
//        for (double value : result) {
//            System.out.print(value + " "); // expected = [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
//        }

//        System.out.println();
//        var result2 = s.medianSlidingWindow(new int[]{2147483647,2147483647}, 2);
//        for (double value: result2) {
//            System.out.print(value + " "); // expected = [2147483647.00000]
//        }

        System.out.println();

        var result3 = s.medianSlidingWindow(new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648},
            3);
        for (double value: result3) {
            System.out.print(value + " "); // expected = [-2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, -2147483648.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, 2147483647.00000, -2147483648.00000]
        }
        System.out.println();

        System.out.println( (long) Integer.MAX_VALUE - (long) Integer.MIN_VALUE);
        System.out.println( (long) Integer.MIN_VALUE - (long) Integer.MAX_VALUE);
    }
}
