package codesample.leetcode.easy;

/**
 * 28. Find the Index of the First Occurrence in a String — https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
public class _28_FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {
        int needleSize = needle.length();
        int haystackSize = haystack.length();

        if (needleSize > haystackSize) {
            return -1;
        }

        int haystackIndex = 0;

        while (haystackIndex != haystackSize) {
            if (haystack.charAt(haystackIndex) == needle.charAt(0)) {
                // try to match the word

                if (haystackIndex + needleSize > haystackSize) {
                    return -1;
                } else {
                    int needleIndex = 0;
                    for (; needleIndex < needleSize; needleIndex++) {
                        if (haystack.charAt(haystackIndex + needleIndex) == needle.charAt(needleIndex)) {
                            // continue
                        } else {
                            break;
                        }
                    }
                    if (needleIndex == needleSize) {
                        return haystackIndex;
                    }
                }
            }


            // simply continue
            haystackIndex++;

        }

        return -1;
    }

    public static void main(String[] args) {
        var s = new _28_FindTheIndexOfTheFirstOccurrenceInAString();

        System.out.println(s.strStr("kekbutsad", "sad")); // expected: 6
    }
}
