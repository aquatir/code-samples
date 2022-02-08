package codesample.other.educative._01_sliding_window;

import java.util.HashMap;
import java.util.Map;

public class _07_CharacterReplacement {
    public static int findLength(String str, int k) {


        // input: string -> char[] would be easier
        // k - number of letter we could replace.

        // k == 26 -> whose sting
        // k == 0 -> just longest substring of uniques
        // if (str is null or has 1 char -> return 0 or 1)

        int windowStart = 0;
        int charMaxRepeats = 0;
        int curMax = 0;
        char[] chars = str.toCharArray();
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            char charAtEnd = chars[windowEnd];
            freqMap.put(charAtEnd, freqMap.getOrDefault(charAtEnd, 0) + 1);
            charMaxRepeats = Math.max(charMaxRepeats, freqMap.get(charAtEnd));

            // possible shrink window
            if (windowEnd - windowStart + 1 - charMaxRepeats > k) {
                char charAtStart = chars[windowStart];
                freqMap.put(charAtStart, freqMap.get(charAtStart) - 1);
                windowStart++;
            }
            // calculate max
            curMax = Math.max(curMax, windowEnd - windowStart + 1);
        }

        return curMax;
    }

    public static void main(String[] args) {
        System.out.println(_07_CharacterReplacement.findLength("aabccbb", 2));  // exp: 5
        System.out.println(_07_CharacterReplacement.findLength("abbcb", 1));    // exp: 4
        System.out.println(_07_CharacterReplacement.findLength("abccde", 1));   // exp: 3
    }
}
