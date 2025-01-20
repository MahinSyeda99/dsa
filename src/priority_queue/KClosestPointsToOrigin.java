package priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        //Maintain a pq of descending order
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(k, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
                if (a.getSecond() > b.getSecond()) {
                    return -1;
                } else if (a.getSecond() < b.getSecond()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (int r = 0; r < points.length; r++) {
            int dist = points[r][0] * points[r][0] + points[r][1] * points[r][1];
            if (pq.size() < k) {
                pq.add(new Pair<Integer, Integer>(r, dist));
            } else {
                Pair<Integer, Integer> peek = pq.peek();
                if (dist < peek.getSecond()) {
                    pq.poll();
                    pq.add(new Pair<Integer, Integer>(r, dist));
                }
            }
        }
        int[][] res = new int[pq.size()][2];
        int i = 0;
        while (pq.size() != 0) {
            res[i] = points[pq.peek().getFirst()];
            i++;
            pq.poll();
        }
        return res;
    }

    class Pair<T, V> {
        T first;
        V second;

        Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return this.first;
        }

        public V getSecond() {
            return this.second;
        }
    }
}
