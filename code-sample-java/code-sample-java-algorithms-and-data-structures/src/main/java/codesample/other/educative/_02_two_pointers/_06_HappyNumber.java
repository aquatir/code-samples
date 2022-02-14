package codesample.other.educative._02_two_pointers;

public class _06_HappyNumber {
    public static boolean find(int num) {
        //
        // 1. Take a number, split it in digits
        // 2. for each such split -> take the squares and sum them
        // 3. if result is 1 => happy
        // 4. if not -> continue to step 1
        // The process either ends some time on step 3 or it never stops.
        // In the latter case we have a cycle.
        //  - How to split number by digits?
        //      - a function which takes a number and return a list would do
        //  - How do we know if the process is in the cycle already
        //      - solution 1: create a set of results. If result was met before -> we have a cycle
        //        - easy to implement, but uses unknown amounth of extra memory (how to estimate it even?)
        //      - solution 2: some fast + slow pointers schenaninags?
        //        - When do we stop the cycle? After n iterations? Why?
        //          - can we mathematically calculate the amount of iterations required?
        //          - can we just stupidly calculate the two steps and see?
        //          - if the cycle exist, fast is going to be equal to slow sooner or later
        //          - if it doesn't we must exist on 1
        //          - are there numbers that have an unlimited number of cycle but never meet a repeat?

        int slow = num;
        int fast = num;

        do {
            slow = next(slow);
            fast = next(next(fast)); // can optimize
        } while (slow != fast);

        return slow == 1;
    }

    public static int next(int current) {
        int sum = 0;
        while (current != 0) {
            int lastDigit = current % 10;
            sum += lastDigit * lastDigit;
            current = current / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(_06_HappyNumber.find(23)); // exp: true
        System.out.println(_06_HappyNumber.find(12)); // exp: false
    }
}
