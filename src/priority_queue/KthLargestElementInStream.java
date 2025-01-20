package priority_queue;

import java.util.PriorityQueue;

public class KthLargestElementInStream {

    PriorityQueue<Integer> pq;

    //Using priority queue
    public KthLargestElementInStream(int k, int[] nums) {
        pq = new PriorityQueue<Integer>(k);
        for (int i = 0; i < k && i < nums.length; i++) {
            pq.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (pq.peek() <= nums[i]) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        if (pq.size() != k) {
            for (int i = 0; i < k - pq.size(); i++) {
                pq.add(Integer.MIN_VALUE);
            }
        }
    }

    public int add(int val) {
        if (pq.size() == 0) {
            pq.add(val);
        } else {
            if (pq.peek() <= val) {
                pq.poll();
                pq.add(val);
            }
        }
        return pq.peek();
    }
}
