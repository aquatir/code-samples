package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1481. Least Number of Unique Integers after K Removals — https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 *
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 *
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 */
public class _1481_LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        // feel frequincy map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int value: arr) {
            freqMap.put(value, freqMap.getOrDefault(value, 0) + 1);
        }

        // now create a priority queue for numbers
        // which is sorted by number of entries in freqMap. poll() must return least frequent
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(
            (num1, num2) -> Integer.compare(freqMap.get(num1), freqMap.get(num2))
        );

        for (int key: freqMap.keySet()) {
            minQueue.offer(key);
        }

        while (k >= 0) {
            if (minQueue.size() == 0) {
                return 0;
            }
            int minFreqElement = minQueue.peek();
            int freqOfElement = freqMap.get(minFreqElement);

            if (k - freqOfElement >= 0) {
                // if we can remove ALL instances of the said element — do it, reducing size by one
                k = k - freqOfElement;
                minQueue.poll(); // remove the picked element;
            } else {
                // if wa can't -> just break
                break;
            }
        }

        return minQueue.size();
    }
}
