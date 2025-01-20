package bit_manipulation;

/**
 * https://leetcode.com/problems/maximum-xor-for-each-query/description/
 */
public class MaximumXOR {

    public int[] getMaximumXor2(int[] nums, int maximumBit) {
        int[] result = new int[nums.length];
        //last position of result
        int r = nums.length - 1;
        int xor = 0;
        //k < 2^maximumBit => k <= 2^maximumBit - 1 => kmax = 2^maximumBit - 1
        int kmax = (int) Math.pow(2, maximumBit) - 1;
        for (int i = 0; i < nums.length; i++) {
            //Find xor
            xor = xor ^ nums[i];
            //Get the maximumBits positions only to xor with kmax, to get correct k value
            int xornew = xor & kmax;
            //If we xor maximumBit positions with kmax(which will have all 1's), then we get the k value
            //Because, 1 ^ 1= 0 and 0 ^ 1 = 1, so that will increase the xor value
            //basically, if we have 1 in the position, we want 0 in that position in k value and if we have 0, then we want 1 in position in k value to maximize xor value
            int k = xornew ^ kmax;
            result[r--] = k;
        }
        return result;
    }

    //Solution 1
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] result = new int[nums.length];
        //last position of result
        int r = nums.length - 1;
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            //Find xor
            xor = xor ^ nums[i];
            //Our k
            int k = 0;
            int position = 0;
            int dup = xor;
            //Iterate through each position till maximumBit - 1, as k < 2^maximumBit => k <= 2^maximumBit - 1 => maximumBit number of positions
            while (position <= (maximumBit - 1)) {
                //If the position value is 0, then if we xor with 1, we get 1, so that will increase the xor value
                //Add the position value to k
                if ((dup & 1) == 0) {
                    k = k + (int) Math.pow(2, position);
                }
                //Right shift xor value
                dup = dup >> 1;
                //Increase position
                position++;
            }
            //Add k to result
            result[r--] = k;
        }
        return result;
    }
}
