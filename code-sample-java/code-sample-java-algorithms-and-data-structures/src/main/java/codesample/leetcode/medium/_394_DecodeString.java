package codesample.leetcode.medium;

import java.util.ArrayDeque;

/**
 * 394. Decode String — https://leetcode.com/problems/decode-string/
 */
public class _394_DecodeString {
//    public String decodeString(String s) {
//        // approach 1:
//        //  find a number and a '[', then find another ']',
//        //     append result recursively


//        // approach 3: just use 1 stack
//        //  only decode when we know that there IS something to decode
//        var stack = new ArrayDeque<Character>();
//
//        for (int i = 0; i < s.length(); i++) {
//
//            // have to decode here
//            if (s.charAt(i) == ']') {
//                var decodedString = new ArrayList<Character>();
//
//                while (stack.peek() != '[') {
//                    decodedString.add(stack.pop());
//                }
//                // pop [ from the stack
//                stack.pop();
//
//
//                int base = 1;
//                int k = 0;
//                // get the number k
//                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
//                    k = k + (stack.pop() - '0') * base;
//                    base *= 10;
//                }
//
//                // decode k[decodedString], by pushing decodedString k times into stack
//                while (k != 0) {
//                    for (int j = decodedString.size() - 1; j >= 0; j--) {
//                        stack.push(decodedString.get(j));
//                    }
//                    k--;
//                }
//            }
//            // push the current character to stack
//            else {
//                stack.push(s.charAt(i));
//            }
//        }
//        // get the result from stack
//        char[] result = new char[stack.size()];
//        for (int i = result.length - 1; i >= 0; i--) {
//            result[i] = stack.pop();
//        }
//        return new String(result);
//    }

    // approach 2:
    //   maybe use a stack?
    //   put number into one stack when encountered the closing ], add result
    //   how to handle e.g. 3[a2[c]] ?
    //     could do pop-append-push
    // 3[a2[c]]
    // 3, 2
    // a
    // c -> closing bracket => pop number & all chars => turn it into cc. Now append
    //  this to the last char in char array
    // 3
    // acc
    // accaccacc

    public String decodeString(String s) {
        var countStack = new ArrayDeque<Integer>();
        var stringStack = new ArrayDeque<StringBuilder>();
        var currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                var decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }

    public static void main(String[] args) {
        var s = new _394_DecodeString();
        System.out.println(s.decodeString("3[a]2[bc]")); // expected: "aaabcbc"
        System.out.println(s.decodeString("3[a2[c]]"));  // expected: "accaccacc"
        System.out.println(s.decodeString("2[abc]3[cd]ef")); // expected: "abcabccdcdcdef"
        System.out.println(s.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef")); // expected: "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
    }


}
