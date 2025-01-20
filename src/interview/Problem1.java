package interview;


import java.util.Arrays;
import java.util.Stack;

public class Problem1 {

    public int[] stockSpan(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                result[i] = 1;
                stack.add(i);
                continue;
            }
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = i - stack.peek();
            } else {
                result[i] = i + 1;
            }
            stack.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {100, 80, 60, 70, 60, 75, 85};
        Problem1 problem1 = new Problem1();
        System.out.println(Arrays.toString(problem1.stockSpan(nums)));
        nums = new int[]{10, 4, 5, 90, 120, 80};
        System.out.println(Arrays.toString(problem1.stockSpan(nums)));
        nums = new int[]{11, 4, 5, 90, 120, 80};
        System.out.println(Arrays.toString(problem1.stockSpan(nums)));
    }
}
