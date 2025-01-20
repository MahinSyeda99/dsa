package one_dimensional_dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    //O(n) TC and O(n) S.C
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return robH(nums, n, 0, dp);
    }

    private int robH(int[] nums, int n, int i, int[] dp) {
        //If we cross the nums size, then no house left to rob, so return 0
        if(i >= n) {
            return 0;
        }
        if(dp[i] != -1) {
            return dp[i];
        }
        //Either we include the current house and rob the next to next house or we don't rob current house and rob the next house
        return dp[i] = Math.max(nums[i] + robH(nums, n, i + 2, dp), robH(nums, n, i + 1, dp));
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {1,2,3,1};
        //o/p is 4
        System.out.println(houseRobber.rob(nums));
        nums = new int[]{2,7,9,3,1};
        //o/p is 12
        System.out.println(houseRobber.rob(nums));
        nums = new int[]{1,2};
        //o/p is 2
        System.out.println(houseRobber.rob(nums));
    }
}
