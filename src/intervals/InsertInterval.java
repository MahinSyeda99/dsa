package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/description/
 */
public class InsertInterval {
    //T.C: O(n)
    //S.C: O(1), assuming space for creating list out of arr is not counted. Since if list is give, it is in-place
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //First, insert the interval
        List<int[]> insertedResult = new ArrayList<>();
        boolean found = false;
        for (int[] interval : intervals) {
            if (newInterval[0] < interval[0]) {
                insertedResult.add(newInterval);
                insertedResult.add(interval);
                found = true;
            } else {
                insertedResult.add(interval);
            }
        }
        if (!found) {
            insertedResult.add(newInterval);
        }
        //Then merge the intervals
        //To keep track of last updated and do in-place update
        int index = 0;
        for (int i = 1; i < insertedResult.size(); i++) {
            //If we find in correct order and no overlapping with previous, then add the current interval
            if (insertedResult.get(i)[0] > insertedResult.get(index)[1]) {
                index++;
                insertedResult.set(index, insertedResult.get(i));
            } else {
                //If overlapping
                //Update the last interval's end with max of current interval's end and previous interval's end
                insertedResult.get(index)[1] = Math.max(insertedResult.get(index)[1], insertedResult.get(i)[1]);
            }
        }
        return insertedResult.subList(0, index + 1).toArray(new int[index + 1][]);
    }

    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        // O/p should be [[1, 5], [6, 9]]
//        int[][] arr = {{1, 3}, {6, 9}};
//        int[] newInterval = {2, 5};

        // O/p should be [[1,5]]
//        int[][] arr = {{1,5}};
//        int[] newInterval = {2, 3};

        // O/p should be [[5, 7]]
//        int[][] arr = {};
//        int[] newInterval = {5, 7};

        // O/p should be [[1, 5], [6, 8]]
//        int[][] arr = {{1,5}};
//        int[] newInterval = {6, 8};

        // O/p should be [[0,0],[1,5]]
        int[][] arr = {{1, 5}};
        int[] newInterval = {0, 0};
        System.out.println(Arrays.deepToString(insertInterval.insert(arr, newInterval)));
    }
}
