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

        // 1


        ListNode prev = null;
        ListNode cur = head;

        while (true) {

            ListNode lastNodeOfPrev = prev;
            ListNode lastNodeOfSublist = cur; // we know it because it's the first node we process

            if (prev != null) {
                System.out.println("lastNodeOfPrev: " + lastNodeOfPrev.value + ", lastNodeOfSublist: " + lastNodeOfSublist.value);
            } else {
                System.out.println("lastNodeOfPrev: null, lastNodeOfSublist: " + lastNodeOfSublist.value);
            }
            // rotate
            for (int i = 0; i < k && cur != null; i++) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // re-assign head
            if (lastNodeOfPrev != null) {
                System.out.println("lastNodeOfPrev.next: " + lastNodeOfPrev.next.value + " => " + prev.value);
                lastNodeOfPrev.next = prev;
            } else {
                head = prev;
            }
            lastNodeOfSublist.next = cur;
            prev = lastNodeOfSublist;

            if (cur == null) {
                break;
            }
        }

        return head;
    }

    public Optional<Integer> get() {
        return Optional.of(3);
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
