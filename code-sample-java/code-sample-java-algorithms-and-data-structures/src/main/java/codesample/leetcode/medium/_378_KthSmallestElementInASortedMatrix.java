package codesample.leetcode.medium;

import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted Matrix — https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * <p>
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * You must find a solution with a memory complexity better than O(n2).
 */
public class _378_KthSmallestElementInASortedMatrix {

    class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    public int kthSmallest(int[][] matrix, int k) {
        // possible: constant memory and O(n)


        // approach 1. put all elements into priority queue
        // extract Kth element.
        // space: O(k),
        // time: n * O(k)

        // approach 2: think of matrix as a set of sorted arrays
        // only add the first elements on each row
        // than pick one and advance the row
        // do that until K elements
        // space: k
        // time: k * O(k)

        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

        // put the 1st element of each row in the min heap
        // we don't need to push more than 'k' elements in the heap because the elements are sorted
        for (int i = 0; i < matrix.length && i < k; i++)
            minHeap.add(new Node(i, 0));

        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the row of the top element has more elements, add the next element to the heap
        int numberCount = 0;
        int result = 0;
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result = matrix[node.row][node.col];
            if (++numberCount == k)
                break;
            node.col++;
            if (matrix[0].length > node.col)
                minHeap.add(node);
        }
        return result;
    }
}
