package codesample.other.crackingthecodinginterview.chapter1;

/**
 * Given an image represented y an NxN matrix, where each pixels in the image is 4 bytes, write a method to rotate the
 * image by 90 degree. Can you do this in place?
 */
public class _0017_RotateMatrix {

    // int holds 4 bytes of data
    // perhaps in-place is possible if we swap the bytes one by-one?
    public int[][] rotateMatrix(int[][] matrix) {
        /*
         first: let's try to rotate in place, just moving the elements
         how to move them?
         after 90 rotation, where would an element a[i][j] end up given the size n?
         let's assume n = 3
         1 2 3 => 7 4 1
         4 5 6 => 8 5 2
         7 8 9 => 9 6 3
         a[0][0] => a[0][2]
         a[0][1] => a[1][1]
         a[0][2] => a[2][2]
         a[1][0] => a[0][1]
         a[1][1] => a[1][1]
         a[1][2] => a[2][2]
         a[2][0] => a[0][0]
         a[2][1] => a[1][0]
         a[2][2] => a[2][0]
         what's the catch?
         if we create another matrix, we might be able to iterate over elements...?
         the first matrix => start with top left elements, the second => start with top right elements
         create an "increment index" operation that first moves right, then down, then left, then top, then decrease the
         circle size and continues.

         rotation is a 4-elements mega-swap, so we can remember the 4 elements and swap them?
         [x] x x x [x]
          x  x x x  x
          x  x x x  x
          x  x x x  x
         [x] x x x [x] -> swap only these elements, then move each by 1
        */

        int n = matrix.length - 1;
        if (matrix.length % 2 == 1) {
            n -= 1;
        }
        int maxIndex = matrix.length-1;

        for (int i = 0; i < n; i++) {
            System.out.println("loop: " + i);
            for (int j = 0; j < maxIndex - i; j++) {

                int topLeft = matrix[0 + i][0 + i + j];
                int topRight = matrix[0 + i + j][maxIndex - i];
                int bottomRight = matrix[maxIndex - i][maxIndex - i - j];
                int bottomLeft = matrix[maxIndex - i - j][0 + i];

                matrix[0 + i][0 + i + j] = bottomLeft; // topLeft corner
                matrix[0 + i + j][maxIndex - i] = topLeft; // topRight corner
                matrix[maxIndex - i][maxIndex - i - j] = topRight; // bottomRight corner
                matrix[maxIndex - i - j][0 + i] = bottomRight; // bottomLeft corner
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        var s = new _0017_RotateMatrix();


        // expected:
        // 1
        printNByNArray(s.rotateMatrix(new int[][]{
                new int[]{1}
            })
        );

        System.out.println();

        // expected:
        // 3 1
        // 4 2
        printNByNArray(s.rotateMatrix(new int[][]{
                new int[]{1, 2},
                new int[]{3, 4}
            })
        );

        System.out.println();

        // expected:
        // 7 4 1
        // 8 5 2
        // 9 6 3
        printNByNArray(s.rotateMatrix(new int[][]{
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9}
            })
        );
    }

    private static void printNByNArray(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
