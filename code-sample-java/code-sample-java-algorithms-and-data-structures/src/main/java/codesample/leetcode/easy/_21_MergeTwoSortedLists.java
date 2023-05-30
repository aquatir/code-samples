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


    // a complicated approach that iterate all the nodes
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//
//        // any of lists is null => return another
//        if (list1 == null) {
//            return list2;
//        } else if (list2 == null) {
//            return list1;
//        }
//
//
//        ListNode headLeft = list1;
//        ListNode headRight = list2;
//
//        ListNode result;
//
//        // save bigger first as first element
//        if (headLeft.val < headRight.val) {
//            result = headLeft;
//            headLeft = headLeft.next;
//        } else {
//            result = headRight;
//            headRight = headRight.next;
//        }
//
//        ListNode headOfResult = result;
//
//        // iterate
//
//
//        while (true) {
//            if (headLeft == null && headRight == null) {
//                break;
//            } else if (headLeft != null && headRight == null) {
//                result.next = headLeft;
//                headLeft = headLeft.next;
//                result = result.next;
//            } else if (headLeft == null && headRight != null) {
//                result.next = headRight;
//                headRight = headRight.next;
//                result = result.next;
//            } else {
//                if (headLeft.val < headRight.val) {
//                    result.next = headLeft;
//                    headLeft = headLeft.next;
//                    result = result.next;
//                } else {
//                    result.next = headRight;
//                    headRight = headRight.next;
//                    result = result.next;
//                }
//            }
//        }
//
//        return headOfResult;
//    }

    // a simplified approach that only iterate on nodes while it need to
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//
//        var sentinel = new ListNode(0);
//        var headOfSentinel = sentinel;
//
//        while(list1 != null && list2 != null) {
//            if (list1.val < list2.val) {
//                sentinel.next = list1;
//                list1 = list1.next;
//            } else {
//                sentinel.next = list2;
//                list2 = list2.next;
//            }
//
//            sentinel = sentinel.next;
//        }
//
//        if (list1 == null) {
//            sentinel.next = list2;
//        } else {
//            sentinel.next = list1;
//        }
//
//        return headOfSentinel.next;
//    }

    // a recursive solution
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        var sentinel = new ListNode(0);
//
//        mergeTwoLists(sentinel, list1, list2);
//
//        return sentinel.next;
//    }
//
//    public void mergeTwoLists(ListNode prev, ListNode list1, ListNode list2) {
//        if (list1 == null && list2 != null) {
//            prev.next = list2;
//            return;
//        }
//        if (list1 != null && list2 == null) {
//            prev.next = list1;
//            return;
//        }
//        if (list1 == null && list2 == null) {
//            return;
//        }
//
//        if (list1.val < list2.val) {
//            prev.next = list1;
//            mergeTwoLists(list1, list1.next, list2);
//        } else {
//            prev.next = list2;
//            mergeTwoLists(list2, list1, list2.next);
//        }
//    }

    // an easier recursive
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        else if (list2 == null) {
            return list1;
        }
        else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

}
