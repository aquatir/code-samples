package codesample.leetcode.medium;

/**
 * 11. Container With Most Water — https://leetcode.com/problems/container-with-most-water/description
 */
public class _11_ContainerWithMostWater {

    // approach 2: intuition the result will be AS FAR as possible from each other
    // between two highest lines.
    //      set left and right pointers
    //      on each step calculate the area, then move the smallest of height closer to the end
    // return when nothing is left

    public int maxArea(int[] height) {

        int max = 0;
        int n = height.length;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            max = Math.max(max, area(height, left, right));

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return max;
    }


    // find two lines that form an area that is MAX
    // if two lines and i and j then the area is
    // (j - i) * max.min(height(i), height(j));

    // approach 1: brute force — time limit exceeded. It's quadratic
    // Select one line, calculate the area for the rest of the lines
    // return max
//    public int maxArea(int[] height) {
//        int max = 0;
//        int n = height.length;
//
//        for (int left = 0; left < n; left++) {
//            for (int right = left + 1; right < n; right++) {
//                var area = area(height, left, right);
//                max = Math.max(max, area);
//            }
//        }
//
//        return max;
//    }

    private int area(int[] height, int left, int right) {
        return (right - left) * Math.min(height[left], height[right]);
    }
}
