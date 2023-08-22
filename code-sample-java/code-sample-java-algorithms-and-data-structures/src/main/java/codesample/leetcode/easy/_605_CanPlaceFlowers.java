package codesample.leetcode.easy;

/**
 * 605. Can Place Flowers — https://leetcode.com/problems/can-place-flowers/description/
 */
public class _605_CanPlaceFlowers {

    // very small brain super-brute-force solution
//    public boolean canPlaceFlowers(int[] flowerbed, int n) {
//        if (n == 0) {
//            return true;
//        }
//        int leftToPlans = n;
//
//        // basic case: look left & right and see if can plant
//        // edge case in front: can only look for this and next
//        // edge case in back: can only look at the back
//        //      plant greadily, because can't replant anyway
//
//        int size = flowerbed.length;
//
//        // for size 1: either don't need to plan anything
//        // or need to plan 1, and there is space
//        if (size == 1) {
//            return flowerbed[0] == 0 && n == 1;
//        }
//
//        // for size 2: either don't need to plan anything
//        // or need to plan 1 and both spaces are empty
//        if (size == 2) {
//            return flowerbed[0] == 0 && flowerbed[1] == 0 && n == 1;
//        }
//
//        // for sizes 3+
//        for (int i = 0; i < size; i++) {
//            if (i == 0) {
//                if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
//                    leftToPlans--;
//                    flowerbed[i] = 1;
//                }
//                // special case front
//            } else if (i == size - 1) {
//                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
//                    leftToPlans--;
//                    flowerbed[i] = 1;
//                }
//            } else {
//                if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
//                    leftToPlans--;
//                    flowerbed[i] = 1;
//                }
//            }
//
//            if (leftToPlans == 0) {
//                return true;
//            }
//        }
//
//        return leftToPlans == 0;
//    }

    // less of a smooth brain approach: check both left and right pot and handle the "very left" and "very right"
    // edge cases at the same time
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Only need to check if we can plant if the place is not occupied
            if (flowerbed[i] == 0) {

                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }
}
