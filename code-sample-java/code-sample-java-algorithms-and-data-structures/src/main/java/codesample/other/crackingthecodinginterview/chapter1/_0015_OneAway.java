package codesample.other.crackingthecodinginterview.chapter1;

/**
 * There are three types of edits that can be performed on strings:
 * - insert a character
 * - remove a character
 * - replace a character
 * Given two string, write a function to check if they are one edit away
 */
public class _0015_OneAway {

    public boolean isOneEditAtMostAway(String str1, String str2) {
        // if length difference is more than one => false
        // if one of string is shorter => only remove/insert is allowed => there could be one difference, but we move a carrier
        // instead of fixing it
        // if strings are the same length => can have 1 edit at most. Iterate and find edits. If more than one => return false

        var len1 = str1.length();
        var len2 = str2.length();

        // return if length diff is more than one
        var lenDiff = Math.abs(len1 - len2);
        if (lenDiff > 1) {
            return false;
        } else if (lenDiff == 0) {
            return isAtMostOneReplaceAway(str1, str2);
        } else {
            // one is longer than the other
            String shortStr;
            String longStr;
            if (len1 > len2) {
                longStr = str1;
                shortStr = str2;
            } else {
                longStr = str2;
                shortStr = str1;
            }

            return isAtMostOneInsertAway(shortStr, longStr);
        }
    }

    private boolean isAtMostOneInsertAway(String shortStr, String longStr) {
        assert Math.abs(shortStr.length() - longStr.length()) == 1;
        var shortCharArray = shortStr.toCharArray();
        var longCharArray = longStr.toCharArray();

        var shortIndex = 0;
        var longIndex = 0;

        while (shortIndex < shortStr.length() && longIndex < longStr.length()) {
            if (shortCharArray[shortIndex] == longCharArray[longIndex]) {
                shortIndex++;
            }
            longIndex++;
            if (Math.abs(longIndex - shortIndex) > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isAtMostOneReplaceAway(String str1, String str2) {
        assert str1.length() == str2.length();
        var firstCharArray = str1.toCharArray();
        var secondCharArray = str2.toCharArray();
        var numberOfDiffs = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (firstCharArray[i] != secondCharArray[i]) {
                numberOfDiffs++;
            }
            if (numberOfDiffs > 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var s = new _0015_OneAway();

        System.out.println(s.isOneEditAtMostAway("pale", "ple"));       // true
        System.out.println(s.isOneEditAtMostAway("pale", "pales"));     // true
        System.out.println(s.isOneEditAtMostAway("pale", "bale"));      // true
        System.out.println(s.isOneEditAtMostAway("pale", "bake"));      // false
        System.out.println(s.isOneEditAtMostAway("pale", "palemd"));    // false
    }
}
