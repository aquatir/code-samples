package codesample.other.crackingthecodinginterview.chapter1;

import java.util.HashSet;
import java.util.function.Function;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, it's entire row and column are set to 0
 */
public class _0018_ZeroMatrix {
    public int[][] zeroMatrix(int[][] matrix) {

        // Brute force:
        // 1. create 2 sets of size N and M: one for rows, one for columns
        // 2. read every number in a matrix, filling the sets
        // 3. for each value in sets, put zeroes for rows and for columns
        // Complexity: N * M to read all values, then N*M to zero-out all rows and columns.

        var rowCount = matrix.length;
        if (rowCount == 0) {
            return matrix;
        }
        var columnCount = matrix[0].length;

        var rowNulls = new HashSet<Integer>();
        var columnNulls = new HashSet<Integer>();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (matrix[i][j] == 0) {
                    rowNulls.add(i);
                    columnNulls.add(j);
                }
            }
        }

        for (var rowIndex: rowNulls) {
            for (int j = 0; j < columnCount; j++) {
                matrix[rowIndex][j] = 0;
            }
        }

        for (var columnIndex: columnNulls) {
            for (int i = 0; i < rowCount; i++) {
                matrix[i][columnIndex] = 0;
            }
        }

        return matrix;
    }

    //
    public int[][] zeroMatrixStoreInMatrix(int[][] matrix) {

        // Store in matrix:
        // Instead of creating 2 sets of size N and M: one for rows, one for columns
        // we can instead update only the upper column for column, and the left row for rows, when marking the nulls

        var rowCount = matrix.length;
        if (rowCount == 0) {
            return matrix;
        }
        var columnCount = matrix[0].length;

        var shouldNullifyLeftColumn = false;
        var shouldNullifyTopRow = false;

        // after this, the left column and the top row will have zeroes in places that should be zeroed-out
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (matrix[i][j] == 0 && i != 0 && j != 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
                if (matrix[i][j] == 0 && i == 0) {
                    shouldNullifyTopRow = true;
                }
                if (matrix[i][j] == 0 && j == 0) {
                    shouldNullifyLeftColumn = true;
                }
            }
        }

        // read the top row, skipping the first one, we will nullify it later
        for (int i = 1; i < columnCount; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < rowCount; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        // read the left column, skipping the first one, we will nullify it later if needed
        for (int i = 1; i < rowCount; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < columnCount; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (shouldNullifyTopRow) {
            for (int j = 0; j < rowCount; j++) {
                matrix[0][j] = 0;
            }
        }
        if (shouldNullifyLeftColumn) {
            for (int j = 0; j < columnCount; j++) {
                matrix[j][0] = 0;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        var s = new _0018_ZeroMatrix();

//        Function<int[][], int[][]> func = s::zeroMatrix;
        Function<int[][], int[][]> func = s::zeroMatrixStoreInMatrix;

        // expected:
        // 1
        printNByNArray(func.apply(new int[][]{
                new int[]{1}
            })
        );

        System.out.println();

        // expected:
        // 0
        printNByNArray(func.apply(new int[][]{
                new int[]{0}
            })
        );

        System.out.println();

        // expected:
        // 0 0
        // 3 0
        printNByNArray(func.apply(new int[][]{
                new int[]{1, 0},
                new int[]{3, 4}
            })
        );
        System.out.println();

        // expected:
        // 1 0
        // 0 0
        // 4 0
        printNByNArray(func.apply(new int[][]{
                new int[]{1, 2},
                new int[]{3, 0},
                new int[]{4, 5}
            })
        );
        System.out.println();

        // expected:
        // 1 0 3
        // 0 0 0
        printNByNArray(func.apply(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 0, 6},
            })
        );

        System.out.println();
        // expected:
        // 0 0 0
        // 0 5 0
        // 0 0 0
        printNByNArray(func.apply(new int[][]{
                new int[]{0, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 0},
            })
        );
    }

    private static void printNByNArray(int[][] ints) {
        var rowsNum = ints.length;
        var columnsNum = ints[0].length;
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnsNum; j++) {
                System.out.print(ints[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
