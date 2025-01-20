package greedy;

/**
 * https://leetcode.com/problems/jump-game/description/
 */
public class JumpGame {

    //Greedy, O(n) solution
    public boolean canJump(int[] nums) {
        // If value of first index guarantees only 1 jump is needed, return true
        if(nums[0] >= nums.length - 1) {
            return true;
        }
        //Can't reach final index
        if(nums[0] == 0) {
            return false;
        }
        //maximum reachable index
        int maxReach = nums[0];
        //number of remaining steps at current position
        int steps = nums[0];
        //Number of jumps taken
        int jumps = 1;
        for(int i = 1; i < nums.length; i++) {
            // Check if we have reached the end of the array
            if (i == nums.length - 1) {
                return true;
            }
            //Check if value at current index guarantees jump to end
            if ((i + nums[i]) >= (nums.length - 1)){
                return true;
            }
            //Update max reach
            maxReach = Math.max(maxReach, i + nums[i]);
            // We use a step to get to the current index
            steps--;
            if(steps == 0) {
                //We will need a jump to get to maxReach position
                jumps++;
                // Check if the current index/position is the maximum reachable point from the current or previous indexes, then we can't go further
                if(i >= maxReach) {
                    return false;
                }
                //Update steps to the remaining steps needed to reach maxReach from current index
                steps = maxReach - i;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = {2, 3, 1, 1, 4};
        //O/p should be true
        System.out.println(jumpGame.canJump(nums));
    }
}
