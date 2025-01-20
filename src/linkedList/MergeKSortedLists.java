package linkedList;

public class MergeKSortedLists {

    //Using Divide and conquer
    //O(NlogK) where N is total number of nodes across all linked lists and K is the number of linked lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int start = 0;
        int end = lists.length - 1;
        return partitionAndMerge(start, end, lists);
    }

    public ListNode partitionAndMerge(int start, int end, ListNode[] lists) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end) / 2;
        //Divide the list of nodes into two parts
        ListNode left = partitionAndMerge(start, mid, lists);
        ListNode right = partitionAndMerge(mid + 1, end, lists);
        //Merge the linked lists
        return mergeSortedLists(left, right);
    }

    public ListNode mergeSortedLists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.val <= h2.val) {
            h1.next = mergeSortedLists(h1.next, h2);
            return h1;
        } else {
            h2.next = mergeSortedLists(h1, h2.next);
            return h2;
        }
    }
}
