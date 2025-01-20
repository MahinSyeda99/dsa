package two_dimensional_dp;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    //O(n * amount) TC and O(n * amount) SC
    public int findTargetSumWays(int[] nums, int target) {
        //To avoid negative indices because of negative target, using map instead of 2D array
        //dp[i][j] where i is index and j is amount <=> In map, Key is i and value is map of target and no. of ways of achieving target from i
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        return targetSumH(nums, target, 0, dp);
    }

    private int targetSumH(int[] nums, int target, int i, Map<Integer, Map<Integer, Integer>> dp) {
        if (i >= nums.length) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp.get(i) != null && dp.get(i).get(target) != null) {
            return dp.get(i).get(target);
        }

        int positive = targetSumH(nums, target - nums[i], i + 1, dp);
        int negative = targetSumH(nums, target + nums[i], i + 1, dp);
        if (dp.get(i) != null) {
            dp.get(i).put(target, positive + negative);
        } else {
            Map<Integer, Integer> mp = new HashMap<>();
            mp.put(target, positive + negative);
            dp.put(i, mp);
        }
        return positive + negative;
    }
}
