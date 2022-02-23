package codesample.leetcode.easy;

/**
 * 21. Merge Two Sorted Lists — https://leetcode.com/problems/merge-two-sorted-lists/
 * <p>
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 * <p>
 * Example
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * <p>
 * Example
 * Input: list1 = [], list2 = []
 * Output: []
 * <p>
 * Example
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class _21_MergeTwoSortedLists {

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


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // any of lists is null => return another
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }


        ListNode headLeft = list1;
        ListNode headRight = list2;

        ListNode result;

        // save bigger first as first element
        if (headLeft.val < headRight.val) {
            result = headLeft;
            headLeft = headLeft.next;
        } else {
            result = headRight;
            headRight = headRight.next;
        }

        ListNode headOfResult = result;

        // iterate


        while (true) {
            if (headLeft == null && headRight == null) {
                break;
            } else if (headLeft != null && headRight == null) {
                result.next = headLeft;
                headLeft = headLeft.next;
                result = result.next;
            } else if (headLeft == null && headRight != null) {
                result.next = headRight;
                headRight = headRight.next;
                result = result.next;
            } else {
                if (headLeft.val < headRight.val) {
                    result.next = headLeft;
                    headLeft = headLeft.next;
                    result = result.next;
                } else {
                    result.next = headRight;
                    headRight = headRight.next;
                    result = result.next;
                }
            }
        }

        return headOfResult;
    }
}
