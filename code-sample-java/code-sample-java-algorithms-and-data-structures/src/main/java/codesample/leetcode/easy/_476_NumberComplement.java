package codesample.leetcode.easy;

/**
 * 476. Number Complement — https://leetcode.com/problems/number-complement/
 * <p>
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 * <p>
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer num, return its complement.
 */
public class _476_NumberComplement {
    public int findComplement(int num) {
        // find max bit
        // xor with only ones
        // return result
        // that's it?

        if (num == 0) {
            return 1;
        }

        int nCopy = num;
        int xorTarget = 0;

        while (nCopy != 0) {
            xorTarget = (xorTarget << 1) | 1;
            nCopy = nCopy >> 1;
        }

        return num ^ xorTarget;
    }
}
