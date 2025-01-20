package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Arrays.fill(ans, 0);
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!s.empty() && temperatures[s.peek()] < temperatures[i]) {
                ans[s.peek()] = i - s.peek();
                s.pop();
            }
            s.push(i);
        }
        return ans;
    }
}
