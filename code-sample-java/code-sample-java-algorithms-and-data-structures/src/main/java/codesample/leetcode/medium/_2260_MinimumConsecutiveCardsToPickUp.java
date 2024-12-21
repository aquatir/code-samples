package codesample.leetcode.medium;

import java.util.HashMap;

/**
 * 2260. Minimum Consecutive Cards to Pick Up — https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/description/
 */
public class _2260_MinimumConsecutiveCardsToPickUp {

    public int minimumCardPickup(int[] cards) {

        // remember the last position for each num
        // if the second one is encountered, calculate the distance by comparing the indexes
        var min = Integer.MAX_VALUE;
        var numToIndex = new HashMap<Integer, Integer>();

        for (int i = 0; i < cards.length; i++) {
            var num = cards[i];
            if (numToIndex.containsKey(num)) {
                min = Math.min(min, i - numToIndex.get(num) + 1);
            }
            numToIndex.put(num, i);
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }
}
