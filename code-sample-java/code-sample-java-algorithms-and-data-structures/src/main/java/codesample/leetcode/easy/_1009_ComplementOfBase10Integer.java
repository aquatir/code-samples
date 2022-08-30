package codesample.leetcode.easy;

/**
 * 1009. Complement of Base 10 Integer — https://leetcode.com/problems/complement-of-base-10-integer/
 *
 * The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 *
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer n, return its complement.
 */
public class _1009_ComplementOfBase10Integer {

    public int bitwiseComplement(int n) {
        // find max bit
        // xor with only ones
        // return result
        // that's it?

        if (n == 0) {
            return 1;
        }

        int nCopy = n;
        int xorTarget = 0;

        while (nCopy != 0) {
            xorTarget = (xorTarget << 1) | 1;
            nCopy = nCopy >> 1;
        }

        return n ^ xorTarget;
    }

    public static void main(String[] args) {
        var s = new _1009_ComplementOfBase10Integer();
        System.out.println(s.bitwiseComplement(7)); //expected 0
        System.out.println(s.bitwiseComplement(5)); // expected 2
        System.out.println(s.bitwiseComplement(10)); // expected 5
    }
}
