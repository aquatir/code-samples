package codesample.leetcode.easy;

/**
 * 1342. Number of Steps to Reduce a Number to Zero — https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/description/
 */
public class _1342_NumberOfStepsToReduceANumberToZero {
    class Solution {
        public int numberOfSteps(int num) {
            // odd = 1,3,5,etc

            int numberOfSteps = 0;
            while (num != 0) {
                numberOfSteps++;
                if ((num & 1) == 1) { // is odd
                    num -= 1;
                } else {
                    num /= 2;
                }
            }
            return numberOfSteps;
        }
    }
}
