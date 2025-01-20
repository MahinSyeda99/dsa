package bit_manipulation;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/description
 */
public class LargestCombination {
    //We can avoid O(32) SC, by iterating for all the candidates for each position to find set bits count
    //O(32 * n) TC solution ~ O(n)
    public int largestCombination2(int[] candidates) {
        int result = 0;

        //Loop through each position
        for (int i = 0; i < 32; i++) {
            //Count of set bits for position j
            int count = 0;
            //Loop through all candidates
            for (int j = 0; j < candidates.length; j++) {
                //(1<<i) => 1 at ith position, so candidates[i] & (1 << i) => checking if 1 is set at ith position in candidates[i]
                if ((candidates[j] & (1 << i)) != 0) {
                    count++;
                }
            }
            //Update result
            result = Math.max(count, result);
        }
        return result;
    }


    //O(32 * n) TC ~ O(n) and O(32) SC
    public int largestCombination(int[] candidates) {
        //count of set digits for each position
        int[] count = new int[32];
        Arrays.fill(count, 0);

        //Iterate through each candidate and add to the count if that position is set
        for (int i = 0; i < candidates.length; i++) {
            int num = candidates[i];
            int j = 31;
            while (num != 0) {
                if ((num & 1) != 0) {
                    //If position is set, then add it to the count
                    count[j] = 1 + count[j];
                }
                j--;
                num = num >> 1;
            }
        }

        int result = 0;
        //Find the positon with max set bits count
        for (int i = 0; i < 32; i++) {
            if (count[i] > result) {
                result = count[i];
            }
        }
        return result;
    }
}
