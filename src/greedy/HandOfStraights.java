package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/hand-of-straights/description/
 */
public class HandOfStraights {

    //O(nlogn) solution
    public boolean isNStraightHand(int[] hand, int groupSize) {
        //If the given array size is not multiple of groupSize, then return false
        if (hand.length % groupSize != 0) {
            return false;
        }
        //Create a map of frequencies
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < hand.length; i++) {
            mp.put(hand[i], mp.getOrDefault(hand[i], 0) + 1);
        }
        //Sort given array
        Arrays.sort(hand);
        //For each hand, verify if it can be grouped if it's still not grouped
        for (int i = 0; i < hand.length; i++) {
            if (mp.get(hand[i]) != 0) {
                mp.put(hand[i], mp.get(hand[i]) - 1);
                //Verify if the next consecutive elements are available till group is formed
                for (int j = 1; j <= groupSize - 1; j++) {
                    if (mp.containsKey(hand[i] + j) && mp.get(hand[i] + j) != 0) {
                        mp.put(hand[i] + j, mp.get(hand[i] + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        //All the elements should be used, and so each one of their frequencies should be zero finally if everything is grouped
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,6,2,3,4,7,8};
        HandOfStraights handOfStraights = new HandOfStraights();
        //o/p should be true
        System.out.println(handOfStraights.isNStraightHand(arr, 3));
    }
}
