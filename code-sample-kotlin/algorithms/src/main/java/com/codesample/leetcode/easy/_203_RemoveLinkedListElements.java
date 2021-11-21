package com.codesample.leetcode.easy;

/**
 * 203. Remove Linked List Elements — https://leetcode.com/problems/remove-linked-list-elements/
 * <p>
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 */
public class _203_RemoveLinkedListElements {

    class ListNode {
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

    static class Solution {
        public ListNode removeElements(ListNode head, int val) {

            // first -> remove elements from head updating head
            while (head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) {
                return null;
            }

            // second -> remove all other elements. We know that head stays.
            ListNode prev = head;
            ListNode next = head.next;

            while(next != null) {
                if (next.val == val) {
                    prev.next = next.next;
                } else {
                    prev = next;
                }
                next = next.next;
            }

            return head;
        }
    }
}
