package bit_manipulation;

import java.util.Arrays;

public class ShortestSubarrayWithORAtleastK {

    //O(n) TC sliding window solution
    public int minimumSubarrayLength(int[] nums, int k) {
        //Array to maintain frequency of 1's at bit positions in current window
        int[] freq = new int[32];
        Arrays.fill(freq, 0);
        //left pointer
        int l = 0;
        //Right pointer
        int r = 0;
        int minLength = Integer.MAX_VALUE;
        int orVal = 0;
        while (r < nums.length) {
            //Update frequency
            updateFreq(nums[r], freq);
            //Update or value
            orVal = (orVal | nums[r]);
            //If we found orVal >= k
            while (orVal >= k && l <= r) {
                //Update min length
                minLength = Math.min(r - l + 1, minLength);
                //Remove nums[l] from or value
                orVal = removeLeftFromOr(orVal, nums[l], freq);
                //Shrink left
                l = l + 1;
            }
            r++;
        }
        if (orVal >= k) {
            minLength = Math.min(r - l + 1, minLength);
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    private void updateFreq(int num, int[] freq) {
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                freq[i] += 1;
            }
        }
    }

    private int removeLeftFromOr(int orVal, int num, int[] freq) {
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                if (freq[i] == 1) {
                    //Only one num had set 1 at position i, which is num
                    //So we need to unset position in orVal
                    orVal = (orVal ^ (1 << i));
                }
                //Decrement frequency
                freq[i]--;
            }
        }
        return orVal;
    }
}
