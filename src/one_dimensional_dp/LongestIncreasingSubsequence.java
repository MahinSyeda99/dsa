package one_dimensional_dp;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    //O(nlogn) TC and O(n) SC
    //Greedy with binary search solution
    public int lengthOfLISUsingGreedy(int[] nums) {
        //Store increasing subsequence in a list
        List<Integer> subSeq = new ArrayList<>();
        subSeq.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            //If the value is greater than last value of subsequence, then its an increasing sequence, so just add it to subseq list
            if (nums[i] > subSeq.get(subSeq.size() - 1)) {
                subSeq.add(nums[i]);
            } else {
                //Find the index of the smallest value >= nums[i]
                int index = findIndex(subSeq, subSeq.size(), nums[i]);
                //Replace that index value with nums[i]
                subSeq.set(index, nums[i]);
            }
        }
        return subSeq.size();
    }

    //Using binary search, because the sequence is increasing, so its sorted
    //O(logn) TCs
    private int findIndex(List<Integer> subSeq, int n, int target) {
        int low = 0;
        int high = n - 1;
        while(low<=high) {
            int mid = low + (high - low)/2;
            if(subSeq.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    //DP solution, bottom up approach
    //O(n^2) TC and O(n) SC
    public int lengthOfLIS(int[] nums) {
        //Store maximum possible increasing subsequence length ending at that index
        int[] dp = new int[nums.length];

        //Base case
        dp[0] = 1; //For one number, it itself is increasing sequence

        //Result
        int maxLength = 1;

        for (int i = 1; i < nums.length; i++) {
            int prevMax = 0; //Max of prev length's for numbers less than current number at index i
            for (int j = i - 1; j >= 0; j--) {
                //If the value at index j is less than current value at index i, then it can be part of increasing sub sequence
                //Also, if the longest length value is greater than prevMax, then only we consider that value
                if (nums[j] < nums[i] && dp[j] > prevMax) {
                    prevMax = dp[j];
                }
            }
            dp[i] = prevMax + 1; //i.e. we include the current number at index i
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
        //o/p is 4 ([2,3,7,101])
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
        System.out.println(longestIncreasingSubsequence.lengthOfLISUsingGreedy(nums));
        nums = new int[]{0,1,0,3,2,3};
        //o/p is 4 ([0, 1, 2, 3])
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
        System.out.println(longestIncreasingSubsequence.lengthOfLISUsingGreedy(nums));
        nums = new int[]{7, 7, 7, 7, 7};
        //o/p is 1 ([7])
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
        System.out.println(longestIncreasingSubsequence.lengthOfLISUsingGreedy(nums));
    }
}
