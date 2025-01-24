package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/score-of-parentheses/description/
 */
public class StoreOfParentheses {

    //O(n) TC and O(n) SC
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            //If the value of char is equal to ‘(‘, push 0 to the stack.
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                int val = stack.pop();
                if (val == 0) {
                    //No inner parentheses, its () => value = 1
                    val = 1;
                } else {
                    //val is not 1 => we encountered ')' previously => inner parentheses exist => value = 2 * inner parentheses value
                    val = 2 * val;
                }
                if (!stack.isEmpty()) {
                    //Update the parent parentheses score
                    stack.push(stack.pop() + val);
                } else {
                    stack.push(val);
                }
            }
        }
        return stack.peek();
    }
}
