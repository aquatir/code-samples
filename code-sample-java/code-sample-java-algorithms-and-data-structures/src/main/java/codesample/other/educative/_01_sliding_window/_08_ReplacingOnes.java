package codesample.other.educative._01_sliding_window;

public class _08_ReplacingOnes {
    public static int findLength(int[] arr, int k) {

        int windowStart = 0;
        int max = 0;
        int maxOnesCount = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1) {
                maxOnesCount++;
            }

            // maybe shrink
            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (arr[windowStart] == 1) {
                    maxOnesCount--;
                }
                windowStart++;
            }

            // max
            max = Math.max(max, windowEnd - windowStart + 1);

        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(_08_ReplacingOnes.findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));            // exp: 6
        System.out.println(_08_ReplacingOnes.findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));      // exp: 9
    }
}
