package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDuplicates {

    //O(n*2^n) solution , because number of subsets is 2^n, and to generate one subset we need O(n) (max length)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //Sort in ascending order
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetH(nums, 0, nums.length, new ArrayList<>(), result);
        return result;
    }

    private void subsetH(int[] nums, int i, int n, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for(int j = i; j < n; j++) {
            //If its a duplicate, then just continue i != j because, we don't want duplicate in next possible value of forming a subset from one element. i.e [1, 2, 2]. From 1 we don't want to branh to two 2's. The second branch to 2, should not be taken
            if(j!= i && nums[j] == nums[j-1]) {
                continue;
            }
            //Add the element to subset
            subset.add(nums[j]);
            subsetH(nums, j + 1, n, subset, result);
            //Don't add element to subset
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubsetsWithDuplicates subsets = new SubsetsWithDuplicates();
        //O/p should be [[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println(subsets.subsetsWithDup(nums));
    }
}
