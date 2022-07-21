package codesample.leetcode.hard;

/**
 * 25. Reverse Nodes in k-Group — https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class _25_ReverseNodesInKGroup {

    public static class ListNode {
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

        @Override
        public String toString() {
            return "ListNode{" +
                "val=" + val +
                '}';
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        /**
         1. revert first k nodes.
         2. figure out how to revert the rest.
         3.   why? revert operation for 1 should be the same as for 2.
         */

        // 1 -> 2 -> 3 -> null, k =1
        //  prev = null, cur = 1
        //  1. next = 2, cur.next = null, prev = 1, cur = 2
        //  2. next = 3, cur.next = 2, prev = 2, cur = 3

        // null <- 1 <- 2  3 -> 4
        // prev = 2
        // cur = 3

        int curNode = 0;
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {

            ListNode lastNodeOfPrevSublist = prev;
            ListNode lastNodeOfThisSublist = cur;
            ListNode next = null;

            // check if we should revert the last part
            ListNode begginning = cur;
            int numberOfNodesLeft = 1;
            while (begginning != null && numberOfNodesLeft != k) {
                begginning = begginning.next;
                numberOfNodesLeft++;
            }
            if (begginning == null || numberOfNodesLeft < k) {
                break; // not enough nodes for the last revert.
            }
            // end of check

            // start reverting
            for (int i = 0; cur != null && i < k; i++) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // connect with prev part
            // the first connection should change the head. All others should not.
            if (lastNodeOfPrevSublist != null) {
                lastNodeOfPrevSublist.next = prev;
            } else {
                head = prev;
            }

            // connect with the next part
            lastNodeOfThisSublist.next = cur;
            prev = lastNodeOfThisSublist;

            if (cur == null) {// break, if we've reached the end of the LinkedList
                break;
            }

        }

        return head;
    }

    public static void main(String[] args) {
        var s = new _25_ReverseNodesInKGroup();

        var head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        var newHead = s.reverseKGroup(head, 2);

        while (newHead != null) {
            System.out.print(newHead.val + " -> " );
            newHead = newHead.next;
        }
        System.out.print("null");
    }
}
