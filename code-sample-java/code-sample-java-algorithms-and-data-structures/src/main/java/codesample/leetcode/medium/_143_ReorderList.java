package codesample.leetcode.medium;

/**
 * 143. Reorder List
 * <p>
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class _143_ReorderList {
    public void reorderList(ListNode head) {

        // 1. Get to the middle of the linked list
        //  - two pointers. Fast and slow. When fast exist, slow shows the middle

        ListNode slow = head;
        ListNode fast = head;

        // 1 - 2 - 3 - 4 - 5      => 1, 1 => 2, 3 => 3, 5 5.next == null => 3 is the middle (aka last element)
        // 1 - 2 - 3 - 4 - 5 - 6  => 1, 1 => 2, 3 => 3, 5 => 4, null => 4 is the middle but right half of it (aka last element)
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode middleOfTheList = slow;

        // 2. Reverse the second half.
        ListNode reversedHead = middleOfTheList.next;

        // mark the end of the interleaved list with null
        middleOfTheList.next = null;

        ListNode prev = null;
        ListNode cur = reversedHead;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        ListNode backwardHead = prev; // ends with null
        ListNode forwardHead = head; // ends with middleOfTheList

        // 3. Iterate over and insert one element after another
        // e.g.
        // 1 - 2 - 3 - middleOfTheList
        // 6 - 5 - 4 - null
        while (backwardHead != null) {
            ListNode nextBackward = backwardHead.next; // remember 5    | remember 4    | null
            ListNode nextForward = forwardHead.next;   // remember 2    | remember 3    | null

            forwardHead.next = backwardHead;           // 1 -> 6        | 1-6-2-5       | 1-6-2-5-3-4
            forwardHead.next.next = nextForward;       // 1 -> 6 -> 2   | 1-6-2-5-3     | 1-6-2-5-3-4-null

            forwardHead = nextForward;                 // 2             | 3             | null
            backwardHead = nextBackward;               // 5             | 4             | null
        }
    }


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

}
