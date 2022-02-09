package codesample.leetcode.easy;

/**
 * 83. Remove Duplicates from Sorted List — https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * <p>
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
 */
public class _83_RemoveDuplicatesFromSortedList {

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
    }

    public ListNode deleteDuplicates(ListNode head) {

        // fix current element
        // if another same element is found -> ignore it. Do not update the current list
        // if another different element is found -> update the next.

        if (head == null) {
            return null;
        }

        ListNode noDuplicatesListCur = new ListNode(head.val);
        noDuplicatesListCur.next = null;

        ListNode noDuplicatesListHead = noDuplicatesListCur;

        ListNode prev = head;
        ListNode cur = prev.next;

        while (cur != null) {
            if (prev.val == cur.val) {
                // ignore
            } else {
                ListNode newNode = new ListNode(cur.val);
                newNode.next = null;
                noDuplicatesListCur.next = newNode;
                noDuplicatesListCur = noDuplicatesListCur.next;
            }
            prev = cur;
            cur = cur.next;
        }

        return noDuplicatesListHead;
    }

    public static void main(String[] args) {
        var s = new _83_RemoveDuplicatesFromSortedList();

        var head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        s.deleteDuplicates(head); // expected: 1->2->3
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
