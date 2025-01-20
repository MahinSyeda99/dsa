package priority_queue;

import java.util.*;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> mp = new HashMap<>();
        //Find the count fo each character
        for(int i = 0; i < tasks.length; i++) {
            mp.put(tasks[i], mp.getOrDefault(tasks[i], 0) + 1);
        }
        //Add the counts to priority queue, maintain max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.addAll(mp.values());
        //Our final result
        int time = 0;
        //Queue to maintain the task which is still remaining after doing a part of it
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        while(!pq.isEmpty() || !q.isEmpty()) {
            //Increment the time
            time = time + 1;
            //If there is no idle wait
            if(!pq.isEmpty()) {
                int max_freq = pq.poll();
                //If max_freq is not 1, then the task is still left to be done
                //So, add it to queue along with the time when it can be next done
                //Time at which it can be next done will be time + n(i.e idle time)
                if(max_freq > 1) {
                    q.add(new Pair<Integer, Integer>(max_freq - 1, time + n));
                }
            }
            //If the first element of queue has reached the current time, then remove it and add the count back to priority queue
            if(!q.isEmpty() && q.peek().getSecond() == time) {
                pq.add(q.poll().getFirst());
            }
        }
        return time;
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

    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        //O/p should be 8
        System.out.println(taskScheduler.leastInterval(tasks, n));
    }
}
