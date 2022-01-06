package codesample.leetcode.medium;

/**
 * 91. Decode Ways — https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 */
public class _91_DecodeWays {
    static class Solution {

        // number of ways to decode string
        //
        // Assume we know the number of correct ways to decode up until (k-1)
        // What's the number of correct decodes at k?
        //
        // if number at k can be decoded, then the number of decodes for k should be incremented by decode(k-1)
        // if number at k and k-1 form a 2-char number, then number of decodes for k should be incremented by decode(k-2)
        //
        public int numDecodings(String s) {

            int n = s.length();

            int[] waysToDecode = new int[n + 1];


            // we never incremet so assume that "" could be decoded.
            // it won't matter in future.
            waysToDecode[0] = 1;
            waysToDecode[1] = s.charAt(0) == '0' ? 0 : 1;

            for (int i = 2; i <= n; i++) {
                int one = Integer.parseInt(s.substring(i - 1, i));
                int two = Integer.parseInt(s.substring(i - 2, i));

                if (one >= 1 && one <= 9) {
                    waysToDecode[i] += waysToDecode[i-1];
                }
                if (two >= 10 && two <= 26) {
                    waysToDecode[i] += waysToDecode[i-2];
                }
            }

            return waysToDecode[n];
        }
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.numDecodings("27")); // expected = 1
    }
}
