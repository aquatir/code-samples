package codesample.leetcode.medium;

/**
 * 649. Dota2 Senate — https://leetcode.com/problems/dota2-senate/description
 */
public class _649_Dota2Senate {
    public String predictPartyVictory(String senate) {
        // is always removing the next guy the best strategy?

        var s = new StringBuilder(senate);

        while (!isMono(s)) {
            int i = 0;
            while (i < s.length()) {
                var ch = s.charAt(i);

                if (ch == 'R') {
                    // find next D and remove it if possible, circling around if needed
                    if (deleteNext(i, s, 'D')) {
                        return "Radiant";
                    }
                }

                if (ch == 'D') {
                    // find next D and remove it if possible
                    if (deleteNext(i, s, 'R')) {
                        return "Dire";
                    }
                }
                i++;
            }
        }

        // s has only 1 of the 2 possible chars, so check any like first in this case
        return s.charAt(0) == 'R' ? "Radiant" : "Dire";
    }

    /**
     * Deletes next specified character starting from position [i+1]
     * If it circles back to original character => there is nothing to delete => return true
     * indicating that one of the sides has won
     */
    private boolean deleteNext(int i, StringBuilder s, char d) {
        var j = i + 1;
        while (j < s.length() && s.charAt(j) != d) {
            j++;
        }
        if (j < s.length()) {
            s.deleteCharAt(j);
            return false;
        } else {
            var circleUntilIndex = j;
            j = 0;
            while (j < circleUntilIndex && s.charAt(j) != d) {
                j++;
            }
            if (j == circleUntilIndex) {
                return true;
            } else {
                s.deleteCharAt(j);
                return false;
            }
        }
    }

    /**
     * See if all characters in string builder are the same
     */
    private boolean isMono(StringBuilder s) {
        var r = false;
        var d = false;

        for (char ch : s.toString().toCharArray()) {
            if (ch == 'R') {
                r = true;
            } else {
                d = true;
            }

            if (r && d) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        var s = new _649_Dota2Senate();
        System.out.println(s.predictPartyVictory("DDRRR")); // expected: "Dire"
        System.out.println(s.predictPartyVictory("RDD")); // expected: Dire
    }

}
