package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    //O(2 ^ target) T.C. Because, let's say each element is 1, then we go down the tree till we reach target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subArray = new ArrayList<>();
        combinationSumH(candidates, 0, target, subArray, result);
        return result;
    }

    //At each step, we either include the element or exclude it
    public void combinationSumH(int[] candidates, int i, int target, List<Integer> subArray, List<List<Integer>> result) {
        if (target == 0) {
            //If we found the sum with target, add to result
            result.add(new ArrayList<>(subArray));
            return;
        }
        if (i >= candidates.length || target < 0) {
            return;
        }
        //Include element, but since repeat is allowed, don't yet go to next element, so we still pass i
        subArray.add(candidates[i]);
        combinationSumH(candidates, i, target - candidates[i], subArray, result);

        //Don't include current element
        subArray.remove(subArray.size() - 1);
        combinationSumH(candidates, i + 1, target, subArray, result);
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        CombinationSum combinationSum = new CombinationSum();
        System.out.println("Result: " + combinationSum.combinationSum(candidates, 7));
        candidates = new int[]{2, 3, 5};
        System.out.println("Result: " + combinationSum.combinationSum(candidates, 8));
    }
}
