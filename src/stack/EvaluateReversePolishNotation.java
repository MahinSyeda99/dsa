package stack;

import java.util.Stack;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for (String str : tokens) {
            switch (str) {
                case "+": {
                    int b = s.pop();
                    int a = s.pop();
                    s.push(a + b);
                    break;
                }
                case "-": {
                    int b = s.pop();
                    int a = s.pop();
                    s.push(a - b);
                    break;
                }
                case "*": {
                    int b = s.pop();
                    int a = s.pop();
                    s.push(a * b);
                    break;
                }
                case "/": {
                    int b = s.pop();
                    int a = s.pop();
                    s.push(a / b);
                    break;
                }
                default:
                    s.push(Integer.parseInt(str));
                    break;
            }
        }
        return s.peek();
    }
}
