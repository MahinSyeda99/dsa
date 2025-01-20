package slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
 */
public class ShortestSubArrayWithSumAtleastK {

    //O(n) TC
    //O(n) SC, using Monotonically increasing deque
    /**
     * We want to find the shortest sub array with length k,
     * so we want to find the longest length with minimum value in the prefix sums, whose removal will satisfy >= k condition
     */
    public int shortestSubarray(int[] nums, int k) {
        //To keep track of minimum prefix sums and the index at which the prefix sum is calculated
        Deque<Pair<Long, Integer>> dq = new ArrayDeque<>();
        //Running sum
        long sum = 0;
        int result = Integer.MAX_VALUE;
        for (int r = 0; r < nums.length; r++) {
            //Sum ending at index i
            sum = sum + nums[r];

            //If we find the current sum >= k, then this is also one subarray
            if (sum >= k) {
                result = Math.min(result, r + 1);
            }

            //Deque is monotonically increasing queue, we have minimum prefix sum at the beginning
            //We check if sum - beginning prefix sum value in deque >= k, if yes, then the length of subarray = r - index of the prefix sum where the prefix sum is calculated
            //We can also remove the beginning element, because we want shortest length, and we already calculated shortest length by removing that prefix sum, going forward the length will only increase as r increases
            while (!dq.isEmpty() && sum - dq.getFirst().getKey() >= k) {
                result = Math.min(result, r - dq.getFirst().getValue());
                dq.removeFirst();
            }

            //If our sum <= dq.getLast().getKey(), then it means sum is minimum value, we want minimum values with longest length, so we can remove values from dq from end whose sum is greater than current sum
            while (!dq.isEmpty() && dq.getLast().getKey() >= sum) {
                dq.removeLast();
            }
            //Finally, add the pair of current sum and index
            dq.addLast(new Pair<>(sum, r));
        }
        return result == Integer.MAX_VALUE ? -1 : result;
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
