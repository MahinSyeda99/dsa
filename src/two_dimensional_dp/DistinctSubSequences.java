package two_dimensional_dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/distinct-subsequences/description/
 */
public class DistinctSubSequences {

    //Solution 5
    //Bottom up approach
    //O(n1 * n2) TC
    //o(n1 * n2) SC
    public int numDistinct5(String s, String t) {
        //dp[i][j] represents number of subsequences possible from s[0, i) that match t[0, j)
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        //Base case
        //For empty string t, there is 1 way
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                //Match the current characters
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //Add dp[i-1][j-1] (i.e., number of ways if we include s[i-1] to match t[j-1]). Skip the current character in s: Add dp[i-1][j] (i.e., number of ways if we skip s[i-1]).
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //If s[i-1] != t[j-1], the only option is to skip the current character in s, i.e., dp[i][j] = dp[i-1][j]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    //Solution 4
    //Top down DP
    //O(n1 * n2) TC, where n1 is length of string s, n2 is length of string t
    //O(n1 * n2) SC
    public int numDistinct4(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return numDistinctH4(s, t, 0, 0, dp);
    }

    private int numDistinctH4(String s, String t, int i, int j, int[][] dp) {
        //If we find a match, then return 1
        //No need to maintain additional str, because we increase j only when there is a match, so if j is crossing t.length(), then we found soln
        if (j >= t.length()) {
            return 1;
        }

        //If either of s or t cross the boundaries, return 0
        if (i >= s.length()) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int count = 0;
        if (s.charAt(i) == t.charAt(j)) {
            //If the characters are equal, then we either take it or we don't take it
            count = numDistinctH4(s, t, i + 1, j + 1, dp) + numDistinctH4(s, t, i + 1, j, dp);
        } else {
            //If characters are not equal, then we definitely don't take it
            count = numDistinctH4(s, t, i + 1, j, dp);
        }
        return dp[i][j] = count;
    }

    //Solution 3
    //Top down DP
    //O(n1 * n2 * n1) TC, where n1 is length of string s, n2 is length of string t
    //Additional n1 because of for loop
    public int numDistinct3(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return numDistinctH3(s, t, "", 0, 0, dp);
    }

    private int numDistinctH3(String s, String t, String str, int i, int j, int[][] dp) {
        //If we find a match, then return 1
        if (str.equals(t)) {
            return 1;
        }

        //If either of s or t cross the boundaries, return 0
        if (i >= s.length() || j >= t.length()) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int count = 0;
        for (int k = i; k < s.length(); k++) {
            //If the char at i of s is same as char at 0 for t, then we find the subsequences from i + 1 to n1 t.substring(1,n2)
            if (s.charAt(k) == t.charAt(j)) {
                count = count + numDistinctH3(s, t, str + s.charAt(k), k + 1, j + 1, dp);
            }
        }
        return dp[i][j] = count;
    }

    //Solution 2
    //Backtracking, DFS
    //O(n1 * 2^n1) TC, where n1 is length of string s
    //2^n1 => because we have two possibilities: either include the character or don't include
    public int numDistinct2(String s, String t) {
        return numDistinctH2(s, t, "", 0, 0);
    }

    private int numDistinctH2(String s, String t, String str, int i, int j) {
        //If we find a match, then return 1
        if (str.equals(t)) {
            return 1;
        }

        //If either of s or t cross the boundaries, return 0
        if (i >= s.length() || j >= t.length()) {
            return 0;
        }

        int count = 0;
        for (int k = i; k < s.length(); k++) {
            //If the char at i of s is same as char at 0 for t, then we find the subsequences from i + 1 to n1 t.substring(1,n2)
            if (s.charAt(k) == t.charAt(j)) {
                count = count + numDistinctH2(s, t, str + s.charAt(k), k + 1, j + 1);
            }
        }
        return count;
    }


    //Solution 1
    //Backtracking, DFS
    //O(n1 * 2^n1) TC, where n1 is length of string s
    //2^n1 => because we have two possibilities: either include the character or don't include
    public int numDistinct(String s, String t) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            //If the char at i of s is same as char at 0 for t, then we find teh subsequences from i + 1 to n1 t.substring(1,n2)
            if (s.charAt(i) == t.charAt(0)) {
                count = count + numDistinctH(s, t, "" + s.charAt(i), i + 1, 1);
            }
        }
        return count;
    }

    private int numDistinctH(String s, String t, String str, int i, int j) {
        //If we find a match, then return 1
        if (str.equals(t)) {
            return 1;
        }

        //If either of s or t cross the boundaries, return 0
        if (i >= s.length() || j >= t.length()) {
            return 0;
        }

        //Two possibilities, either include or exclude
        return numDistinctH(s, t, str + s.charAt(i), i + 1, j + 1) + numDistinctH(s, t, str, i + 1, j);
    }
}
