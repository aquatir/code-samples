package codesample.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 58. Length of Last Word — https://leetcode.com/problems/length-of-last-word/
 *
 * Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 */
public class _58_LengthOfLastWord {

    static class Solution {

        // going from behind
        public int lengthOfLastWord(String s) {
            boolean inWord = false;
            int n = s.length();
            int size = 0;

            for (int i = 0; i < n; i++) {
                char cur = s.charAt(n - 1 - i);

                if (cur == ' ') {
                    if (inWord) {
                        return size;
                    } else {
                        //
                    }
                } else {
                    inWord = true;
                    size++;
                }
            }

            return size;
        }

        // marching forward
        public int lengthOfLastWordAll(String s) {

            int lastWord = 0;
            int curWord = 0;

            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);

                if (cur != ' ') {
                    curWord++;
                } else {
                    lastWord = curWord == 0 ? lastWord : curWord;
                    curWord = 0;
                }
            }

            return curWord == 0 ? lastWord : curWord;
        }
    }

}
