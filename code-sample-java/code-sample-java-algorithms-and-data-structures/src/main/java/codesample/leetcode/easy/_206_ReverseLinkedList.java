package codesample.leetcode.easy;

import java.util.List;

/**
 * 206. Reverse Linked List — https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class _206_ReverseLinkedList {

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

//    public ListNode reverseList(ListNode head) {
//        ListNode prev = null;
//        ListNode cur = head;
//
//        while (cur != null) {
//            ListNode next = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = next;
//        }
//
//        return prev;
//    }

    // recursive
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        var s = new _206_ReverseLinkedList();

        var node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        var res = s.reverseList(node);
        while (res != null) {
            System.out.print(res.val + " -> ");
            res = res.next;
        }
    }
}
