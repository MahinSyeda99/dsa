package one_dimensional_dp;

/**
 * https://leetcode.com/problems/climbing-stairs/description/
 */
public class ClimbStairs {

    //O(n) TC and O(1) S.C
    public int climbStairs(int n) {
        //Base conditions
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        //Instead of using a cache/table, we can just use two variables, as we only need currentPrev and prev values to calculate for the current n value
        //Using a bottom up approach here
        //No of ways of reaching nth stair, F(n) = F(n-1) + F(n-2)
        int currentPrev = 2; //F(n-1)
        int prev = 1; //F(n-2)
        int current = 0; //F(n)
        for (int i = 3; i <= n; i++) {
            //No of ways to reach nth stair
            current = currentPrev + prev;
            //Set the prev to currentPrev
            prev = currentPrev;
            //Set currentPrev to current
            currentPrev = current;
        }
        return current;
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        //o/p is 2
        System.out.println(climbStairs.climbStairs(2));
        //o/p is 3
        System.out.println(climbStairs.climbStairs(3));
        //o/p is 8
        System.out.println(climbStairs.climbStairs(5));
    }
}
