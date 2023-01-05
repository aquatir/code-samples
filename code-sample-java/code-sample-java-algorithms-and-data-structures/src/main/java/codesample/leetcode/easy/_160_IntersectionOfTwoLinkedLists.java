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

            // Approach 2 (implemented): find lengths of both lists + iterate over them until the end
            //  if the end is not the same for both lists => there is no intersection
            //      the length after interception is >= length of the shortest list
            //      find longer and shorter list
            //      iterate longer list until they both have the same size
            //      iterate both lists until we find an intersection point

            var listOne = headA;
            var listTwo = headB;

            var lengthOne = 1;
            var lengthTwo = 1;

            while (listOne.next != null) {
                listOne = listOne.next;
                lengthOne++;
            }

            while (listTwo.next != null) {
                listTwo = listTwo.next;
                lengthTwo++;
            }

            // lists ends with different nodes => no intersection
            if (listOne != listTwo) {
                return null;
            }

            ListNode longerList, shorterList = null;
            int longerSize, shorterSize = 0;

            if (lengthOne >= lengthTwo) {
                longerList = headA;
                longerSize = lengthOne;
                shorterList = headB;
                shorterSize = lengthTwo;
            } else {
                longerList = headB;
                longerSize = lengthTwo;
                shorterList = headA;
                shorterSize = lengthOne;
            }

            while (longerSize != shorterSize) {
                longerList = longerList.next;
                longerSize--;
            }

            while(longerList != shorterList) {
                shorterList = shorterList.next;
                longerList = longerList.next;
            }
            return longerList;
        }
    }
}
