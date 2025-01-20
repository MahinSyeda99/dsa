package slidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//Monotonically decreasing deque problem
//Youtube video: https://www.youtube.com/watch?v=DfljaUwZsOk&ab_channel=NeetCode

class SlidingWindowMaximum {

    //Using monotonically decreasing dequeue
    //Why deque? Because we want to remove elements from both front and back
    //O(n) TC and O(k) SC
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        //Create a deque of indices
        Deque<Integer> dq = new ArrayDeque<>();
        //left pointer
        int l = 0;
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                //If the last element of deque is less than current element, then we found a maximum, so just pop the last index from deque
                dq.removeLast();
            }
            //Add current index
            dq.addLast(i);
        }
        //Max for the current window will be first element in dequeue
        //Add first element of the result
        result[l++] = nums[dq.getFirst()];
        for (int r = k; r < nums.length; r++) {
            //If left pointer is greater than first index of deque, we already crossed that window, so just remove the first element of deque
            if (l > dq.getFirst()) {
                dq.removeFirst();
            }
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[r]) {
                //If the last element of deque is less than current element, then we found a maximum, so just pop the last index from deque
                dq.removeLast();
            }
            //Add current index
            dq.addLast(r);
            //Add the first element of deque - which will be maximum for the current window
            result[l++] = nums[dq.getFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        //o/p is [3,3,5,5,6,7]
        System.out.println(Arrays.toString(slidingWindowMaximum.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
