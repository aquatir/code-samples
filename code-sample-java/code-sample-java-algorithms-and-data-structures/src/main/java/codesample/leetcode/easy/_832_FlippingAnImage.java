package codesample.leetcode.easy;

/**
 * 832. Flipping an Image — https://leetcode.com/problems/flipping-an-image/
 *
 * Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.
 *
 * For example, flipping [1,1,0] horizontally results in [0,1,1].
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
 *
 * For example, inverting [0,1,1] results in [1,0,0].
 */
public class _832_FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;

        // flip == swap all bits
        // invert == xor with ones only;

        // flip
        for (int row = 0; row < n; row++) {
            // [1, 2, 3] and [1, 2, 3, 4]
            // [1, 2, 3]; n = 3. 3/2 = 1 => correct

            for (int j = 0; j < n / 2; j++) { // check
                int temp = image[row][j];
                image[row][j] = image[row][n - j - 1];
                image[row][n - j - 1] = temp;
            }
        }

        // invert
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                image[i][j] = 1 ^ image[i][j];
            }
        }

        return image;
    }
}
