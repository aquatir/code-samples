package codesample.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements — https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class _347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {

        // approach 1: brute force:
        //  1. count with hasmap: n memory, O(n) speed
        //  2. put all values into a min priority queue by frequency (n * log(k)), hold only k values;
        //  3. all numbers in PQ are correct

        Map<Integer, Integer> freqMap = new HashMap<>();

        // count freq
        for (int num: nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }

        // create minQueue holding the top of freq elements;
        // generic parameter (aka sort key) == the number
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(
            (num1, num2) -> Integer.compare(freqMap.get(num1), freqMap.get(num2))
        );

        for (var entry: freqMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (minQueue.size() < k) {
                minQueue.offer(key);
            } else {
                if (freqMap.get(minQueue.peek()) < value) {
                    minQueue.poll();
                    minQueue.offer(key);
                } else {
                    // skip => the value is less frequent
                }
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minQueue.poll();
        }

        return result;
    }
}
