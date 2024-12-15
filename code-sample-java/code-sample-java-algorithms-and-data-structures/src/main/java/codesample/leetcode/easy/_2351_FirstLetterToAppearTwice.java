package codesample.leetcode.easy;

import java.util.HashSet;

/**
 * 2351. First Letter to Appear Twice — https://leetcode.com/problems/first-letter-to-appear-twice/description/
 */
public class _2351_FirstLetterToAppearTwice {
    public char repeatedCharacter(String s) {
        var appeared = new HashSet<Character>();

        for (char c: s.toCharArray()) {
            if (appeared.contains(c)) {
                return c;
            } else {
                appeared.add(c);
            }
        }
        return 'a'; // to compile, never executed due to question condition of duplicate always existing
    }
}
