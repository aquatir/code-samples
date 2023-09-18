package codesample.leetcode.medium;

/**
 * 2095. Delete the Middle Node of a Linked List — https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class _2095_DeleteTheMiddleNodeOfALinkedList {

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


    public ListNode deleteMiddle(ListNode head) {

        // fast & slow ?
        // assume n = 5
        // slow = 0 -> 1 -> 2 ->
        // fast = 0 -> 2 -> 4 -> can't progress, stop, delete 3

        // assume n = 4
        // slow = 0 -> 1 -> 2
        // fast = 0 -> 2 -> 4 can't progress

        if (head.next == null) {
            return null;
        }

        var prev = head;
        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = prev.next.next;

        return head;
    }
}
