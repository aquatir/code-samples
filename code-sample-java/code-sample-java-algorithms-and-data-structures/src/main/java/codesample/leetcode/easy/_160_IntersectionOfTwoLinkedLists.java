package codesample.leetcode.easy;

/**
 * 160. Intersection of Two Linked Lists — https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 */
public class _160_IntersectionOfTwoLinkedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // 2 lists
            // cycle one of the lists
            // iterate to find the loop point form the other one
            // if not found => no itersection. If found => break the cycle are return the node

            var listOne = headA;

            while (listOne.next != null) {
                listOne = listOne.next;
            }
            var endNodeA = listOne;
            endNodeA.next = headA;

            var slow = headB;
            var fast = headB;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    slow = headB;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    endNodeA.next = null;
                    return slow;
                }
            }

            endNodeA.next = null;
            return null;
        }
    }
}
