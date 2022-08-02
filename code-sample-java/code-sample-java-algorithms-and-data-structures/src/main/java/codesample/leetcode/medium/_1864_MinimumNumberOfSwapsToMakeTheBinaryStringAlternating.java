package codesample.leetcode.medium;

/**
 * 1864. Minimum Number of Swaps to Make the Binary String Alternating — https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-binary-string-alternating/
 *
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 *
 */
public class _1864_MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {
    public int minSwaps(String s) {
        int n = s.length();

        int zeroes = 0;
        int ones = 0;

        for (char ch: s.toCharArray()) {
            if (ch == '0') {
                zeroes++;
            } else {
                ones++;
            }
        }

        // alterate => either start and end with the same e.g.
        // 101, 01010 => (diff = 1)
        // or start with one change with another
        // 10, 0101.
        // WE NEED SWAPS

        if (n % 2 == 0) {
            if (zeroes != ones) {
                return -1;
            }
        } else {
            if (Math.abs(zeroes - ones) != 1) {
                return -1;
            }
        }

        // now... how many swaps do we need?
        // 110 -> 1 swap
        // 11001 -> 10101 -> 1 swap

        // start with 0
        if (n % 2 == 0) {
            // for this case => alterate between 0 and 1 and see both results
            char nextExpectedSymbol = '0';
            int numberOfMissmatches = 0;
            for (char ch: s.toCharArray()) {
                if (ch != nextExpectedSymbol) {
                    numberOfMissmatches++;
                }
                nextExpectedSymbol = nextExpectedSymbol == '0' ? '1' : '0';
            }
            int maxStartWithZero = numberOfMissmatches / 2 + numberOfMissmatches % 2;

            // start with 1
            nextExpectedSymbol = '1';
            numberOfMissmatches = 0;
            for (char ch: s.toCharArray()) {
                if (ch != nextExpectedSymbol) {
                    numberOfMissmatches++;
                }
                nextExpectedSymbol = nextExpectedSymbol == '0' ? '1' : '0';
            }
            int maxStartWithOne = numberOfMissmatches / 2 + numberOfMissmatches % 2;

            // the min is the result;
            return Math.min(maxStartWithOne, maxStartWithZero);
        } else {
            // for this result definitely start with the one which is larger
            int numberOfMissmatches = 0;
            char nextExpectedSymbol = '0';
            if (ones > zeroes) {
                nextExpectedSymbol = '1';
            }
            for (char ch: s.toCharArray()) {
                if (ch != nextExpectedSymbol) {
                    numberOfMissmatches++;
                }
                nextExpectedSymbol = nextExpectedSymbol == '0' ? '1' : '0';
            }
            return numberOfMissmatches / 2 + numberOfMissmatches % 2;
        }
    }

    public static void main(String[] args) {
        var s = new _1864_MinimumNumberOfSwapsToMakeTheBinaryStringAlternating();

        var res1 = s.minSwaps("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001");
        System.out.println(res1); // expected: 65

        var res2 = s.minSwaps("1110000000100001010100101010000101010101001000001110101000010111101100000111110001000111010111101100001100001001100101011110100011111100000000100011111011110111111011110111010100111101011111111101101100101010110000011110110100101111000100000001100000");
        System.out.println(res2); // expected 60
    }
}
