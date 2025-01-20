package linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/description/
 */
public class LRUCache {

    //We are using a LL, where head.next is most recently used and tail.prev is least recently used

    //head and tail are not actual nodes, dummy
    public DoublyLinkedListNode head;
    public DoublyLinkedListNode tail;
    //Map of key and the node
    public Map<Integer, DoublyLinkedListNode> mp;
    public int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.mp = new HashMap<>();
        this.head = new DoublyLinkedListNode(-1, -1);
        this.tail = new DoublyLinkedListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (mp.containsKey(key)) {
            DoublyLinkedListNode node = mp.get(key);
            //Remove node from LL
            deleteNode(node);
            //Add node to front of LL
            addNode(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        DoublyLinkedListNode node;
        if (mp.containsKey(key)) {
            //If mp contains key, get the node
            node = mp.get(key);
            //Delete the node from LL
            deleteNode(node);
            //Update the value of node
            node.key = key;
            node.val = value;
        } else {
            //If mp doesn't contain key,create a new node with key and value
            node = new DoublyLinkedListNode(key, value);
            //Add to map
            mp.put(key, node);
        }
        //If adding new node, crosses the capacity
        if (mp.size() > capacity) {
            //Remove node from end(LRU)
            if (tail.prev != null) {
                //Remove it from map
                mp.remove(tail.prev.key);
                //Delete node
                deleteNode(tail.prev);
            }
        }
        //Add node to front of LL
        addNode(node);
    }

    public void deleteNode(DoublyLinkedListNode node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

    public void addNode(DoublyLinkedListNode node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    class DoublyLinkedListNode {
        public DoublyLinkedListNode prev;
        public DoublyLinkedListNode next;
        public int key;
        public int val;

        DoublyLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}
