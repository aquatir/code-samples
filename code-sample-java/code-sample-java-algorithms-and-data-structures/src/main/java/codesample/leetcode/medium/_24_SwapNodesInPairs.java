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

        var cur = head;
        var newHead = head.next;  // new head is always going to be this after swap
        ListNode prev = null;
        // move by two nodes at once.
        while (cur != null && cur.next != null) {
            var afterRotation = rotate(prev, cur, cur.next);
            prev = afterRotation;
            cur = afterRotation.next;
        }

        return newHead;
    }

    // rotate 2 nodes, update prev if possible, return a node which was moved (e.g. for a-b initial, result is b-a, return node a)
    private ListNode rotate(ListNode prev, ListNode a, ListNode b) {
        // before
        // [prev]  -  a   -   b    - c
        //
        // becomes this
        // [prev]  -  b  -  a   -  c; return node a (to set it as prev)
        // + handle prev if it's present. Prev will point to b

        var c = b.next;
        a.next = c;
        b.next = a;
        if (prev != null) {
            prev.next = b;
        }
        return a;
    }


    // tricky recursion solution
    public ListNode swapPairsRecursion(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode next = head.next;

        head.next = next.next;
        next.next = head;

        head.next = swapPairsRecursion(head.next);

        return next;  // next is new head
    }
}
