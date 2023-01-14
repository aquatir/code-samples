package codesample.leetcode.medium;

/**
 * 2. Add Two Numbers — https://leetcode.com/problems/add-two-numbers/description/
 */
public class _2_AddTwoNumbers {

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

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // appraoch 1: rotate both and sum normally from lower digits
            // approach 2... ????

            var result = new ListNode(0);
            var resultHead = result;

            int extra = 0;

            while (!(l1 == null && l2 == null)) { // while both are not null
                var sum = valOrZero(l1) + valOrZero(l2) + extra;
                if (sum < 10) {
                    result.next = new ListNode(sum);
                    extra = 0;
                } else {
                    result.next = new ListNode(sum % 10);
                    extra = 1;
                }
                l1 = moveIfNotNull(l1);
                l2 = moveIfNotNull(l2);
                result = result.next;
            }

            if (extra == 1) {
                result.next = new ListNode(1);
            }

            return resultHead.next;
        }

        public ListNode rotate(ListNode node) {
            ListNode prev = null;
            ListNode cur = node;

            while (cur != null) {
                var next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            return prev;
        }

        public ListNode moveIfNotNull(ListNode node) {
            if (node == null) {
                return null;
            } else {
                return node.next;
            }
        }

        public int valOrZero(ListNode node) {
            if (node == null) {
                return 0;
            } else {
                return node.val;
            }
        }
    }

    public static void main(String[] args) {
        var s = new Solution();

        // [2,4,9]
        //[5,6,4,9]
        var list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(9);

        var list2 = new ListNode(5);
        list2.next = new ListNode(6);
        list2.next.next = new ListNode(4);
        list2.next.next.next = new ListNode(9);

        var res = s.addTwoNumbers(list1, list2);

        while (res != null) { // expcted = [7,0,4,0,1]
            System.out.print(res.val + " -> ");
            res = res.next;
        }

        System.out.println();
    }
}
