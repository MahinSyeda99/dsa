package twoPointers;

import java.util.Stack;

public class TrappingRainWater {

    //Using stack
    //O(n) TC and O(n) SC
    public int trap(int[] height) {
        //Monotonic stack, decreasing elements
        Stack<Integer> s = new Stack<>();
        int result = 0;
        int i = 0;
        int distance = 0;
        while (i < height.length) {
            while (!s.empty() && height[i] > height[s.peek()]) {
                int top = s.pop();
                if (s.empty()) {
                    break;
                }
                distance = i - s.peek() - 1;
                result += (Math.min(height[i], height[s.peek()]) - height[top]) * distance;
            }
            s.push(i++);
        }
        return result;
    }
}
