package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    //O(2^n) TC
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsH(nums, 0, result, new ArrayList<>());
        return result;
    }

    //For each element, we have two possibilities: either we include it or exclude it
    public void subsetsH(int[] nums, int i, List<List<Integer>> result, List<Integer> subsets) {
        //Add the generated subset to result
        result.add(new ArrayList<>(subsets));
        for (int j = i; j < nums.length; j++) {
            // Take the element - Adds the current element nums[j] to the subsets.
            subsets.add(nums[j]);
            //Call with next index including current element
            subsetsH(nums, j + 1, result, subsets);
            // Don't include current element
            subsets.remove(subsets.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsets(nums));
    }
}
