package greedy;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 */
public class MaximumSubArray {

    //O(n) TC and O(n) S.C
    public int maxSubArrayUsingDp(int[] nums) {
        //Initialize an array for dp
        int[] dp = new int[nums.length];
        int maxSum = nums[0];
        dp[0] = nums[0];
        //For each element, either the current element is max or the sum with prev elements is max
        //Just store the max sum in the dp for that element
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    //O(n) TC and O(1) SC
    public int maxSubArrayWithoutDp(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        //For each element, either the current element is max or the sum with prev elements is max
        //Just store the max sum for that element
        //Kadane's Algorithm
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            maxSum = Math.max(maxSum, sum);
            //If current sum < 0, then we set sum to 0, to find sum of new subarray
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubArray maximumSubArray = new MaximumSubArray();
        //O/p should be 6, subArray: [4,-1,2,1]
        System.out.println(maximumSubArray.maxSubArrayWithoutDp(nums));
    }
}
