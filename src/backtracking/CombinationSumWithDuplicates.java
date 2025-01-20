package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumWithDuplicates {

    //O(n*2^n) worst case
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Sort in increasing order
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSumH(candidates, 0, candidates.length, target, new ArrayList<>(), result);
        return result;
    }

    private void combinationSumH(int[] candidates, int i, int n, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (i >= n || target < 0) {
            return;
        }

        //Include current element in path
        path.add(candidates[i]);
        combinationSumH(candidates, i + 1, n, target - candidates[i], path, result);

        //Don't include current element in path
        path.remove(path.size() - 1);
        //We don't want to include it again if there are duplicates forward, as we don't want to consider the element that we don't want to include
        while (i + 1 < n && candidates[i + 1] == candidates[i]) {
            i++;
        }
        combinationSumH(candidates, i + 1, n, target, path, result);
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        CombinationSumWithDuplicates combinationSumWithDuplicates = new CombinationSumWithDuplicates();
        //O/P: [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println(combinationSumWithDuplicates.combinationSum2(candidates, 8));
        candidates = new int[]{2, 5, 2, 1, 2};
        //O/P: [[1,2,2],[5]]
        System.out.println(combinationSumWithDuplicates.combinationSum2(candidates, 5));
    }
}
