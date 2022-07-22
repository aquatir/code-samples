package codesample.other.educative._06_in_place_linked_list_revert;

import java.util.Optional;

/**
 * Reverse alternating K-element Sub-list (medium)#
 * Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from the head.
 * <p>
 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 */
public class _01_ReverseAlternatingKElementSubList {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode reverse(ListNode head, int k) {
        // 1. learn to rotate k elements only
        // 2. then learn to rotate sublists on k elements
        // 3. skip 2 every other time

        ListNode prev = null;
        ListNode cur = head;

        boolean shouldRotate = true;

        while (true) {

            // last element of PREVIOUS list which was rotated. Is null for the first list
            ListNode lastNodeOfPrevSubList = prev;
            // last element of CURRENT list which is about to be rotated. It is the first element of the list == cur
            ListNode lastNodeOfCurSubList = cur;

            // an extra check to see if need to rotate or not.
            if (!shouldRotate) {
                for (int i = 0; i < k && cur != null; i++) {
                    ListNode next = cur.next;
                    prev = cur;
                    cur = next;
                }
                if (cur == null) {
                    break;
                } else {
                    shouldRotate = !shouldRotate;
                    continue;
                }
            }

            // rotate
            for (int i = 0; i < k && cur != null; i++) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // Initially lastNodeOfPrevSubList.next point to the first element of the new list
            // that element is becoming the last element
            // we need to re-target lastNodeOfPrevSubList.next to point to the last reverted element of the new list,
            // because it is the begginning of the reverted part
            if (lastNodeOfPrevSubList != null) {
                lastNodeOfPrevSubList.next = prev;
            } else {
                head = prev;
            }
            // lastNodeOfCurSubList.next initially point to the next element of the reverted list, but that element
            // also point to lastNodeOfCurSubList due to revert
            // to fix that we point lastNodeOfCurSubList.next to current element, which is also the begginning of the
            // next sublist
            lastNodeOfCurSubList.next = cur;

            // we also reset prev to point to the last node of current sub list. `cur` already holds the first element
            // of new sublist
            prev = lastNodeOfCurSubList;

            if (cur == null) {
                break;
            }
            shouldRotate = !shouldRotate;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = _01_ReverseAlternatingKElementSubList.reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
