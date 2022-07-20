package codesample.leetcode.easy;

/**
 * 206. Reverse Linked List — https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class _206_ReverseLinkedList {

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

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
