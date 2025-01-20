package two_dimensional_dp;

import one_dimensional_dp.LongestIncreasingSubsequence;

public class LongestCommonSubSequence {

    //Bottom up approach O(m * n) TC and O(max(m, n)) SC (i.e 1D DP)
    public int longestCommonSubsequenceUsing1DDP(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m > n) {
            //Swap
            String temp = text1;
            text1 = text2;
            text2 = temp;
            m = text1.length();
            n = text2.length();
        }
        //dp[i] represents the longest common subsequence of text1[0 ... i - 1]
        //We only need 1D array because we only need dp[i][j - 1] => only prev values
        int[] dp = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int prev = 0, prevCurrent; // Previous diagonal value (dp[i-1][j-1])
            for (int j = 0; j < n; j++) {
                prevCurrent = prev;
                prev = dp[j + 1];
                if (text1.charAt(i) == text2.charAt(j)) {
                    //If character's match
                    dp[j + 1] = 1 + prevCurrent;
                } else {
                    //If they don't match, take the maximum from either excluding the character from string a or string b
                    // Take the max of previous entries
                    dp[j + 1] = Math.max(prev, dp[j]);
                }
            }
        }
        return dp[n];
    }


    //Bottom up approach O(m * n) TC and O((m + 1) * (n + 1)) SC
    public int longestCommonSubsequence2ndApproach(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        //dp[i][j] represents the longest common subsequence of text1[0 ... i - 1] & text2[0 ... j - 1]
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0; //LCS for empty strings is 0
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    //If character's match
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //If they don't match, take the maximum from either excluding the character from string a or string b
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    //Bottom up approach O(m * n) TC and O(m * n) SC
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        //dp[i][j] represents the longest common subsequence of text1[0 ... i] & text2[0 ... j]
        int[][] dp = new int[m][n];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int j = 1; j < n; j++) {
            if (text1.charAt(0) == text2.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    //If character's match
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //If they don't match, take the maximum from either excluding the character from string a or string b
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();
        //o/p is 3
        System.out.println(longestCommonSubSequence.longestCommonSubsequence("abcde", "ace"));
        //o/p is 3
        System.out.println(longestCommonSubSequence.longestCommonSubsequence("abc", "abc"));
        //o/p is 0
        System.out.println(longestCommonSubSequence.longestCommonSubsequence("abc", "def"));
    }
}
