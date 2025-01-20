package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    //O(n*n!) T.C
    public List<List<Integer>> permuteWithSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHSwap(nums, 0, nums.length, result);
        return result;
    }

    private void permuteHSwap(int[] nums, int index, int n, List<List<Integer>> result) {
        if (index == n) {
            result.add(Arrays.stream(nums)     // IntStream
                    .boxed()             // Stream<Integer>
                    .collect(Collectors.toList()));
        }
        for (int i = index; i < n; i++) {
            //Swap i with index
            swap(nums, i, index);
            //Recursive call to create permutations for the next element
            permuteHSwap(nums, index + 1, n, result);
            //Swap it back, i.e backtracking
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }


    //O(n*n!) T.C
    public List<List<Integer>> permute(int[] nums) {
        //Sort in increasing order
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        permuteH(nums, nums.length, result);
        return result;
    }

    private void permuteH(int[] nums, int n, List<List<Integer>> result) {
        result.add(Arrays.stream(nums)     // IntStream
                .boxed()             // Stream<Integer>
                .collect(Collectors.toList()));
        //Find index where that value is less than the next value
        int k;
        for (k = n - 2; k >= 0; k--) {
            if (nums[k] < nums[k + 1]) {
                break;
            }
        }
        //If it is entirely in decreasing, then its the last possible permutation
        if (k < 0) {
            return;
        }
        //Find the index whose value is less than min and greater than value at k in the values from k + 2
        int minIndex = k + 1;
        int min = nums[k + 1];
        for (int i = k + 2; i < n; i++) {
            if (nums[i] < min && nums[i] > nums[k]) {
                min = nums[i];
                minIndex = i;
            }
        }
        //Swap value at k with value at minIndex
        int temp = nums[k];
        nums[k] = nums[minIndex];
        nums[minIndex] = temp;
        //reverse the values after kth index
        reverse(nums, k + 1, n);
        //Find the next permutation
        permuteH(nums, n, result);
    }

    private void reverse(int[] nums, int i, int n) {
        int j = n - 1;
        while (i <= j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permuteWithSwap(nums));
    }
}
