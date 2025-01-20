package linkedList;

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        //If k nodes are not available, then we don't want to reverse, so return head directly
        if (!isValid(head, k)) {
            return head;
        }

        int count = 0;
        ListNode prev = null;
        ListNode curr = head;
        //Reverse the LL from head till count = k
        while (count < k && curr != null) {
            ListNode t = curr.next;
            curr.next = prev;
            prev = curr;
            curr = t;
            count++;
        }
        //current head's next will be reverse of next k nodes in LL
        head.next = reverseKGroup(curr, k);
        //prev will be our new head after reversing the list
        return prev;
    }

    //Find if we have k nodes in the LL
    public boolean isValid(ListNode head, int k) {
        if (head == null) {
            return false;
        }
        ListNode temp = head;
        int count = 0;
        while (count != k && temp != null) {
            temp = temp.next;
            count++;
        }
        return count == k;
    }
}
