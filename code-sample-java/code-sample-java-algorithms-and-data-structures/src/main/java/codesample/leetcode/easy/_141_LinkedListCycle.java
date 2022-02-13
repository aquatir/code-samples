package codesample.leetcode.easy;

/**
 * 141. Linked List Cycle — https://leetcode.com/problems/linked-list-cycle/
 * <p>
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
public class _141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        head.val = 1;

        while (fast.next != null && fast.next.next != null) {
            slow.next.val = slow.val;
            slow = slow.next;

            fast.next.next.val = fast.val + 2;
            fast = fast.next.next;
            if (slow.val == fast.val) {
                return true;
            }
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
