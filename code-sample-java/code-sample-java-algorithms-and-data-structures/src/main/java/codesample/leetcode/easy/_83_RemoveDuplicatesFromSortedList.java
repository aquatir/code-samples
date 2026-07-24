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


        /**
        // big brain solution
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
        */


        // approach:
        // cur always updates to cur.next on each iteration
        // if next element is different => put cur as next for nonDupList
        // otherwise; don't put it as next (only cur updates)
        // what if next is null? last update => exit
        var nonDupList = head;  // change what next of this points too
        var cur = head;         // iterate over elements
        while (cur != null) {
            var next = cur.next;
            if (next != null && next.val != cur.val) {
                nonDupList.next = next;
                nonDupList = nonDupList.next;
            }
            if (next == null) {
                nonDupList.next = null;
            }
            cur = next;
        }
        return head;
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
