package codesample.leetcode.medium;

/**
 * 92. Reverse Linked List II — https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the
 * list from position left to position right, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
public class _92_ReverseLinkedListII {


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

    public ListNode reverseBetween(ListNode head, int left, int right) {

        /**
         1. Iterate over the list until element # left
         2. Remember where two is located and also prev. We will need those later
         3. Keep reversing until meet right
         4. Remember next after right.
         5. Now we have Begin of reverted, End of reverted and the next
         6. point element before to End, point element after to Begin
         */

        // 1->2->3->4->5, left = 2, right = 4

        ListNode prev = null;
        ListNode cur = head;

        int curPosition = 1;

        while (curPosition != left) {
            prev = cur;
            cur = cur.next;
            curPosition++;
        }

        // Remember begginning of the list and the end which will be a head of reverted after revert
        ListNode positionBeforeRevertedPart = prev;
        ListNode headAfterRevert = cur;

        // 1->2->3->4->5, left = 2, right = 4
        // cur = 2, curPosition = 2
        // next = 3
        // cur.next = null (null <- 2)
        // prev = cur (2)
        // cur = next (3)
        // curPosition = 3

        // next = 4
        // cur.next = 2 (null <- 2 <- 3)
        // prev = 3
        // cur = next (4)
        // curPosition = 4

        // reverting now
        prev = null;
        while (curPosition != right + 1) { // right + 1 becase we have to revert right element too
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            curPosition++;
        }

        // connect with the first part
        if (positionBeforeRevertedPart != null) {
            positionBeforeRevertedPart.next = prev;
        }
        else {
            head = prev;
        }

        // it is pointing to null now => must point to the current element
        headAfterRevert.next = cur;

        return head;
    }
}
