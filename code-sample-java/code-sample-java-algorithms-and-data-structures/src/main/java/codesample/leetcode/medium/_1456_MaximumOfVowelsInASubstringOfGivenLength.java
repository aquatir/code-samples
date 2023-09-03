package codesample.leetcode.medium;

import java.util.HashSet;

/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length — https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 */
public class _1456_MaximumOfVowelsInASubstringOfGivenLength {


    // approach 1: just iterate and keep the number of vowels encountered
    // approach 2: TBA. Sliding window of size k
    public int maxVowels(String s, int k) {

        var vowels = new HashSet<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int left = 0;
        int right = k - 1;

        int curNumber = 0;
        int max = 0;

        for (int i = 0; i <= right; i++) {
            var ch = s.charAt(i);
            if (vowels.contains(ch)) {
                curNumber++;
                max = Math.max(max, curNumber);
            }
        }

        while (right != (s.length() - 1)) {
            // try to move right;
            right++;
            var rightCh = s.charAt(right);
            if (vowels.contains(rightCh)) {
                curNumber++;
            }

            // move left because we have moved write but only if required
            if (right - left == k) {
                if (vowels.contains(s.charAt(left))) {
                    curNumber--;
                }
                left++;
            }
            max = Math.max(max, curNumber);
        }

        return max > k ? k : max;
    }

    public static void main(String[] args) {
        var s = new _1456_MaximumOfVowelsInASubstringOfGivenLength();
        System.out.println(s.maxVowels("weallloveyou", 7));
        System.out.println(s.maxVowels("ramadan", 2));
    }
}
