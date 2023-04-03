package codesample.leetcode.medium;

/**
 * 61. Rotate List — https://leetcode.com/problems/rotate-list/description/
 */
public class _61_RotateList {


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


    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        // close the linked list into the ring, also count number of nodes that we pass
        var oldTail = head;
        int n;
        for (n = 1; oldTail.next != null; n++) {
            oldTail = oldTail.next;
        }
        oldTail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        var newTail = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            newTail = newTail.next;
        }
        var newHead = newTail.next;

        // break the ring
        newTail.next = null;

        return newHead;
    }
}
