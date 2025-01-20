package one_dimensional_dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionSubsetEqualSum {

    //O(n*sum) TC and O(sum) SC
    public boolean canPartitionUsing1DDp(int[] nums) {
        //Calculate sum of all numbers
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        //To find two subsets having equal sums, the length of two subsets should be half of total sum
        //And, if half sum is odd, then there is no way we find two subsets of equal sum, so return false
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;

        //Find if there is a subset with sum equal to half of total sum
        //And, if there is such solution, then the other half is definitely going to be half of total sum

        //state dp[i][j] will be true if there exists a subset of elements from set[0 . . . i] with sum value = ‘j’
        boolean[] dp = new boolean[sum + 1];

        //For sum = 0, can be achieved by not including any element
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            //updates the DP array from right to left. This ensures that each number is used at most once for each possible sum.
            //If you update dp[j] from left to right, you may incorrectly use the same element multiple times,
            // which violates the subset sum rules.
            for (int j = sum; j >= 1; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    //O(n*sum) TC and O(n*sum) SC(Also, no auxiliary space, as no recursion)
    public boolean canPartitionTabulation(int[] nums) {
        //Calculate sum of all numbers
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        //To find two subsets having equal sums, the length of two subsets should be half of total sum
        //And, if half sum is odd, then there is no way we find two subsets of equal sum, so return false
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;

        //Find if there is a subset with sum equal to half of total sum
        //And, if there is such solution, then the other half is definitely going to be half of total sum

        //state dp[i][j] will be true if there exists a subset of elements from set[0 . . . i] with sum value = ‘j’
        boolean[] dp = new boolean[sum + 1];

        //For sum = 0, can be achieved by not including any element
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            //updates the DP array from right to left. This ensures that each number is used at most once for each possible sum.
            //If you update dp[j] from left to right, you may incorrectly use the same element multiple times,
            // which violates the subset sum rules.
            for (int j = sum; j >= 1; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    //Top-down approach or memoization solution
    //O(n*sum) T.C solution, 2D DP and O(n*sum) S.C solution
    public boolean canPartition(int[] nums) {
        //Calculate sum of all numbers
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        //To find two subsets having equal sums, the length of two subsets should be half of total sum
        //And, if half sum is odd, then there is no way we find two subsets of equal sum, so return false
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;

        //Find if there is a subset with sum equal to half of total sum
        //And, if there is such solution, then the other half is definitely going to be half of total sum
        //Store the has sum at index for that sum as true or false
        Boolean[][] dp = new Boolean[nums.length][sum + 1];
        return isSubsetSumH(nums, 0, sum, dp);
    }

    private Boolean isSubsetSumH(int[] nums, int i, int sum, Boolean[][] dp) {
        if (sum == 0) {
            return true;
        }

        if (i >= nums.length || sum < 0) {
            return false;
        }

        if (dp[i][sum] != null) {
            return dp[i][sum];
        }
        return dp[i][sum] = isSubsetSumH(nums, i + 1, sum - nums[i], dp) || isSubsetSumH(nums, i + 1, sum, dp);
    }

    public static void main(String[] args) {
        PartitionSubsetEqualSum partitionSubsetEqualSum = new PartitionSubsetEqualSum();
        int[] nums = {1, 5, 11, 5};
        //o/p is true i.e [1,5,5] and [11]
        System.out.println(partitionSubsetEqualSum.canPartition(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionTabulation(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionUsing1DDp(nums));
        nums = new int[]{1, 2, 3, 5};
        //o/p is false
        System.out.println(partitionSubsetEqualSum.canPartition(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionTabulation(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionUsing1DDp(nums));
        nums = new int[]{1, 2, 5};
        //o/p is false
        System.out.println(partitionSubsetEqualSum.canPartition(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionTabulation(nums));
        System.out.println(partitionSubsetEqualSum.canPartitionUsing1DDp(nums));
    }
}
