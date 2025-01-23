package arrays_and_hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/
 */
public class WinnerOfCircularGame {

    //Solution 2
    //O(n) TC and O(n) SC
    public int findTheWinner2(int n, int k) {
        return winner(n, k) + 1;
    }

    public int winner(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (winner(n - 1, k) + k) % n;
    }

    //Solution 1
    //O(n^2) TC because remove index in list takes O(n) and O(n) SC
    public int findTheWinner(int n, int k) {
        List<Integer> l = new ArrayList<>();
        //Create the list
        for (int i = 0; i < n; i++) {
            l.add(i + 1);
        }
        int index;
        int i = 0; //Start from i = 0 => 1st friend
        while (l.size() != 1) {
            index = (i + (k - 1)) % l.size(); //Find the index where it will end
            l.remove(index); //Remove the index
            i = index; //Assign index to i, because, shifting happens after we remove from list and next element will be available at index
        }
        return l.get(0); //Winner
    }
}
