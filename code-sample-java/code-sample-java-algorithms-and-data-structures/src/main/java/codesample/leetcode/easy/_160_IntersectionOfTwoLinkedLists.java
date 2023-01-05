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
            // Approach 1 (find in git history): create a cycle in one of the lists
            //  iterate to find the loop point form the other one
            //  if not found => no intersection. If found => break the cycle are return the node

            // Approach 2 (git history): find lengths of both lists + iterate over them until the end
            //  if the end is not the same for both lists => there is no intersection
            //      the length after interception is >= length of the shortest list
            //      find longer and shorter list
            //      iterate longer list until they both have the same size
            //      iterate both lists until we find an intersection point

            // Approach 3 (implemented): iterate both lists at the same time. When one of the lists reaches the end
            // reset it's head to another list's head => this is pretty much the same things as in Approach 2 where we
            // calculate the length and move the pointer only here we don't "calculate" but simply move two heads
            // because it's the same distance.
            //
            // Due to the fact that shorter list will hit the end faster, and we move it's head to a longer list, it
            // will pass exactly a difference of longer minus shorted lengths before the other list switch too => this is
            // the same length difference as in Approach 2.
            //
            // We than keep iterating before the intersection point OR before both lists hit null node again if there is
            // no intersection

            var listOne = headA;
            var listTwo = headB;

            while (listOne != listTwo) {
                listOne = listOne == null ? headB : listOne.next;
                listTwo = listTwo == null ? headA : listTwo.next;
            }

            return listOne;
        }
    }
}
