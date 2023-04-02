package codesample.leetcode.easy;

/**
 * 557. Reverse Words in a String III — https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 */
public class _557_ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        var result = new StringBuilder();
        var wordStart = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                var revertedWord = revertWord(s, wordStart, i);
                if (!revertedWord.isBlank()) {
                    result.append(revertedWord);
                    result.append(" ");
                }
                wordStart = i + 1;
            }
        }

        result.append(revertWord(s, wordStart, s.length()));
        return result.toString();
    }

    private String revertWord(String wholeString, int from, int to) {
        var trimmed = wholeString.substring(from, to).trim();
        return new StringBuilder(trimmed).reverse().toString();
    }
}
