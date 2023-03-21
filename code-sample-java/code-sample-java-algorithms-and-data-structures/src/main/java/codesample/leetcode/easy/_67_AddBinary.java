package codesample.leetcode.easy;

/**
 * 67. Add Binary https://leetcode.com/problems/add-binary/description/
 */
public class _67_AddBinary {
    public String addBinary(String a, String b) {
        var aCharArray = a.toCharArray();
        var bCharArray = b.toCharArray();

        var aMax = aCharArray.length;
        var bMax = bCharArray.length;

        StringBuilder result = new StringBuilder();

        var rem = false;

        int aIndex = aMax - 1;
        int bIndex = bMax - 1;

        while (aIndex >= 0 || bIndex >= 0) {
            int res = 0;
            if (aIndex >= 0) {
                res += toInt(aCharArray[aIndex]);
            }
            if (bIndex >= 0) {
                res += toInt(bCharArray[bIndex]);
            }

            if (rem) {
                res += 1;
            }

            if (res == 3) {
                result.append('1');
                rem = true;
            } else if (res == 2) {
                result.append('0');
                rem = true;
            } else if (res == 1) {
                result.append('1');
                rem = false;
            } else {
                result.append('0');
                rem = false;
            }
            aIndex--;
            bIndex--;
        }
        if (rem) {
            result.append('1');
        }

        return result.reverse().toString();
    }

    public int toInt(char a) {
        if (a == '1') {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        var s = new _67_AddBinary();

        System.out.println(s.addBinary("1010", "1011")); // expected: 10101
        System.out.println(s.addBinary("11", "1")); // expected: 100
    }
}
