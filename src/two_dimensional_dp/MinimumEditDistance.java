package two_dimensional_dp;

import java.util.Arrays;

public class MinimumEditDistance {

    //Solution 2
    //Using 2D DP, bottom-up approach
    //O(n * m) TC and O(n * m) SC
    public int minDistance2(String word1, String word2) {
        //dp[i][j] represents minimum edit distance for word1[0, i) to form word2[0, j)
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        //Base case - Minimum edit distance to form empty string from empty string
        dp[0][0] = 0;
        //Minimum edit distance to form word2 from word1 as empty string
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        //Minimum edit distance to form empty string word2 from word1
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                //Match in characters
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //Insert
                    dp[i][j] = 1 + dp[i][j - 1];
                    //Delete
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i - 1][j]);
                    //Replace
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i - 1][j - 1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }


    //Solution 1
    //Using 2D DP, top-down approach
    //O(n * m) TC and O(n * m) SC
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return minDistH(word1, word2, 0, 0, dp);
    }

    private int minDistH(String word1, String word2, int i, int j, int[][] dp) {
        if (i == word1.length()) {
            //If we reached end of word1
            //We need to insert remaining characters from word2
            return word2.length() - j;
        }

        if (j == word2.length()) {
            //If we reached end of word2
            //We need to delete remaining characters from word2
            return word1.length() - i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int min = Integer.MAX_VALUE;
        if (word1.charAt(i) == word2.charAt(j)) {
            //If characters are equal
            //Move to next character, i.e do nothing
            min = Math.min(min, minDistH(word1, word2, i + 1, j + 1, dp));
        } else {
            //Insert character
            min = Math.min(min, 1 + minDistH(word1, word2, i, j + 1, dp));
            //Delete character
            min = Math.min(min, 1 + minDistH(word1, word2, i + 1, j, dp));
            //Replace character
            min = Math.min(min, 1 + minDistH(word1, word2, i + 1, j + 1, dp));
        }
        return dp[i][j] = min;
    }
}
