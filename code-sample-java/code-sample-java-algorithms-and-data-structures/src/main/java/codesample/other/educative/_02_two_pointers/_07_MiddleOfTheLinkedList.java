package codesample.other.educative._02_two_pointers;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class _07_MiddleOfTheLinkedList {

    public static ListNode findMiddle(ListNode head) {
        // Use fast + slow pointer
        // fast jumps by two
        // slow jumps by one.
        // when fast can no longer just
        // slow is the answer
        // or is it...?
        // 0. Millde = 0
        // 0 -> 1. slow = 1, fast = 2 -> cant, but can get "half jump"
        // 0 -> 1 -> 2 -> 3.      // slow = 2, fast = 4 cant -> but can get a "half jump"
        // 0 -> 1 -> 2 -> 3 -> 4  // slow = 2, fast = 4 -> result 2, because fast can't get half jump

        ListNode slow = head;
        ListNode fast = head;

        // 0 -> 1 -> 2 -> 3 slow = 2, fast = 4

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
