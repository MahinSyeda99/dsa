package stack;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stk.push(s.charAt(i));
            } else {
                if (stk.empty()) {
                    return false;
                }
                if ((s.charAt(i) == ')' && stk.peek() == '(') ||
                        (s.charAt(i) == '}' && stk.peek() == '{') ||
                        (s.charAt(i) == ']' && stk.peek() == '[')) {
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        return stk.empty();
    }
}
