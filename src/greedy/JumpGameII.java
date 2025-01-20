package greedy;

/**
 * https://leetcode.com/problems/jump-game-ii/description/
 */
public class JumpGameII {

    //O(n) greedy solution
    public int jump(int[] nums) {
        //window start
        int l = 0;
        //window end
        int r = 0;
        //Number of jumps
        int jumps = 0;
        while (r < nums.length - 1) {
            //Farthest for a window
            int maxReachable = 0;
            //Find maxReachable in the window
            for(int i = l; i <= r; i++) {
                maxReachable = Math.max(maxReachable, i + nums[i]);
            }
            //Update left to right + 1
            l = r + 1;
            //Update right to maxReachable
            r = maxReachable;
            //Update jump
            jumps++;
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        JumpGameII jumpGameII = new JumpGameII();
        //O/p should be 2
        System.out.println(jumpGameII.jump(nums));
    }
}
