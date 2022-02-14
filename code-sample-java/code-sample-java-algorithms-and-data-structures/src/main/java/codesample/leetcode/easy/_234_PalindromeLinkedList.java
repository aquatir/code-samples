package codesample.leetcode.easy;

/**
 * 234. Palindrome Linked List — https://leetcode.com/problems/palindrome-linked-list/
 * <p>
 * Given the head of a singly linked list, return true if it is a palindrome.
 */

public class _234_PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        // approach 1:
        // count number of nodes
        // revert until the middle. Reverts half of the list though
        // check one-by-one
        // Could you do it in O(n) time and O(1) space?

        // 1 -> 2 -> 3 -> 3 -> 2 -> 1
        // count: 6
        // revert until 6 / 2 = 3
        // 1 <- 2 <- 3 3 -> 2 -> 1 correct
        // 1 -> 2 -> 3 -> 2 -> 1
        // count: 5
        // revert until 5 / 2 = 2
        // 1 <- 2 skip 3 2 -> 1 correct
        // changes the list but will work.

        // What if we preserve the order of elements?
        // approach 2:

        int size = countSize(head);
        int half = size / 2;

        ListNode cur = head;
        ListNode prev = null;

        // revert first half
        for (int i = 0; i < half; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        ListNode headOfReverted = prev;

        // find beginning of the second half
        ListNode headOfOther = null;
        if (size % 2 == 0) {
            headOfOther = cur;
        } else {
            headOfOther = cur.next;
        }

        // compare all values
        while (headOfReverted != null && headOfOther != null) {
            if (headOfReverted.val != headOfOther.val) {
                return false;
            } else {
                headOfReverted = headOfReverted.next;
                headOfOther = headOfOther.next;
            }
        }

        return true;
    }

    public int countSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
