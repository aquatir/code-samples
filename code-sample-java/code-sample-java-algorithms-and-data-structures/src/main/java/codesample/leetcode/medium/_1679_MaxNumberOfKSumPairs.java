package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 1679. Max Number of K-Sum Pairs — https://leetcode.com/problems/max-number-of-k-sum-pairs/
 */
public class _1679_MaxNumberOfKSumPairs {

    // optimal: below


    // approach 1: two pass hashmap
    // for given number X, there is only one possible number Y, where X+Y == k
    // hence it does not make sense to save numbers for later. If pair is found, it's optimal to instantly remove it aka remove in greedy fashion

    // we could put all numbers in a hashmap for extra O(n) calculating number of times a number is encountered, then iterate over all numbers in O(n).
    // total O(n) time, O(n) space.

    // could be done without extra space? We can sort first in [n log (n)], then binary-search the corresponding number, but then we'll have to remove this number after it is encuntered
    // alternative will be to keep left & right pointer, this way we don't need to remove anything and just need a [n log(n)] sort + a second pass to count

//    public int maxOperations(int[] nums, int k) {
//        var freqMap = new HashMap<Integer, Integer>();
//
//        for (var n : nums) {
//            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
//        }
//
//        int result = 0;
//
//        // alternative faster solution: ignore original array and just iterate over map entries.
//        // this will allow removing multiple matches at the same time
//        for (var n : nums) {
//            if (freqMap.containsKey(n) && freqMap.containsKey(k - n)) {
//
//                // special case where next number is the same
//                // skip operation if number of hits is less than 2
//                if (n == (k - n) && freqMap.get(n) < 2) {
//                    continue;
//                }
//
//                result++;
//                freqMap.put(n, freqMap.get(n) - 1);
//                freqMap.put(k - n, freqMap.get(k - n) - 1);
//
//                if (freqMap.get(n) == 0) {
//                    freqMap.remove(n);
//                }
//                // check because might have removed the number alraedy for matching numbers
//                if (freqMap.containsKey(k - n) && freqMap.get(k - n) == 0) {
//                    freqMap.remove(k - n);
//                }
//            }
//        }
//
//        return result;
//    }

    //approach 2 one-pass hashmap
    public int maxOperations(int[] nums, int k) {
        var freqMap = new HashMap<Integer, Integer>();
        var result = 0;
        for (int n : nums) {
            int sumNumber = k - n;
            if (freqMap.getOrDefault(sumNumber, 0) > 0) {
                // remove complement from the map
                freqMap.put(sumNumber, freqMap.get(sumNumber) - 1);
                result++;
            } else {
                // add current element in the map
                freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
            }
        }
        return result;
    }
}
