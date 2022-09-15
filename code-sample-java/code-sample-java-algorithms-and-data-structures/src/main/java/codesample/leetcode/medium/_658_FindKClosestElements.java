package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 658. Find K Closest Elements — https://leetcode.com/problems/find-k-closest-elements/
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
 * * The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 */
public class _658_FindKClosestElements {

    // this will will for unsorted array too, but is slower.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        // 1. Store the numbers by lowest diff
        // 2. The K elements in this pq must be a result
        // 3. When removing the numbers => remove the one with LARGEST diff first
        //.   in order to do it => sort by largest, so max queue
        //    preserve the size of queue
        PriorityQueue<Integer> maxDiffPq = new PriorityQueue<>(
            (val1, val2) -> Integer.compare(Math.abs(x - val2), Math.abs(x - val1))
        );

        for (int num : arr) {

            if (maxDiffPq.size() < k) {
                maxDiffPq.offer(num);
            } else {
                if (Math.abs(maxDiffPq.peek() - x) <= Math.abs(num - x)) {
                    // skipping the number if the peak has LESS difference than the current
                } else {
                    maxDiffPq.poll(); // removing element with max diff
                    maxDiffPq.offer(num); // offering a new element with less diff
                }
            }
        }

        // at this point maxDiffPq contains all elements closest to X, but from max to min.
        // we need min to max.
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(maxDiffPq.poll());
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        var s = new _658_FindKClosestElements();
        //System.out.println(s.findClosestElements(new int[]{1,2,3,4,5}, 4, -1)); // expected [1,2,3,4]
        System.out.println(s.findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9)); // expected [10]

    }
}
