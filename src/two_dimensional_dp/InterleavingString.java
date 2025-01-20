package two_dimensional_dp;

/**
 * https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {

    //Solution4
    //Bottom up approach
    //O(n1 * n2) TC solution and O(n1 * n2) SC
    public boolean isInterleave4(String s1, String s2, String s3) {
        //If the sum of s1 and s2 length is not same as s3 length, then we can't divide the s1 and s2 strings to form s3
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        //dp[i][j] represents if s3[0, i+j) can be formed as interleaving string from s1[0, i) and s2[0, j)
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        //Base case
        dp[0][0] = true; //empty s1 and s2 can form an empty s3

        //Whether the first i characters of s1 match the first i characters of s3
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s3.charAt(i)) {
                dp[i][0] = true;
            } else {
                break; // If there's a mismatch, break out of the loop
            }
        }

        //Whether the first j characters of s2 match the first j characters of s3
        for (int j = 0; j < s2.length(); j++) {
            if (s2.charAt(j) == s3.charAt(j)) {
                dp[0][j] = true;
            } else {
                break; // If there's a mismatch, break out of the loop
            }
        }

        //For each combination of i and j, check if the current character of s3 matches the current character of s1 or s2.
        // Update dp[i][j] accordingly
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // If current char of s1 matches s3, check if s1 up to i-1 and s2 up to j can form s3
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }
                //If solution found
                if (dp[i][j]) {
                    continue;
                }
                // If current char of s2 matches s3, check if s1 up to i and s2 up to j-1 can form s3
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    //Solution 3
    //O(n1 * n2) TC solution and O(n1 * n2) SC solution
    public boolean isInterleave3(String s1, String s2, String s3) {
        //If the sum of s1 and s2 length is not same as s3 length, then we can't divide the s1 and s2 strings to form s3
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        //dp[i][j] represents if s3[i+j, s3.length()] can be formed as interleaving string from s1[i, s1.length()] and s2[j, s2.length()]
        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        return isInterleaveH(s1, s2, s3, 0, 0, dp);
    }

    private Boolean isInterleaveH(String s1, String s2, String s3, int index1, int index2, Boolean[][] dp) {
        //If index3 = index1 + index2 is same as s3.length(), then we achieved
        if (index1 + index2 == s3.length()) {
            return true;
        }

        if (dp[index1][index2] != null) {
            return dp[index1][index2];
        }

        //Consider character from s1 if the character at index1 is same as character from index3
        boolean solutionFromS1 = false;
        if (index1 < s1.length() && s1.charAt(index1) == s3.charAt(index1 + index2)) {
            solutionFromS1 = isInterleaveH(s1, s2, s3, index1 + 1, index2, dp);
        }
        if (solutionFromS1) {
            return dp[index1][index2] = true;
        }

        //Consider character from s2 if the character at index2 is same as character from index3
        if (index2 < s2.length() && s2.charAt(index2) == s3.charAt(index1 + index2)) {
            return dp[index1][index2] = isInterleaveH(s1, s2, s3, index1, index2 + 1, dp);
        }
        return dp[index1][index2] = false;
    }


    //We don't need extra index3. Because, index3 is what? Position of s3, which will be number of characters covered from s1 and s2
    //So index3 will be index1 + index2
    //Solution 2
    public boolean isInterleave2(String s1, String s2, String s3) {
        return isInterleaveH(s1, s2, s3, 0, 0, 0);
    }

    private boolean isInterleaveH(String s1, String s2, String s3, int index1, int index2) {
        if(index1 == s1.length() && index2 == s2.length() && index1 + index2 == s3.length()) {
            return true;
        }

        if ((index1 == s1.length() && index1 + index2 == s3.length()) || (index2 == s2.length() && index1 + index2 == s3.length())) {
            return false;
        }

        boolean solutionFromS1 = false;
        if (index1 < s1.length() && index1 + index2 < s3.length() && s1.charAt(index1) == s3.charAt(index1 + index2)) {
            solutionFromS1 = isInterleaveH(s1, s2, s3, index1 + 1, index2);
        }
        if (solutionFromS1) {
            return true;
        }
        if (index2 < s2.length() && index1 + index2 < s3.length() && s2.charAt(index2) == s3.charAt(index1 + index2)) {
            return isInterleaveH(s1, s2, s3, index1, index2 + 1);
        }
        return false;
    }


    //Solution 1
    //Naive solution using Backtracking
    //O(2^n) TC solution => At each point, you have two options either choose character from string s1 or string s2
    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleaveH(s1, s2, s3, 0, 0, 0);
    }

    private boolean isInterleaveH(String s1, String s2, String s3, int index1, int index2, int index3) {
        //If we have covered all character's from all strings, then we achieved
        if (index1 == s1.length() && index2 == s2.length() && index3 == s3.length()) {
            return true;
        }

        //If we exhaust characters from s3 but not from s1 or s2, then we can't achieve
        if ((index1 == s1.length() && index3 == s3.length()) || (index2 == s2.length() && index3 == s3.length())) {
            return false;
        }

        //Consider character from s1 if the character at index1 is same as character from index3
        boolean solutionFromS1 = false;
        if (index1 < s1.length() && index3 < s3.length() && s1.charAt(index1) == s3.charAt(index3)) {
            solutionFromS1 = isInterleaveH(s1, s2, s3, index1 + 1, index2, index3 + 1);
        }
        if (solutionFromS1) {
            return true;
        }
        //Consider character from s2 if the character at index2 is same as character from index3
        if (index2 < s2.length() && index3 < s3.length() && s2.charAt(index2) == s3.charAt(index3)) {
            return isInterleaveH(s1, s2, s3, index1, index2 + 1, index3 + 1);
        }
        return false;
    }
}
