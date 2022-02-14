package codesample.leetcode.medium;

/**
 * 142. Linked List Cycle II — https://leetcode.com/problems/linked-list-cycle-ii/
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following
 * the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 */
public class _142_LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // find cycle
        boolean cycleExist = false;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                cycleExist = true;
                break;
            }
        }
        // or exit if not found
        if (!cycleExist) {
            return null;
        }

        ListNode node = head;

        while (node != slow) {
            node = node.next;
            slow = slow.next;
        }

        return node;
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
