package codesample.leetcode.medium;

/**
 * 328. Odd Even Linked List — https://leetcode.com/problems/odd-even-linked-list/description/
 */
public class _328_OddEvenLinkedList {

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


    class Solution {
        public ListNode oddEvenList(ListNode head) {

            // idea: build odd and even lists separately. Then merge them in the end.
            // it's constant space regardless of head's list size.
            // alternative — find those head nodes first. Even less space, but slightly more code
            var oddListHead = new ListNode(0);
            var evenListHead = new ListNode(0);

            var oddList = oddListHead;
            var evenList = evenListHead;

            var cur = head;
            int index = 1;
            while (cur != null) {
                if (index % 2 == 0) { // is even index
                    evenList.next = cur;
                    evenList = evenList.next;
                } else { // is odd index
                    oddList.next = cur;
                    oddList = oddList.next;
                }
                cur = cur.next;
                index++;
            }
            evenList.next = null;
            oddList.next = null;

            oddList.next = evenListHead.next;

            return oddListHead.next;

        }
    }
}
