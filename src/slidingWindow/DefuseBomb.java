package slidingWindow;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/defuse-the-bomb/
 */
public class DefuseBomb {

    //O(n) TC solution
    //O(n) SC solution
    //Sliding window solution
    //Solution 2
    public int[] decrypt2(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }
        int sum = 0;
        int start = 1;
        int end = k;
        if (k < 0) {
            start = n - Math.abs(k);
            end = n - 1;
        }
        for (int i = start; i <= end; i++) {
            sum = sum + code[i];
        }
        for (int i = 0; i < n; i++) {
            result[i] = sum;
            sum = sum - code[start%n];
            sum = sum + code[(end + 1)%n];
            start++;
            end++;
        }
        return result;
    }

    //O(n) TC solution
    //O(n) SC solution
    //Using prefix sum
    //Solution 1
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            Arrays.fill(result, 0);
            return result;
        }
        //Find the prefix sum array
        int[] prefixSum = new int[n];
        prefixSum[0] = code[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + code[i];
        }
        if (k > 0) {
            for (int i = 0; i < n; i++) {
                if (i + k >= n) {
                    result[i] = prefixSum[n - 1] - prefixSum[i] + prefixSum[k - (n - 1 - i) - 1];
                } else {
                    result[i] = prefixSum[i + k] - prefixSum[i];
                }
            }
        } else {
            k = Math.abs(k);
            for (int i = 0; i < n; i++) {
                if (i - k - 1 < 0) {
                    if (i == 0) {
                        result[i] = prefixSum[n - 1] - prefixSum[n - 1 - k];
                    } else {
                        result[i] = prefixSum[i - 1] + (prefixSum[n - 1] - prefixSum[n - 1 - k + i]);
                    }
                } else {
                    result[i] = prefixSum[i - 1] - prefixSum[i - 1 - k];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DefuseBomb defuseBomb = new DefuseBomb();
        int[] code = {5, 7, 1, 4};
        //o/p is [12, 10, 16, 13]
        System.out.println(Arrays.toString(defuseBomb.decrypt(code, 3)));
        code = new int[]{2, 4, 9, 3};
        //o/p is [12, 5, 6, 13]
        System.out.println(Arrays.toString(defuseBomb.decrypt(code, -2)));
        code = new int[]{5, 2, 2, 3, 1};
        //o/p is [7, 6, 9, 8, 9]
        System.out.println(Arrays.toString(defuseBomb.decrypt(code, 3)));
    }
}
