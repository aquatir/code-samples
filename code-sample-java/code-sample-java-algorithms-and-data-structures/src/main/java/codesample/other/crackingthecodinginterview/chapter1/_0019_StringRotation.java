package codesample.other.crackingthecodinginterview.chapter1;

/**
 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2,
 * write code to check if s2 is a rotation of s1 using only one call to isSubstring
 * (e.g. "waterbottle" is a rotation of "erbottlewat")
 */
public class _0019_StringRotation {
    public boolean isStringRotation(String s1, String s2) {
        // "rotation" -> same string, but elements are shifted around
        // Iterate until the end then substring the rest:
        // 1. start indexes on each string
        // 2. iterate both s1 and s2 while a character matches, count No of matches
        // 3. If a char does not match, reset s1 index, always continue s2. Do this until the end of s2.
        // 4. move s1 by No of matches, skip No of matches as s2 => compare two string
        //  if match => one is a rotation, if not => not a rotation
        if (s1.length() != s2.length()) {
            return false;
        }

        var s1Index = 0;
        var s2Index = 0;
        var numberOfMatches = 0;

        while (s2Index != s2.length()) {
            if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
                numberOfMatches++;
                s1Index++;
            } else {
                s1Index = 0;
                numberOfMatches = 0;
            }
            s2Index++;
        }

        if (numberOfMatches == s1.length()) {
            return true;
        }
        var s2Cut = s2.substring(0, s2.length() - numberOfMatches);

        return isSubstring(s1, s2Cut);
    }

    /**
     * Checks if s2 is a substring of s1
     */
    public boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }

    public static void main(String[] args) {
        var s = new _0019_StringRotation();

        System.out.println(s.isStringRotation("waterbottle", "erbottlewat"));   // true
        System.out.println(s.isStringRotation("waterbottle", "ebrottlewat"));   // false
        System.out.println(s.isStringRotation("kekwait", "waitkek"));           // true
    }
}
