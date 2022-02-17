package codesample.leetcode.medium;

public class _457_CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean isLoopFromIndex = isLoopFromIndex(i, nums);
            if (isLoopFromIndex) {
                return true;
            }
        }

        return false;
    }

    public boolean isLoopFromIndex(int from, int[] arr) {
        boolean isForward = arr[from] >= 0;
        if (arr[from] == 0) {
            return false;
        }

        // b) going back in direction.
        // c) ???
        // Must handle jumping over the array
        // also: negative indexes.
        int fast = from;
        int slow = from;

        do {
            int newSlow = advanceIndex(slow, arr);
            if (newSlow == slow) {
                return false;
            }

            slow = newSlow;
            int newFast = advanceIndex(fast, arr);

            if (newFast == -1) {
                return false;
            }

            int newNewFast = advanceIndex(newFast, arr);
            if (newFast == newNewFast) {
                return false;
            }
            fast = newNewFast;

            // -1 denoted as "no loop"
            if (fast == -1 || slow == -1) {
                return false;
            }
        } while(slow != fast);

        return true;
    }

    public int advanceIndex(int fromIndex, int[] arr) {
        boolean isForward = arr[fromIndex] > 0;
        int jumpValue = arr[fromIndex];

        int indexAfterJump = fromIndex + jumpValue;
        indexAfterJump = indexAfterJump % arr.length;
        if (indexAfterJump < 0) {
            indexAfterJump = indexAfterJump + arr.length;
        }

        boolean isForwardAfterJump = arr[indexAfterJump] > 0;
        if (isForwardAfterJump != isForward) {
            return -1;
        } else {
            return indexAfterJump;
        }
    }

    public static void main(String[] args) {
        var s = new _457_CircularArrayLoop();
        System.out.println(s.circularArrayLoop(new int[]{-1, -2, -3, -4, -5})); // exp: false
        System.out.println(s.circularArrayLoop(new int[]{-2, -3, -9})); // exp: false
    }
}
