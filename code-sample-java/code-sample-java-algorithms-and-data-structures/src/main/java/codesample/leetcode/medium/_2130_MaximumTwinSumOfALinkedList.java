package codesample.leetcode.medium;

/**
 * 2130. Maximum Twin Sum of a Linked List — https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/
 */
public class _2130_MaximumTwinSumOfALinkedList {

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


    public int pairSum(ListNode head) {
        var copy = copy(head);
        var reversed = reversed(copy);

        var max = 0;

        var headDirect = head;
        var headReversed = reversed;

        while (headDirect != null) {
            max = Math.max(max, headDirect.val + headReversed.val);
            headDirect = headDirect.next;
            headReversed = headReversed.next;
        }

        return max;
    }

    private ListNode copy(ListNode head) {
        var copyHead = new ListNode(head.val);
        var returnedHead = copyHead;

        var hd = head.next;
        while (hd != null) {
            copyHead.next = new ListNode(hd.val);
            hd = hd.next;
            copyHead = copyHead.next;
        }

        return returnedHead;
    }

    private ListNode reversed(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            var next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
