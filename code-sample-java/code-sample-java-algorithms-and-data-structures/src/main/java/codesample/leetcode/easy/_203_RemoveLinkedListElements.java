package codesample.leetcode.easy;

/**
 * 203. Remove Linked List Elements — https://leetcode.com/problems/remove-linked-list-elements/
 * <p>
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 */
public class _203_RemoveLinkedListElements {

    static class ListNode {
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
            if (head == null) {
                return null;
            }

            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            var prev = dummy;

            while (prev != null && prev.next != null) {
                if (prev.next.val == val) {
                    // if next element is val => skip it and don't advance pointer
                    prev.next = prev.next.next;
                } else {
                    // else => advance pointer
                    prev = prev.next;
                }

            }
            return dummy.next;
        }
    }

    public static void main(String[] args) {

        var s = new _203_RemoveLinkedListElements.Solution();

        var head = new ListNode(1);

        var newHead = s.removeElements(head, 1);

        if (newHead == null) {
            System.out.println("null");
        } else {
            while (newHead != null) {
                System.out.print(newHead.val + " -> ");
                newHead = newHead.next;
            }
        }
    }
}
