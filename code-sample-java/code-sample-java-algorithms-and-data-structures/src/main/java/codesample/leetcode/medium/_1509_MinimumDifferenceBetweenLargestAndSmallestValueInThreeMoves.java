package codesample.leetcode.medium;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves — https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 * <p>
 * You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 * <p>
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 */
public class _1509_MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    public int minDifference(int[] nums) {

        // can always change the 3 values to one of them to get 0
        if (nums.length <= 4) {
            return 0;
        }


        // store only some of min and max elements
        // because they are the only elements we need to remove
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(4);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(4, Collections.reverseOrder());

        for (int num : nums) {
            minHeap.add(num);
            maxHeap.add(num);
            if (maxHeap.size() > 4) {
                minHeap.poll();
                maxHeap.poll();
            }
        }

        // Some arcane magic
        // the result is either take 3 from min or take 3 from max
        // or any combination, e.g. take 1 from min and take 3 from max
        // we store values in the manner that take 3 from max index will be equal to take 0 to max
        // same for e.g. take 2 from max == take 1 from min
        int[] smallest = new int[4];
        int[] largest = new int[4];
        for (int i = 0; i < 4; i++) {
            smallest[3 - i] = maxHeap.poll();
            largest[i] = minHeap.poll();
        }

        int output = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            output = Math.min(output, largest[i] - smallest[i]);
        }

        return output;
    }
}
