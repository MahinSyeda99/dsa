package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 *
 *
 * arr = [3, 1, 2, 4]
 *
 *
 * [3], [2], [1], [4], [3, 1], [1,2], [2, 4] [3,1,2], [1,2,4], [3,1,2,4]
 * sum of min's = 3 + 2 + 1 + 4 + 1 + 1 + 2 + 1 + 1 + 1 = 17
 *
 *
 * [3, 1, 2, 4]
 *
 * minLeft = [3, 1, 1, 1]
 * minRight = [1, 1, 2, 4]
 *
 *
 * [3, 1, 2]
 *
 * [3], [1], [2]
 * [3, 1, 2]
 * [3, 1], [1, 2]
 *
 * 3 + 1 + 2 + 1 + 1 + 1 = 9
 *
 * leftMinIndex = [-1, -1, 1]
 * rightMinIndex = [1, 3, 3]
 *
 * 3,1
 * 3,1,2
 * 1
 * 1,2
 *
 * 3 * 1 + 1 * 4 + 2 * 1 = 9
 *
 * [4, 3, 1, 2]
 *
 * 4 , [4, 3], [4, 3, 1], [4, 3, 1, 2]
 * 3, [3, 1], [3, 1, 2]
 *
 *
 * (rightIndex - i) * (i - leftIndex)
 *
 * (3 - 1) * (1 - (-1)) = 2 * 2 = 4
 *
 *
 *
 *
 * [71,55,82,55]
 *
 * [71]
 *
 * [71, 55]
 * [71, 55, 82]
 * [71, 55, 82, 55]
 *
 * [55]
 * [55, 82]
 * [55, 82, 55]
 *
 * [82]
 *
 * [82, 55]
 * [55]
 *
 *
 * 71 + 55 + 55 + 55 + 55 + 55 + 55 + 82 + 55 + 55 = 593
 *
 *
 * left = [-1, -1, 1, 1]
 * right = [1, 3, 3, 4]
 *
 * 71 * (1 - 0) * (0 - (-1)) = 71
 *
 * 55 * (3 - 1) * (1 - (-1)) = 55 * 2 * 2 =
 *
 * 82 * (3 - 2) * (2 - 1) = 82 * 1 = 82
 *
 * 55 * (4 - 3) * (3 - 1) = 55 * 2
 */
public class SumOfMinimumElementsOfSubArrays {

    public void minSumOfSubArray(int[] arr) {

        Stack<Integer> s = new Stack<>();
        int[] leftMinIndex = new int[arr.length];
        int[] rightMinIndex = new int[arr.length];
        Arrays.fill(leftMinIndex, -1);
        Arrays.fill(rightMinIndex, arr.length);

        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] > arr[i]) {
                s.pop();
            }
            if (!s.isEmpty()) {
                leftMinIndex[i] = s.peek();
            }
            s.add(i);
        }
        s = new Stack<>();
        for (int i = arr.length -  1; i >= 0; i--) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                s.pop();
            }
            if (!s.isEmpty()) {
                rightMinIndex[i] = s.peek();
            }
            s.add(i);
        }
        System.out.println(Arrays.toString(leftMinIndex));
        System.out.println(Arrays.toString(rightMinIndex));
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + (rightMinIndex[i] - i) * (i - leftMinIndex[i]) * arr[i];
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{71,55,82,55};
        SumOfMinimumElementsOfSubArrays sumOfMinimumElementsOfSubArrays = new SumOfMinimumElementsOfSubArrays();
        sumOfMinimumElementsOfSubArrays.minSumOfSubArray(arr);
    }
}
