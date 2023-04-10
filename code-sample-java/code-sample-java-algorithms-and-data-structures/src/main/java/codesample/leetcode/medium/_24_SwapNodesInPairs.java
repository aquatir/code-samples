package codesample.leetcode.medium;

/**
 * 24. Swap Nodes in Pairs — https://leetcode.com/problems/swap-nodes-in-pairs/description/
 */
public class _24_SwapNodesInPairs {

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


    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode next = head.next;

        head.next = next.next;
        next.next = head;

        head.next = swapPairs(head.next);

        return next;  // next is new head
    }
}
