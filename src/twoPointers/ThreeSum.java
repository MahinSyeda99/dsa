package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    //O(n^2) TC
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int j;
        int k;
        int sum;
        int target;
        for (int i = 0; i < nums.length - 2; i++) {
            //We don't want duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            j = i + 1;
            k = nums.length - 1;
            while (j < k) {
                sum = nums[j] + nums[k];
                target = -1 * nums[i];
                if (sum > target) {
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    ans.add(nums[k]);
                    result.add(ans);
                    j++;
                    k--;
                    //To avoid duplicates, when we found one of the target triplets, we move j once, but we check the number after we move j.
                    // If the number is the same previous number, we continue to move j until we find different number,
                    // so that we can avoid duplicate combination.
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    //Same case with k
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return result;
    }
}
