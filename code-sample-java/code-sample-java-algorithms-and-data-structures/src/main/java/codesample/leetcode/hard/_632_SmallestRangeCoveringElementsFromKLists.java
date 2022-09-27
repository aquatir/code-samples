package codesample.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 632. Smallest Range Covering Elements from K Lists — https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 *
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 */
public class _632_SmallestRangeCoveringElementsFromKLists {

    class Node {
        public int elementIndex;
        public int arrayIndex;

        Node(int elementIndex, int arrayIndex) {
            this.elementIndex = elementIndex;
            this.arrayIndex = arrayIndex;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // arrays are in non-decreasing order
        // something similar to K-way merge sort...?

        // what if we put first elements of each array into PQ
        // the range will be min of PQ to max of PQ
        //

        // on each step we need to
        //      a) make sure that current range covers all arrays;
        //      b) make sure that the current range is the smallest possible;

        // iterate over all elements:
        //  a) take the smallest from list i (with PQ)
        //  b) when do we update the range:
        //

        // [4/, 10/, 15/, 24/, 26/]
        // [0/, 9/, 12/, 20/]
        // [5/, 18/, 22/, 30]
        // ans: [20,24]

        // initial 3 eles: [0,4,5], range is [0,5]
        // next element: 9: the 3 eles is [4,9,5] -> range is [4,9], bad range
        // 10 -> new range [10,9,5], range is [5,10], bad range
        // 12 -> new range [10, 12, 5], range is [5,12], bad
        // 15 -> new range [15, 12, 5], range is [5,15], bad
        // 18 -> new range [15, 12, 18], range is [12,18], smaller. New best: [12,18]
        // 20 -> new range [15, 20, 18], range is [15,20], worse
        // 22 -> new range [15, 20, 22], range is [15,22], worse
        // 24 -> new range [24, 20, 22], range is [20,24], better. New best [20,24]
        // 26 -> new range [26, 20, 22], range is [20,26], worse
        // 30 -> new range [26, 20, 30], range is [20,30], worse.
        // best: [20,24]

        // taking elements: min PQ. Continue until there are no elements left
        // might either put ALL elements into PQ, or add + extract to make smaller PQ

        // calculating current range: array of nums.length, update element on index, sort
        // sorting is very slowish, but should work...

        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
            (n1, n2) -> Integer.compare(nums.get(n1.arrayIndex).get(n1.elementIndex), nums.get(n2.arrayIndex).get(n2.elementIndex)));

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE, currentMaxNumber = Integer.MIN_VALUE;
        // put the 1st element of each array in the min heap
        for (int i = 0; i < nums.size(); i++)
            if (nums.get(i) != null) {
                minHeap.add(new Node(0, i));
                currentMaxNumber = Math.max(currentMaxNumber, nums.get(i).get(0));
            }

        // take the smallest (top) element form the min heap, if it gives us smaller range, update the ranges
        // if the array of the top element has more elements, insert the next element in the heap
        while (minHeap.size() == nums.size()) {
            Node node = minHeap.poll();
            int curNumber = nums.get(node.arrayIndex).get(node.elementIndex);
            if (rangeEnd - rangeStart > currentMaxNumber - curNumber) {
                rangeStart = curNumber;
                rangeEnd = currentMaxNumber;
            }
            node.elementIndex++;
            if (nums.get(node.arrayIndex).size() > node.elementIndex) {
                minHeap.add(node); // insert the next element in the heap
                currentMaxNumber = Math.max(currentMaxNumber, nums.get(node.arrayIndex).get(node.elementIndex));
            }
        }
        return new int[]{rangeStart, rangeEnd};
    }
}
