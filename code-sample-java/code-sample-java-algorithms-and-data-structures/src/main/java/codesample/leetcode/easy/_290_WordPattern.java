package codesample.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 290. Word Pattern — https://leetcode.com/problems/word-pattern/description/
 */
public class _290_WordPattern {
    public boolean wordPattern(String pattern, String s) {

        // count character to word mapping
        var letterToWordMapping = new HashMap<Character, String>();

        // prevent the same word to be mapped to 2 different characters
        var seenWords = new HashSet<String>();
        var words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            var ch = pattern.charAt(i);
            if (!letterToWordMapping.containsKey(ch)) {
                if (seenWords.contains(words[i])) {
                    return false;
                }
                letterToWordMapping.put(ch, words[i]);
                seenWords.add(words[i]);
            } else {
                if (!letterToWordMapping.get(ch).equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
