package codesample.leetcode.medium;

import java.util.PriorityQueue;

/**
 * Paywall! —  https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * <p>
 * should read something like:
 * <p>
 * Given ‘N’ sticks with different lengths, we need to connect these sticks into one big stick with minimum cost.
 * * The cost of connecting two sticks is equal to the sum of their lengths.
 * <p>
 * Example 1:
 * Input: [1, 3, 11, 5]
 * Output: 33
 * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)
 * <p>
 * Example 2:
 * Input: [3, 4, 5, 6]
 * Output: 36
 * Explanation: First connect 3+4(=7), then 5+6(=11), 7+11(=18). Total cost is 36 (7+11+18)
 */
public class ___MinumumCostToConnectSticks {
    public int minimumCostToConnectRopes(int[] ropeLengths) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        for (int rope : ropeLengths) {
            minQueue.offer(rope);
        }

        int totalSum = 0;

        while (minQueue.size() != 1) {
            int sum = minQueue.poll() + minQueue.poll();
            totalSum += sum;
            minQueue.offer(sum);
        }

        return totalSum;
    }
}
