package arrays_and_hashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    //First turn the input into a set of numbers. That takes O(n) and then we can ask in O(1) whether we have a certain number.
    //Then go through the numbers. If the number x is the start of a streak (i.e., x-1 is not in the set), then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set.
    // The length of the streak is then simply y-x, and we update our global best with that. Since we check each streak only once, this is overall O(n)
    //O(n) T.C
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        int max = 0;
        for (int n : s) {
            //If the prev element is not present, then check consecutive elements for current element
            if (!s.contains(n - 1)) {
                int length = 1;
                //If it contains consecutive elements, keep adding to set
                while (s.contains(n + 1)) {
                    length++;
                    n = n + 1;
                }
                max = Math.max(length, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        //O/p should be 4. i.e [1, 2, 3, 4]
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));
    }
}
