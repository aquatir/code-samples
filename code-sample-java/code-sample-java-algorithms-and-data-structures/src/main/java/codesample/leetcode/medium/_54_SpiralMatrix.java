package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix — https://leetcode.com/problems/spiral-matrix/description/
 */
public class _54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        // make boundaries, travese in all directions, repeat again until done

        List<Integer> result = new ArrayList<>();
        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;

        int topBound = 0;
        int leftBound = 0;
        int rightBound = numOfColumns - 1;
        int bottomBound = numOfRows - 1;

        while (result.size() < numOfRows * numOfColumns) {

            // Traverse from left to rightBoundry.
            for (int col = leftBound; col <= rightBound; col++) {
                result.add(matrix[topBound][col]);
            }
            // Traverse downwards, skip the first entry, because it was handled above
            for (int row = topBound + 1; row <= bottomBound; row++) {
                result.add(matrix[row][rightBound]);
            }
            // Make sure we are now on a different row.
            if (topBound != bottomBound) {
                // Traverse from rightBoundary to left, also skip first entry, because it was handled before
                for (int col = rightBound - 1; col >= leftBound; col--) {
                    result.add(matrix[bottomBound][col]);
                }
            }
            // Make sure we are now on a different column.
            if (leftBound != rightBound) {
                // Traverse upwards, also skip first entry, because it was handled before
                for (int row = bottomBound - 1; row > topBound; row--) {
                    result.add(matrix[row][leftBound]);
                }
            }
            leftBound++;
            rightBound--;
            topBound++;
            bottomBound--;
        }

        return result;
    }
}
