package priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {

    //Using priority queue
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a < b) {
                    return 1;
                } else if (a > b) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < stones.length; i++) {
            pq.add(stones[i]);
        }
        if (pq.size() == 1) {
            return pq.peek();
        }
        while (pq.size() != 0) {
            if (pq.size() == 0) {
                return 0;
            }
            if (pq.size() == 1) {
                return pq.peek();
            }
            int y = pq.poll();
            int x = pq.poll();

            if (x != y) {
                pq.add(y - x);
            }
        }
        return 0;
    }
}
