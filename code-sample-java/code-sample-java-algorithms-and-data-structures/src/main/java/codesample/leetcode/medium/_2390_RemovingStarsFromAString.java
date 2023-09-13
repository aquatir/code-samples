package codesample.leetcode.medium;

/**
 * 2390. Removing Stars From a String — https://leetcode.com/problems/removing-stars-from-a-string/description/
 */
public class _2390_RemovingStarsFromAString {
    public String removeStars(String s) {
        var charArray = new char[s.length()];
        int nextIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);

            if (ch != '*') {
                charArray[nextIndex] = ch;
                nextIndex++;
            } else {
                nextIndex--;
            }
        }

        var res = new char[nextIndex];
        for (int i = 0; i < nextIndex; i++) {
            res[i] = charArray[i];
        }
        return new String(res);
    }

    public static void main(String[] args) {
        var s = new _2390_RemovingStarsFromAString();

        System.out.println(s.removeStars("leet**cod*e")); // expected "lecoe"
    }
}
