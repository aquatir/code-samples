package codesample.leetcode.medium;

/**
 * 443. String Compression — https://leetcode.com/problems/string-compression/
 */
public class _443_StringCompression {

    // approach 2: like approach 1 below, but with in-place update
    // instead of adding values to StringBuilder, keep the writePointer
    public int compress(char[] chars) {

        int writePointer = 0;
        char prev = chars[0];
        int numberOfCurChar = 1;

        for (int i = 1; i < chars.length; i++) {
            var ch = chars[i];
            if (prev == ch) {
                numberOfCurChar++;
            } else {
                writePointer = update(chars, writePointer, prev, numberOfCurChar);
                prev = ch;
                numberOfCurChar = 1;
            }
        }
        // the last char is not processed
        writePointer = update(chars, writePointer, prev, numberOfCurChar);
        return writePointer;
    }

    private int update(char[] chars, int writePointer, char prev, int numberOfCurChar) {
        if (numberOfCurChar == 1) {
            chars[writePointer] = prev;
            return ++writePointer;
        } else {
            chars[writePointer] = prev;
            ++writePointer;
            var numericStr = String.valueOf(numberOfCurChar);
            for (int i = 0; i < numericStr.length(); i++) {
                chars[writePointer] = numericStr.charAt(i);
                writePointer++;
            }
            return writePointer;
        }
    }

    // approach 1: not constant space with StringBuilder

    // put result in StringBuilder
    // iterate over char array
    // remember the nuber of same characters
    // if another char is met => find what to write into SB
    //  don't forget about the last char
    // result will be the lenght of SB
    // put all chars from SB to chars
//    public int compress(char[] chars) {
//
//        var res = new StringBuilder();
//        char prev = chars[0];
//        int numberOfCurChar = 1;
//
//        for (int i = 1; i < chars.length; i++) {
//            var ch = chars[i];
//            if (prev == ch) {
//                numberOfCurChar++;
//            } else {
//                update(res, prev, numberOfCurChar);
//                prev = ch;
//                numberOfCurChar = 1;
//            }
//        }
//        // the last char is not processed
//        update(res, prev, numberOfCurChar);
//
//        // compute result;
//
//        var resAsString = res.toString();
//        var len = resAsString.length();
//        for (int i = 0; i < len; i++) {
//            chars[i] = resAsString.charAt(i);
//        }
//        return len;
//    }
//
//    private void update(StringBuilder res, char prev, int numberOfCurChar) {
//        if (numberOfCurChar == 1) {
//            res.append(prev);
//        } else {
//            res.append(prev);
//            res.append(numberOfCurChar);
//        }
//    }
}
