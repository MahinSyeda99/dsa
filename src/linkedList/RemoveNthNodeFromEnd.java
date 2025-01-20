package linkedList;

public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //Use slow, fast pointer method, i.e two pointers
        ListNode s = head;
        ListNode f = head;
        //Give f a faster start so that when f reached end, s reaches n nodes behond it
        for (int i = 0; i < n; i++) {
            f = f.next;
        }
        if (f == null) {
            //We already reached end, so we need to remove first node
            return head.next;
        }
        //We need to reach node behind target node, so f.next != null condition
        while (f.next != null) {
            s = s.next;
            f = f.next;
        }
        s.next = s.next.next;
        return head;
    }
}
