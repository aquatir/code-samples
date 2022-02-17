package codesample.other.educative._03_fast_and_slow_pointers;

public class _03_CycleInACircularArray {
    public static boolean loopExists(int[] arr) {
        // for each number -> start fast and slow pointers.
        // we stop on
        // a) loop when slow == fast

        for (int i = 0; i < arr.length; i++) {
            boolean isLoopFromIndex = isLoopFromIndex(i, arr);
            if (isLoopFromIndex) {
                return true;
            }
        }

        return false;
    }

    public static boolean isLoopFromIndex(int from, int[] arr) {
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
        } while (slow != fast);

        return true;
    }

    public static int advanceIndex(int fromIndex, int[] arr) {
        boolean isForward = arr[fromIndex] > 0;
        int jumpValue = arr[fromIndex];

        int indexAfterJump = fromIndex + jumpValue;
        indexAfterJump = indexAfterJump % arr.length;
        if (indexAfterJump >= arr.length) {
            indexAfterJump = indexAfterJump % arr.length;
        }

        boolean isForwardAfterJump = arr[indexAfterJump] > 0;
        if (isForwardAfterJump != isForward) {
            return -1;
        } else {
            return indexAfterJump;
        }
    }


    public static void main(String[] args) {
        System.out.println(_03_CycleInACircularArray.loopExists(new int[]{1, 2, -1, 2, 2}));
        System.out.println(_03_CycleInACircularArray.loopExists(new int[]{2, 2, -1, 2}));
        System.out.println(_03_CycleInACircularArray.loopExists(new int[]{2, 1, -1, -2}));
    }
}
