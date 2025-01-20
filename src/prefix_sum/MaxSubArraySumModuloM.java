package prefix_sum;

import java.util.Set;
import java.util.TreeSet;

public class MaxSubArraySumModuloM {

    //O(nlogn) solution
    //where, O(logn) to insert and search in the TreeSet
    public int maxSubArraySumModuloM(int[] arr, int m) {
        //Create a prefix array
        int[] prefix = new int[arr.length];
        TreeSet<Integer> s = new TreeSet<>();
        int maxSeen = Integer.MIN_VALUE;
        int result = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            prefix[i] = (sum + arr[i]) % m;
            if (prefix[i] > maxSeen) {
                maxSeen = prefix[i];
                result = Math.max(maxSeen, result);
                s.add(prefix[i]);
                continue;
            }
            //Find smallest Pj > Pi such that Sumj+1...i = (prefix[i] - prefix[j] + 1)%m  is maximized
            Integer higher = s.higher(prefix[i]);
            if (higher != null) {
                result = Math.max(result, prefix[i] - higher + m);
            }
            s.add(prefix[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 9, 9, 5};
        MaxSubArraySumModuloM maxSubArraySumModuloM = new MaxSubArraySumModuloM();
        //o/p is 6
        System.out.println(maxSubArraySumModuloM.maxSubArraySumModuloM(arr, 7));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, -8, 2, 12, 11};
        //o/p is 14
        System.out.println(maxSubArraySumModuloM.maxSubArraySumModuloM(arr, 15));
    }
}
