package codesample.other.crackingthecodinginterview.chapter1;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters. For example, the string
 * aabcccccaaa would become a2b1c5a3. If "compressed" string would not become smaller than the original string, your method,
 * should return the original string. You can assume the string has only uppercase and lowercase letters (a-z)
 */
public class _0016_StringCompression {
    public String compressString(String str) {
        // get a StringBuilder to hold result.
        // Iterate over all characters, remembering the last one
        // if new character is the same as last => increase number of entries of last character
        // else => add compressed result, reset counter
        //  Take care to include the last elements of an iteration in all cases

        if (str.length() < 2) {
            return str;
        }
        var result = new StringBuilder();
        var prevChar = str.charAt(0);
        var numOfRepeats = 0;

        for (var ch: str.toCharArray()) {
            if (ch == prevChar) {
                numOfRepeats++;
            } else {
                result.append(prevChar).append(numOfRepeats);
                prevChar = ch;
                numOfRepeats = 1;
            }
        }

        result.append(prevChar).append(numOfRepeats);

        var resultAsStr = result.toString();
        if (resultAsStr.length() < str.length()) {
            return resultAsStr;
        } else {
            return str;
        }
    }

    public static void main(String[] args) {
        var s = new _0016_StringCompression();

        System.out.println(s.compressString("aabcccccaaa"));    // a2b1c5a3
        System.out.println(s.compressString("aaa"));            // a3
        System.out.println(s.compressString(""));               // ""
        System.out.println(s.compressString("aaaAAA"));         // ""a3A3
        System.out.println(s.compressString("aabb"));           // "aabb"
    }
}
