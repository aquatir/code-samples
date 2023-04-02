package codesample.leetcode.medium;

/**
 * 151. Reverse Words in a String — https://leetcode.com/problems/reverse-words-in-a-string/description/
 */
public class _151_ReverseWordsInAString {

    public String reverseWords(String s) {

        // revert whole string => revert each word in string => result
        var revertedString = new StringBuilder(s.trim()).reverse().toString();
        var result = new StringBuilder();

        var wordStart = 0;
        for (int i = 0; i < revertedString.length(); i++) {
            if (revertedString.charAt(i) == ' ') {
                var revertedWord = revertWord(revertedString, wordStart, i);
                if (!revertedWord.isBlank()) {
                    result.append(revertedWord);
                    result.append(" ");
                }
                wordStart = i + 1;
            }
        }

        result.append(revertWord(revertedString, wordStart, revertedString.length()));
        return result.toString();
    }

    private String revertWord(String wholeString, int from, int to) {
        var trimmed = wholeString.substring(from, to).trim();
        return new StringBuilder(trimmed).reverse().toString();
    }

}
