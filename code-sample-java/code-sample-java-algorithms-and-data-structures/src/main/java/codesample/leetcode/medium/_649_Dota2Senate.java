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
                    var j = i+1;
                    while (j < s.length() && s.charAt(j) != 'D') {
                        j++;
                    }
                    if (j < s.length()) {
                        s.deleteCharAt(j);
                    } else {
                        var circleUntilIndex = j;
                        j = 0;
                        while (j < circleUntilIndex && s.charAt(j) != 'D') {
                            j++;
                        }
                        if (j == circleUntilIndex) {
                            return "Radiant";
                        } else {
                            s.deleteCharAt(j);
                        }
                    }
                }

                if (ch == 'D') {
                    // find next D and remove it if possible
                    var j = i+1;
                    while (j < s.length() && s.charAt(j) != 'R') {
                        j++;
                    }
                    if (j < s.length()) {
                        s.deleteCharAt(j);
                    } else {
                        var circleUntilIndex = j;
                        j = 0;
                        while (j < circleUntilIndex && s.charAt(j) != 'R') {
                            j++;
                        }
                        if (j == circleUntilIndex) {
                            return "Dire";
                        } else {
                            s.deleteCharAt(j);
                        }
                    }
                }
                i++;
            }
        }

        // only one letter will remake
        return s.charAt(s.length() - 1) == 'R' ? "Radiant" : "Dire";
    }

    private boolean isMono(StringBuilder s) {
        var r = false;
        var d = false;

        for (char ch: s.toString().toCharArray()) {
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
