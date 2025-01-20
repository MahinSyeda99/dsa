package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleInHistogram {

    //O(n^2) TC and O(n) SC solution
    //Monotonic increasing stack
    public int largestRectangleArea(int[] heights) {
        //Store indexes of heights
        Stack<Integer> s = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            //If we find a height greater than stack's peek height, then add to stack
            if (s.empty() || heights[i] > heights[s.peek()]) {
                s.push(i);
            } else {
                //If we find a height less than stack peek's height, then we can't form with the further heights any more
                // rectangle with stack peek's height
                while (!s.empty() && heights[i] < heights[s.peek()]) {
                    //Get the top index
                    int top = s.peek();
                    //Pop the index
                    s.pop();
                    //If stack is not empty, Area is (current index - current stack peek after popping - 1) * top height
                    //If stack is empty, then there are no more indexes of height less than current height, so area will be top height * current index
                    int area = s.empty() ? heights[top] * (i) : heights[top] * (i - s.peek() - 1);
                    max = Math.max(max, area);
                }
                s.push(i);
            }
        }

        //If stack is not empty, then just consider current index as length and do the same
        while (!s.empty()) {
            int top = s.peek();
            s.pop();
            int area = s.empty() ? heights[top] * (heights.length) : heights[top] * (heights.length - s.peek() - 1);
            max = Math.max(max, area);
        }
        return max;
    }
}
