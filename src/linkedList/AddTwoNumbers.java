package linkedList;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int sum = l1.val + l2.val;
        int rem = (sum) / 10;
        ListNode l = new ListNode(sum % 10);
        l1 = l1.next;
        l2 = l2.next;
        ListNode prev = l;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + rem;
            rem = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            prev.next = curr;
            prev = curr;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + rem;
            rem = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            prev.next = curr;
            prev = curr;
            l1 = l1.next;
        }

        while (l2 != null) {
            sum = l2.val + rem;
            rem = sum / 10;
            ListNode curr = new ListNode(sum % 10);
            prev.next = curr;
            prev = curr;
            l2 = l2.next;
        }

        if (rem != 0) {
            ListNode curr = new ListNode(rem);
            prev.next = curr;
        }
        return l;
    }
}
