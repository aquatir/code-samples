package codesample.leetcode.easy;

/**
 * 202. Happy Number — https://leetcode.com/problems/happy-number/
 *
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
public class _202_HappyNumber {

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = next(slow);
            fast = next(next(fast)); // can optimize
        } while (slow != fast); // Both would become equal either when cycle is found or when both are 1 (happy case)

        return slow == 1;
    }

    public int next(int current) {
        int sum = 0;
        while (current != 0) {
            int lastDigit = current % 10;
            sum += lastDigit * lastDigit;
            current = current / 10;
        }

        return sum;
    }
}
