package codesample.leetcode.medium;

/**
 * 19. Remove Nth Node From End of List — https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class _19_RemoveNthNodeFromEndOfList {

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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // approach 1: count number of elements, than iterate again and remove
            // approach 2: create two pointers. Move one of them by n nodes forward.
            //      now move both of them until the node infront is not null. At this point the node
            //      behind is pointing at the element 'n' nodes behind that must be deleted.
            var size = 0;
            var dummy = new ListNode(0);
            dummy.next = head;

            var behind = dummy;
            var infront = dummy;


            for (int i = 0; i <= n; i++) {
                infront = infront.next;
            }

            while (infront != null) {
                infront = infront.next;
                behind = behind.next;
            }

            behind.next = behind.next.next;


            return dummy.next;

        }
    }
}
