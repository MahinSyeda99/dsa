package two_dimensional_dp;

public class UniquePaths {

    //Bottom up approach, O(m * n) TC and O(m * n) SC
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        //No. of ways to reach bottom-right corner is 1 to reach anywhere from last row
        //Since he can't go down further and can only go right
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }

        //No. of ways to reach bottom-right corner is 1 to reach anywhere from last column
        //Since he can't go right further and can only go down
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                //No.of ways to reach i, j is no. of ways from right + no. of ways from down
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        //o/p is 3
        System.out.println(uniquePaths.uniquePaths(3, 2));
        //o/p is 28
        System.out.println(uniquePaths.uniquePaths(3, 7));
    }
}
