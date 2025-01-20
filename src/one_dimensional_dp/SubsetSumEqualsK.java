package one_dimensional_dp;

public class SubsetSumEqualsK {

    //TC O(n*sum), bottom up approach,
    //SC: O(sum), 1D DP
    public boolean isSubsetSum1DDP(int[] nums, int sum) {
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

    //Bottom-up approach or tabulation solution
    //This avoids the issue with top-down approach where we see index out of bound exception if we use dp size [i+1][sum+1],
    // because while (i + 1)th can be store in dp, the call to array with i + 1 in sum calculation (sum - arr[i]) will give stack overflow exception
    // and only using size [i][num+1] is leading to redundant calculations like sum == 0, then return true which cannot be stored currently

    //O(n*sum) TC and O(n*sum) SC(Also, no auxiliary space, as no recursion)
    //Pseudo-polynomial time, NP-Complete problem
    public Boolean isSubsetSumTabulation(int[] nums, int sum) {
        //state dp[i][j] will be true if there exists a subset of elements from set[0 . . . i] with sum value = ‘j’
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];

        //Base case, for sum  = 0, it's true
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        //Base case, for i = 0, empty array, no sum > 0 can be achieved
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                //if the current element has a value greater than the ‘current sum value’
                //we will copy the answer for previous cases
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //if any of the previous states have already experienced the sum=j OR
                    //any previous states experienced a value ‘j – nums[i]’ which will solve our purpose.
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][sum];
    }

    //Using 2D DP
    //O(n*sum) TC and  O(n*sum) SC + O(n) auxiliary stack space
    //Pseudo-polynomial time, NP-Complete problem
    public Boolean isSubsetSum(int[] arr, int sum) {
        //Store the has sum at index for that sum as true or false
        //state dp[i][j] will be true if there exists a subset of elements from set[0 . . . i] with sum value = ‘j’
        Boolean[][] dp = new Boolean[arr.length][sum + 1];

        return isSubsetSumH(arr, 0, sum, dp);
    }

    private Boolean isSubsetSumH(int[] arr, int i, int sum, Boolean[][] dp) {
        if (sum == 0) {
            return true;
        }

        if (i >= arr.length || sum < 0) {
            return false;
        }

        if (dp[i][sum] != null) {
            return dp[i][sum];
        }

        return dp[i][sum] = isSubsetSumH(arr, i + 1, sum - arr[i], dp) || isSubsetSumH(arr, i + 1, sum, dp);
    }

    public static void main(String[] args) {
        SubsetSumEqualsK subsetSumEqualsK = new SubsetSumEqualsK();
        int[] arr = {73, 51, 90, 25, 44, 41, 46, 44, 2, 18, 54, 65, 71, 56, 88, 92, 58, 77, 79, 68, 71, 71, 37, 55, 78, 96, 28, 81,
                74, 48, 4, 30, 49, 39, 59, 35, 94, 44, 96, 47, 26, 32, 1, 5, 40, 37, 56, 33, 80, 11, 16, 86, 77, 19, 24, 63, 91, 66, 34,
                40, 56, 4, 10, 54, 93};
        //o/p is true
        System.out.println(subsetSumEqualsK.isSubsetSum(arr, 2857));
        System.out.println(subsetSumEqualsK.isSubsetSumTabulation(arr, 2857));
        System.out.println(subsetSumEqualsK.isSubsetSum1DDP(arr, 2857));
        //o/p is true
        arr = new int[]{5, 5, 1};
        System.out.println(subsetSumEqualsK.isSubsetSum(arr, 11));
        System.out.println(subsetSumEqualsK.isSubsetSumTabulation(arr, 11));
        System.out.println(subsetSumEqualsK.isSubsetSum1DDP(arr, 11));
        //o/p is false
        arr = new int[]{1, 2, 3, 6};
        System.out.println(subsetSumEqualsK.isSubsetSum(arr, 15));
        System.out.println(subsetSumEqualsK.isSubsetSumTabulation(arr, 15));
        System.out.println(subsetSumEqualsK.isSubsetSum1DDP(arr, 15));
    }
}
