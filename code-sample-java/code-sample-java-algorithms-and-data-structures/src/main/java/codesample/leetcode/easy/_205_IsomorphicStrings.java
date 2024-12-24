package codesample.leetcode.easy;

import java.util.HashMap;

/**
 * 205. Isomorphic Strings — https://leetcode.com/problems/isomorphic-strings/description/
 */
public class _205_IsomorphicStrings {

    // replace each character with a transformation
    // if we replace each char with an index where it was first met, two string will be the same
    // e.g. "egg" and "abb" will both become "0 1 1"
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        return transform(s).equals(transform(t));
    }

    private String transform(String s) {
        var sb = new StringBuilder();
        var firstIndex = new HashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);

            if (!firstIndex.containsKey(ch)) {
                firstIndex.put(ch, i);
            }

            sb.append(firstIndex.get(ch));
            sb.append(" ");
        }

        return sb.toString();
    }



    // one-pass. Compare mapping from both sides
    public boolean isIsomorphicOnePass(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var sToT = new HashMap<Character, Character>();
        var tToS = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {
            var sChar = s.charAt(i);
            var tChar = t.charAt(i);

            if (!sToT.containsKey(sChar) && !tToS.containsKey(tChar)) {
                sToT.put(sChar, tChar);
                tToS.put(tChar, sChar);
            } else {

                var sMapped = sToT.get(sChar);
                var tMapped = tToS.get(tChar);

                if (sMapped == null || tMapped == null || sMapped != tChar || tMapped != sChar) {
                    return false;
                }
            }
        }
        return true;
    }

    // count frequencies. Compare that each frequency in s is also in t
    // — doesnt work for e.g. s="bbbaaaba", t = "aaabbbba"


    // try replace iterativly by keeping the map of replacement.
    // if need to replace, but replacement is wrong, return false.
    //   this only work if we try it for both s => t and t => s.
    // e.g. "foo"/"bar" vs "bar"/"foo"
    public boolean isIsomorphicTwoPass(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        return canReplace(s, t) && canReplace(t, s);
    }

    private boolean canReplace(String s, String t) {
        // char in t to char in s
        var replacements = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            var charAtS = s.charAt(i);
            var charAtT = t.charAt(i);

            if (replacements.containsKey(charAtT)) {
                charAtT = replacements.get(charAtT);
                if (charAtT != charAtS) {
                    return false;
                }
            } else {
                replacements.put(charAtT, charAtS);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var s = new _205_IsomorphicStrings();
        System.out.println(s.isIsomorphic("foo", "bar")); // expected: false
    }
}
