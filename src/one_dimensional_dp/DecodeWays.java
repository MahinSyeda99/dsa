package one_dimensional_dp;

import java.util.Arrays;

public class DecodeWays {

    //O(n) TC, 1D DP, starting from end
    public int numDecodings(String s) {
        int n = s.length();
        //dp to keep track of decodings possible at every index
        int[] dp = new int[n + 1];

        //For n, decodings possible for empty string is 1
        //Base case
        dp[n] = 1;
        //Initialize for last character
        //For n-1,
        dp[n-1] = s.charAt(n-1) == '0' ? 0 : 1;

        //Iterate from the second-last character to the start
        for(int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0; // If current character is '0', no valid decoding
            } else {
                //Single digit decoding
                dp[i] = dp[i+1];
                //Double digit decoding
                if (Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                    //Add the value of number of decodings possible at i + 2 to dp[i]
                    dp[i] = dp[i] + dp[i+2];
                }
            }
        }
        return dp[0];
    }

    //O(n) TC DP solution, O(n) SC
    public int numDecodings2DDp(String s) {
        int n = s.length();
        //dp to keep track of decodings possible at every index for single digit decoding and double digit decoding
        int[][] dp = new int[2][s.length()];
        //Fill with 0
        for(int i = 0; i < 1; i++) {
            Arrays.fill(dp[i], 0);
        }
        //Initializing last dp value
        //If last character is not zero, then we can get single digit decoding with number of ways 1
        //There is no double digit decoding possible, so value remains zero
        if (Integer.valueOf(s.substring(n - 1, n)) != 0) {
            dp[0][n-1] = 1;
        }
        //For last two digits, if first digit is 0(ex:06), then not correct
        //If its not zero, then for single digit decondig, the value will be same as the next digit decoding
        //If double digit decoing and it is less than equal to 26, then value will be 1
        if (n - 2 >=0 && Integer.valueOf(s.substring(n - 2, n - 1)) != 0) {
            dp[0][n-2] = dp[0][n-1];
            if (Integer.valueOf(s.substring(n - 2, n)) <= 26) {
                dp[1][n-2] = 1;
            }
        }
        //For rest of the characters, we evaluate the dp
        for(int i = n - 3; i >=0; i--) {
            //If single digit is 0, then don't proceed to evaluate
            if (Integer.valueOf(s.substring(i, i + 1)) != 0) {
                //If single digit is not zero, then for single digit decoding, the value will be sum of single digit decoding value at next index and double digit decoding value at next index
                dp[0][i] = dp[0][i+1] + dp[1][i+1];
                //For double digit decoding
                if (Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                    //Value will be sum of single digit decoding value at index + 2 and double digit decoding value at index + 2
                    dp[1][i] = dp[0][i+2] + dp[1][i+2];
                }
            }
        }
        //Final result will be sum of single digit decoding value at index 0 and double digit decoding value at index 1
        return dp[0][0] + dp[1][0];
    }

    //Back tracking, 2^n solution.
    //For n <= 100, 2^100 ~ (2^10)^10 ~ 10^10 > 10^8, so TLE
    /**
     private int count = 0;
    public int numDecodings(String s) {
        numDecodingsH(s, 0);
        return count;
    }

    private void numDecodingsH(String s, int i) {
        if (i >= s.length()) {
            count++;
            return;
        }

        if (Integer.parseInt(s.substring(i, i + 1)) == 0) {
            return;
        }
        numDecodingsH(s, i + 1);
        if (i + 1 < s.length()) {
            if(Integer.parseInt(s.substring(i, i + 2)) > 26) {
                return;
            } else {
                numDecodingsH(s, i + 2);
            }
        }
    }
     **/

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        //3
        System.out.println(decodeWays.numDecodings("223"));
        //1836311903
        System.out.println(decodeWays.numDecodings("111111111111111111111111111111111111111111111"));
    }
}
