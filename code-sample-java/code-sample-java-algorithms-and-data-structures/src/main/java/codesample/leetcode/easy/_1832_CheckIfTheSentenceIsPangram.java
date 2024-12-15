package codesample.leetcode.easy;

import java.util.HashSet;

/**
 * 1832. Check if the Sentence Is Pangram — https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/
 */
public class _1832_CheckIfTheSentenceIsPangram {
    public boolean checkIfPangram(String sentence) {
        var allCharsSet = new HashSet<Character>();
        for (char c: sentence.toCharArray()) {
            allCharsSet.add(c);
        }

        return allCharsSet.size() == 26;
    }
}
