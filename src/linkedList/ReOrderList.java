package linkedList;

public class ReOrderList {

    public void reorderList(ListNode head) {
        ListNode mid = findMid(head);

        //Split the list into two from mid
        ListNode curr = mid.next;
        mid.next = null;

        //Reverse the rest half of list
        ListNode head2 = reverseList(curr);

        //Merge the first half and reversed second half of list
        mergeAlternateLists(head, head2);
    }

    public ListNode findMid(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        //Find mid
        while (f != null && f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    public ListNode reverseList(ListNode h) {
        ListNode prev = null;
        while (h != null) {
            ListNode temp = h.next;
            h.next = prev;
            prev = h;
            h = temp;
        }
        return prev;
    }

    public ListNode mergeAlternateLists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        ListNode t = h1.next;
        h1.next = h2;
        h2.next = mergeAlternateLists(t, h2.next);
        return h1;
    }
}
