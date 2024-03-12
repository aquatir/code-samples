package codesample.leetcode.medium;

/**
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List — https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
 */
public class _1171_RemoveZeroSumConsecutiveNodesFromLinkedList {

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


    public ListNode removeZeroSumSublists(ListNode head) {
        // The given linked list will contain between 1 and 1000 nodes.
        // Each node in the linked list has -1000 <= node.val <= 1000.

        // Approach 1: deconstruct a list into an array
        //   Remove zero-sum sublist
        //   Construct the array back

        // How do we find zero-sum sublist in unordered array?
        //   1: Brute-force. Fix ith elements, try to grow the array. If at some point
        //     found a subzero, remember. Remove the biggest subsequence
        //     repeat for every i (is it always optimal?)

        if (head == null) {
            return head;
        }

        int[] tempArr = new int[1000];  // not more than 1000 elements
        int i = 0;                      // at the end i counts total number of elements
        while (head != null) {
            if (head.val != 0) {        // Hint: can remove 0s because they are always zero-sum;
                tempArr[i] = head.val;
            }
            i++;
            head = head.next;
        }

        // After this arr is correctly sized
        int[] arr = new int[i];
        for (int j = 0; j < i; j++) {
            arr[j] = tempArr[j];
        }

        // find zero-sum subsequences
        int[] res = removeZeroSumSeq(arr);
        while (res.length != arr.length) {
            // there was a zero-sum. Rerun again
            arr = res;
            res = removeZeroSumSeq(arr);
        }

        // back to linked list and return
        if (arr.length == 0) {
            return null;
        }
        var newHead = new ListNode(arr[0]);
        var cur = newHead;
        for (int m = 1; m < res.length; m++) {
            cur.next = new ListNode(arr[m]);
            cur = cur.next;
        }
        return newHead;
    }

    private int[] removeZeroSumSeq(int[] arr) {
        var len = arr.length;
        for (int i = 0; i < len; i++) {
            var sum = 0;
            for (int j = i; j < len; j++) {
                sum += arr[j];
                if (sum == 0) { // found zero-sum. Remove it and return result
                    var res = new int[len - j + i - 1];
                    var index = 0;

                    // move elements before start of i
                    for (int k = 0; k < i; k++) {
                        res[index] = arr[index];
                        index++;
                    }
                    // move elements after start of j
                    for (int k = j + 1; k < len; k++) {
                        res[index] = arr[k];
                        index++;
                    }
                    return res;
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        var s = new _1171_RemoveZeroSumConsecutiveNodesFromLinkedList();

        // 1, 2, -3, 3, 1          // expected = [3,1] or [1,2,1]
//        var head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(-3);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(1);

        // 1,2,3,-3,4               // expected = [1,2,4]
        var head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(-3);
        head.next.next.next.next = new ListNode(4);

        var res = s.removeZeroSumSublists(head);
        while (res != null) {
            System.out.print(res.val + " -> ");
            res = res.next;
        }
    }
}
