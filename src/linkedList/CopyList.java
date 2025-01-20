package linkedList;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */
public class CopyList {

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> mp = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            Node node = new Node(curr.val);
            mp.put(curr, node);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            Node currNew = mp.get(curr);
            if (curr.next != null) {
                currNew.next = mp.get(curr.next);
            }
            if (curr.random != null) {
                currNew.random = mp.get(curr.random);
            }
            curr = curr.next;
        }
        return mp.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
