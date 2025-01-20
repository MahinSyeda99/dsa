package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/description/
 */
class MinStack {
    Stack<Integer> s;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        s = new Stack<>();
    }

    public void push(int val) {
        if (val <= min) {
            s.push(min);
            min = val;
        }
        s.push(val);
    }

    public void pop() {
        if (s.pop() == min) {
            min = s.pop();
        }
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return min;
    }
}
