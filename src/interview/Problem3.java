package interview;

import java.util.Stack;

public class Problem3 {

    public int minOperations(int[] nums) {
        int[] sumArr = new int[nums.length];
        sumArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArr[i] = sumArr[i - 1] + nums[i];
        }
        int[] minValue = new int[nums.length];
        minValue[nums.length - 1] = Integer.MAX_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            minValue[i] = Math.min(minValue[i + 1], sumArr[i + 1]);
        }
        int count = 0;
        int addValue = 0;
        for (int i = 1; i < nums.length; i++) {
            if (sumArr[i] + addValue < 0) {
                addValue = addValue + Math.abs(minValue[i]);
                sumArr[i] = sumArr[i] + addValue;
                count++;
            }
        }
        return count;
    }

    public int makeArrayPositive(int[] arr) {
        int replacements = 0;
        int[] prefix = new int[arr.length + 1];
        int min = Integer.MAX_VALUE;
        int min_index = 0;
        for (int i = 1; i <= arr.length; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
            if (prefix[i + 1] < min) {
                min = prefix[i + 1];
                min_index = i + 1;
            }
        }
        if (min < 0) {
            replacements++;
            prefix[min_index] = -min + 1;
        }

        return replacements;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, -8, -1, 2};
        Problem3 problem3 = new Problem3();
        System.out.println(problem3.makeArrayPositive(nums));
        nums = new int[]{-1, -1, -1, -1, -1};
        System.out.println(problem3.makeArrayPositive(nums));
    }
}
