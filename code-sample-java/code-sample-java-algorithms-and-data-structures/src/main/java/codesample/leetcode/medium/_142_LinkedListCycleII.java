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
        // fast + slow pointers would find one of the points on the cycle.
        // From there we can get a length of cycle while traversing it again
        // How to get the beggining point though?
        // Can we count the number of nodes in total? That - cycle length == node where cycle begins.
        //  Cycle can not be in the middle?

        // 1 -> 2 -> 3 -> 4 -> 5 does the slow always end up at the place of the loop? NO
        //           ^         |
        //           -   -   - -

        // 1 -> 2 -> 3 -> 4 -> 5 does the slow always end up at the place of the loop? NO
        // ^                   |
        // -  -   -  -  -   -  -

        // 1 -> 2 -> 3 -> 4 -> 5 does the slow always end up at the place of the loop? NO
        //                ^    |
        //                - -  -

        // in ^ we know slow = 4. We also know length of cycle = 3. How to we get the index of loop
        // without remembering the nodes?

        // 1. Find the cycle
        // 2. Find the length of the cycle
        // 3. Get two pointer. Move one of them by length forward
        // 4. Iterate until they meet -> that the cycle beginning.
        // Why does it work??

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

        // find length of cycle
        int length = 0;
        ListNode node = slow;

        do {
            node = node.next;
            length++;
        } while (node != slow);

        // create 2 pointer
        ListNode pointer1 = head;
        ListNode pointer2 = head;

        // move pointer 2 by length nodes
        for (int i = 0; i < length; i++) {
            pointer2 = pointer2.next;
        }

        // find the begginning
        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1;
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
