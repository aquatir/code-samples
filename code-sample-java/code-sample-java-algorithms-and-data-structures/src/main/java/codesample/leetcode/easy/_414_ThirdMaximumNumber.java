package codesample.leetcode.easy;

import java.util.PriorityQueue;

/**
 * 414. Third Maximum Number — https://leetcode.com/problems/third-maximum-number/description/
 */
public class _414_ThirdMaximumNumber {
    class Solution {
        public int thirdMax(int[] nums) {

            int n = nums.length;
            PriorityQueue<Integer> maxPq = new PriorityQueue<>(3, (a, b) -> Integer.compare(b,a) );

            for (int i = 0; i < n; i++) {

                // pq has size of 3, so extra data structure to find an element will be an overkill
                // checking 3 elements in PQ is fast enough
                if (!maxPq.contains(nums[i])) {
                    maxPq.offer(nums[i]);
                }
            }

            if (maxPq.size() >= 3) {
                maxPq.poll();
                maxPq.poll();
            }
            return maxPq.poll();
        }
    }
}
