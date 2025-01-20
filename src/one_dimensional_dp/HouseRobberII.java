package one_dimensional_dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber-ii/description/
 */
public class HouseRobberII {

    //O(n) TC
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);
        //first and last houses are adjacent, so we can't rob both of them on the same night.
        //So, scenario1:  exclude the last house and consider only the houses from the first house to the second-to-last house.
        //Scenario 2: Exclude first house and consider houses from second house to last house
        //Final result is the maximum of the two scenarios.
        return Math.max(robH(nums, n - 1, 0, dp1), robH(nums, n, 1, dp2));
    }

    public int robH(int[] nums, int n, int i, int[] dp) {
        if (i >= n) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        return dp[i] = Math.max(nums[i] + robH(nums, n, i + 2, dp), robH(nums, n, i + 1, dp));
    }

    public static void main(String[] args) {
        HouseRobberII houseRobberII = new HouseRobberII();
        int[] nums = {2, 3, 2};
        //o/p is 3
        System.out.println(houseRobberII.rob(nums));
        nums = new int[]{1, 2, 3, 1};
        //o/p is 4
        System.out.println(houseRobberII.rob(nums));
        nums = new int[]{1, 2, 3};
        //o/p is 3
        System.out.println(houseRobberII.rob(nums));
    }
}
