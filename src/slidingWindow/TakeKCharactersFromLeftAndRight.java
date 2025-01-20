package slidingWindow;

/**
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right
 */
public class TakeKCharactersFromLeftAndRight {

    //O(n) TC, sliding window solution
    //O(3) ~ O(1) SC
    public int takeCharacters(String s, int k) {
        if (k == 0) {
            return 0;
        }
        //Count from left
        int[] countLeft = new int[3];
        //Count from right
        int[] countRight = new int[3];
        int n = s.length();
        int l = n - 1;
        int r = n - 1;
        //Find the count of characters
        for (; l >= 0; l--) {
            countRight[s.charAt(l) - 'a']++;
            if (countRight[0] >= k && countRight[1] >= k && countRight[2] >= k) {
                break;
            }
        }
        //Some of the characters frequency is not >= k
        if (l < 0) {
            return -1;
        }
        //For x = 0
        int result = r - l + 1;
        //For each value of x, check how many elements from right are required
        for (int x = 1; x <= s.length(); x++) {
            //Increase left count
            countLeft[s.charAt(x - 1) - 'a']++;
            //If left and right windows overlap, move right window
            while (x - 1 >= l) {
                countRight[s.charAt(l) - 'a']--;
                l++;
            }
            //If the total count of left most character in right window has count > k, then we can afford to move the right window, as we will still get count>=k
            while (l <= r && (countRight[s.charAt(l) - 'a'] + countLeft[s.charAt(l) - 'a'] > k)) {
                countRight[s.charAt(l) - 'a']--;
                l++;
            }
            //x + r - l + 1 is the total number of elements
            result = Math.min(result, x + r - l + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        TakeKCharactersFromLeftAndRight takeKCharactersFromLeftAndRight = new TakeKCharactersFromLeftAndRight();
        System.out.println(takeKCharactersFromLeftAndRight.takeCharacters("aabaaaacaabc", 2));
    }
}
