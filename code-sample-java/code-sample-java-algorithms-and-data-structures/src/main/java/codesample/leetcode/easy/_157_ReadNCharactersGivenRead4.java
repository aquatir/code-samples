package codesample.leetcode.easy;

/**
 * 157. Read N Characters Given Read4 — https://leetcode.com/problems/read-n-characters-given-read4/description/
 */
public class _157_ReadNCharactersGivenRead4 {

    public class Reader4 {
        public int read4(char[] buf) {
            return 0; // mock
        }
    }

    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {

            char[] buf4 = new char[4];
            var index = 0;
            var totalRead = 0;
            while (n > 0) {
                var res = read4(buf4);
                var readUntil = Math.min(res, n);
                for (int i = 0; i < readUntil; i++) {
                    buf[index++] = buf4[i];
                }

                totalRead += readUntil;
                n -= res;
                if (res < 4) {
                    break;
                }
            }

            return totalRead;
        }
    }
}
