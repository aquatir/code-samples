package codesample.leetcode.easy;

/**
 * 744. Find Smallest Letter Greater Than Target — https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 * <p>
 * Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.
 * <p>
 * Note that the letters wrap around.
 * <p>
 * For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
 */
public class _744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        var left = 0;
        var right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right-left)/2;

            char atMid = letters[mid];

            if ( (target - atMid) >= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left == letters.length) {
            return letters[0];
        } else {
            return letters[left];
        }
    }

    public static void main(String[] args) {
        var s = new _744_FindSmallestLetterGreaterThanTarget();
        System.out.println(s.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c')); // expected: f
    }
}
