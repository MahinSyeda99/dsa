package two_dimensional_dp;

public class RegExMatching {

    //Solution 2
    //Bottom up 2D DP
    //O(n * m) TC where n is length of string s and m is length of string p
    //O(n * m) SC
    public boolean isMatch2(String s, String p) {
        //dp[i][j] represents match between s[i, s.length()) and p[j, p.length())
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        //Base case
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i>= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                // Check if current characters match (for single character or '.')
                boolean currentMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                // Handle '*' wildcard
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    // Skip '*' pattern entirely(i.e. zero matching) or continue with currentMatch(to match one or more characters by advancing i)
                    dp[i][j] = dp[i][j + 2] || (currentMatch && dp[i + 1][j]);
                } else {
                    // Regular matching, move to next character in both strings
                    //If '*' is not involved, it simply proceeds to the next character in both s and p if currentMatch is true
                    dp[i][j] = currentMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    //Solution 1
    //Top down 2D DP
    //O(n * m) TC where n is length of string s and m is length of string p
    //O(n * m) SC
    public boolean isMatch(String s, String p) {
        //dp[i][j] represents match between s[i, s.length()) and p[j, p.length())
        Boolean[][] dp = new Boolean[s.length() + 1][p.length()];
        return isMatchH(s, p, 0, 0, dp);
    }

    private boolean isMatchH(String s, String p, int i , int j, Boolean[][] dp) {
        // Base case: both strings are fully matched
        if (j == p.length()) {
            return i == s.length();
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // Check if current characters match (for single character or '.')
        boolean currentMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        // Handle '*' wildcard, if next character is *
        //Ex: a*, then we can do 0 matching, so ignore a and * completely and move to next char in j
        //(or) 1 or more matching of * with value if there is a current match in characters, so increasing i
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Skip '*' pattern entirely(i.e. zero matching) or continue with currentMatch(to match one or more characters by advancing i)
            return dp[i][j] = isMatchH(s, p, i, j + 2, dp) || (currentMatch && isMatchH(s, p, i + 1, j, dp));
        }

        // Regular matching, move to next character in both strings
        //If '*' is not involved, it simply proceeds to the next character in both s and p if currentMatch is true
        return dp[i][j] = currentMatch && isMatchH(s, p, i + 1, j + 1, dp);
    }
}
