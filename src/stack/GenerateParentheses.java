package stack;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesesH("", n, n, result);
        return result;
    }

    public void generateParenthesesH(String s, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0) {
            generateParenthesesH(s + "(", left - 1, right, result);
        }
        if (left < right) {
            generateParenthesesH(s + ")", left, right - 1, result);
        }
    }
}
