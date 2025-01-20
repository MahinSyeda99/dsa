package stack;

import java.util.*;

/**
 * https://leetcode.com/problems/car-fleet/description/
 */
public class CarFleet {

    //Can be solved in O(maxPosition - minPosition) TC and O(n) SC using greedy
    public int carFleet2(int target, int[] position, int[] speed) {
        if (position.length == 1) {
            return 1;
        }
        //To store time taken to reach target from position[i]
        double[] timeArr = new double[target];
        //Keep track of low and high positions
        int positionLow = target - 1;
        int positionHigh = 0;
        for (int i = 0; i < position.length; i++) {
            timeArr[position[i]] = (target - (double) position[i]) / (double) speed[i];
            if (position[i] < positionLow) {
                positionLow = position[i];
            }
            if (position[i] > positionHigh) {
                positionHigh = position[i];
            }
        }

        int count = 0;
        double max = 0;

        //Instead of sorting, we are just traversing the time array from right end
        //Start from the highest position
        for (int i = positionHigh; i >= positionLow; i--) {
            //If time taken is greater than prev max value, then we need a new car fleet
            if (timeArr[i] > max) {
                max = timeArr[i];
                count++;
            }
        }
        //Final size of stack will be number of car fleets
        return count;
    }

    //O(nlogn) TC and O(n) SC solution
    // Monotonic increasing stack solution
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 1) {
            return 1;
        }
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            pairs.add(new Pair<>(position[i], speed[i]));
        }
        //Sort based on position
        Collections.sort(pairs, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
                return b.getKey() - a.getKey();
            }
        });
        //Stack to store time taken to reach target from the positions
        Stack<Double> s = new Stack<>();
        //Start from the highest position
        for (int i = 0; i < pairs.size(); i++) {
            //Calculate time taken to reach target from position i with speed[i]
            double time = ((double) target - (double) pairs.get(i).getKey()) / (double) pairs.get(i).getValue();
            //If the time taken is less than the stack peek value, then the current car can form a fleet with previous as
            // it is starting from lower position but can reach target in less time than from previous position(s)
            if (!s.empty() && time <= s.peek()) {
                continue;
            }
            //Else, just push the time to stack as new fleet will be formed
            s.push(time);
        }
        //Final size of stack will be number of car fleets
        return s.size();
    }

    class Pair<T, V> {
        T key;
        V value;

        Pair(T first, V second) {
            this.key = first;
            this.value = second;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }
}
